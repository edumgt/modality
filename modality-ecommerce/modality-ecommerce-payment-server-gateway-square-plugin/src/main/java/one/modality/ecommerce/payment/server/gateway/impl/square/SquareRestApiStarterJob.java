package one.modality.ecommerce.payment.server.gateway.impl.square;

import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.authentication.BearerAuthModel;
import com.squareup.square.models.*;
import dev.webfx.platform.boot.spi.ApplicationJob;
import dev.webfx.platform.console.Console;
import dev.webfx.platform.vertx.common.VertxInstance;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import one.modality.ecommerce.payment.PaymentService;
import one.modality.ecommerce.payment.PaymentStatus;
import one.modality.ecommerce.payment.UpdatePaymentStatusArgument;

/**
 * @author Bruno Salmon
 */
public final class SquareRestApiStarterJob implements ApplicationJob {

    private final static boolean DEBUG = true;

    static final String SQUARE_PAYMENT_FORM_ENDPOINT             = "/payment/square/paymentForm/:htmlCacheKey";
    static final String SQUARE_LIVE_COMPLETE_PAYMENT_ENDPOINT    = "/payment/square/live/completePayment";
    static final String SQUARE_SANDBOX_COMPLETE_PAYMENT_ENDPOINT = "/payment/square/sandbox/completePayment";
    private static final String SQUARE_LIVE_WEBHOOK_ENDPOINT     = "/payment/square/live/webhook";
    private static final String SQUARE_SANDBOX_WEBHOOK_ENDPOINT  = "/payment/square/sandbox/webhook";

    @Override
    public void onInit() {
        Router router = VertxInstance.getHttpRouter();

        // This endpoint is called by the Modality front-office when the web payment form content requires a subsequent
        // server call, typically when it is embedded in an iFrame, iFrame.src is set to that endpoint to pull the html
        // code and start the web payment form.
        router.route(SQUARE_PAYMENT_FORM_ENDPOINT)
                .handler(ctx -> {
                    // Because it is a subsequent call to SquarePaymentGateway.initiatePayment(), we expect the content
                    // to be present in the html cache, as set by SquarePaymentGateway.initiatePayment() just a moment before
                    String cacheKey = ctx.pathParam("htmlCacheKey");
                    String html = SquareRestApiOneTimeHtmlResponsesCache.getOneTimeHtmlResponse(cacheKey);
                    // And we return that content
                    ctx.response()
                            .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaders.TEXT_HTML)
                            .end(html);
                });

        // This endpoint is called by the web payment form after the user pressed the Pay button and all verifications
        // have been successful on the web payment form (CC details, buyer verified, etc...)
        router.route(SQUARE_LIVE_COMPLETE_PAYMENT_ENDPOINT)
                .handler(BodyHandler.create()) // To ensure the whole payload is loaded before calling the next handler
                .handler(ctx -> handleCompletePayment(ctx, true));

        // Same endpoint but for sandbox payments
        router.route(SQUARE_SANDBOX_COMPLETE_PAYMENT_ENDPOINT)
                .handler(BodyHandler.create()) // To ensure the whole payload is loaded before calling the next handler
                .handler(ctx -> handleCompletePayment(ctx, false));

        // Endpoint for live payments Square web hook
        router.route(SQUARE_LIVE_WEBHOOK_ENDPOINT)
                .handler(BodyHandler.create()) // To ensure the whole payload is loaded before calling the next handler
                .handler(ctx -> handleWebhook(ctx, true));

        // Same endpoint but for sandbox payments
        router.route(SQUARE_SANDBOX_WEBHOOK_ENDPOINT)
                .handler(BodyHandler.create()) // To ensure the whole payload is loaded before calling the next handler
                .handler(ctx -> handleWebhook(ctx, false));
    }

    private void handleCompletePayment(RoutingContext ctx, boolean live) {
        if (DEBUG) {
            Console.log("[Square] completePayment - step 0 - endpoint called with live = " + live);
        }
        JsonObject payload = ctx.body().asJsonObject();
        String paymentId = payload.getString("modality_paymentId");
        Long amount = payload.getLong("modality_amount");
        String currencyCode = payload.getString("modality_currencyCode");
        String locationId = payload.getString("square_locationId");
        String idempotencyKey = payload.getString("square_idempotencyKey");
        String sourceId = payload.getString("square_sourceId");
        String verificationToken = payload.getString("square_verificationToken");
        // TODO check all the above values are set, otherwise return an error

        PaymentService.loadPaymentGatewayParameters(paymentId, live)
                .onFailure(e -> ctx.end(e.getMessage()))
                .onSuccess(parameters -> {
                    if (DEBUG) {
                        Console.log("[Square] completePayment - step 1 - payment gateway parameters loaded");
                    }
                    String accessToken = parameters.get("access_token");
                    // TODO check accessToke is set, otherwise return an error
                    SquareClient client = new SquareClient.Builder()
                            .environment(live ? Environment.PRODUCTION : Environment.SANDBOX)
                            .bearerAuthCredentials(new BearerAuthModel.Builder(accessToken).build())
                            .build();
                    if (DEBUG) {
                        Console.log("[Square] completePayment - step 2 - calling Square createPayment with amount = " + amount + ", currencyCode = " + currencyCode);
                    }
                    PaymentsApi paymentsApi = client.getPaymentsApi();
                    paymentsApi.createPaymentAsync(new CreatePaymentRequest.Builder(sourceId, idempotencyKey)
                            .locationId(locationId)
                            .verificationToken(verificationToken)
                            .amountMoney(new Money(amount, currencyCode))
                            .build()
                    ).thenAccept(result -> {
                        if (DEBUG) {
                            Console.log("[Square] completePayment - step 3 - createPayment returned without exception");
                        }
                        Payment payment = result.getPayment();
                        JsonObject gatewayResponseJson = new JsonObject();
                        gatewayResponseJson.put("id", payment.getId());
                        gatewayResponseJson.put("status", payment.getStatus());
                        gatewayResponseJson.put("buyerEmailAddress", payment.getBuyerEmailAddress());
                        CardPaymentDetails cardDetails = payment.getCardDetails();
                        Card card = cardDetails == null ? null : cardDetails.getCard();
                        if (card != null) {
                            gatewayResponseJson.put("cardBrand", card.getCardBrand());
                            gatewayResponseJson.put("cardLast4", card.getLast4());
                        }
                        gatewayResponseJson.put("orderId", payment.getOrderId());
                        gatewayResponseJson.put("createdAt", payment.getCreatedAt());
                        gatewayResponseJson.put("updatedAt", payment.getUpdatedAt());
                        gatewayResponseJson.put("receiptNumber", payment.getReceiptNumber());
                        gatewayResponseJson.put("receiptUrl", payment.getReceiptUrl());
                        String gatewayResponse = gatewayResponseJson.toString();
                        String gatewayTransactionRef = payment.getId();
                        String gatewayStatus = payment.getStatus();
                        SquarePaymentStatus squarePaymentStatus = SquarePaymentStatus.valueOf(gatewayStatus.toUpperCase());
                        PaymentStatus paymentStatus = squarePaymentStatus.getGenericPaymentStatus();
                        boolean pending = paymentStatus.isPending();
                        boolean successful = paymentStatus.isSuccessful();
                        if (DEBUG) {
                            Console.log("[Square] completePayment - step 4 - updating the payment status in the database");
                        }
                        PaymentService.updatePaymentStatus(UpdatePaymentStatusArgument.createCapturedStatusArgument(paymentId, gatewayResponse, gatewayTransactionRef, gatewayStatus, pending, successful))
                                .onSuccess(v -> ctx.end(gatewayStatus.toUpperCase()))
                                .onFailure(e -> ctx.end(e.getMessage()))
                        ;
                    }).exceptionally(ex -> {
                        if (DEBUG) {
                            Console.log("[Square] completePayment - Square raised exception " + ex.getMessage());
                        }
                        PaymentService.updatePaymentStatus(UpdatePaymentStatusArgument.createExceptionStatusArgument(paymentId, null, ex.getMessage()))
                                .onSuccess(v -> ctx.end(ex.getMessage()))
                                .onFailure(e -> ctx.end(e.getMessage()));
                        return null;
                    });
                });
    }

    private void handleWebhook(RoutingContext ctx, boolean live) {
        JsonObject payload = ctx.body().asJsonObject();
        Console.log("[Square] webhook called with live = " + live + ", payload = " + payload.encode());
        // TODO
        ctx.response().setStatusCode(HttpResponseStatus.OK.code()).end();
    }

}
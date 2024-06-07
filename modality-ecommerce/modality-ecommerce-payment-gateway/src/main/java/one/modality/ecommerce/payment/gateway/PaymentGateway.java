package one.modality.ecommerce.payment.gateway;

import dev.webfx.platform.async.Future;

public interface PaymentGateway {

    String getName();

    Future<GatewayInitiatePaymentResult> initiatePayment(GatewayInitiatePaymentArgument argument);

    Future<GatewayMakeApiPaymentResult> makeApiPayment(GatewayMakeApiPaymentArgument argument);

}

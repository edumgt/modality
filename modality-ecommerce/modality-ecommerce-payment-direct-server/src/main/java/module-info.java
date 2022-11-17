// File managed by WebFX (DO NOT EDIT MANUALLY)

module modality.ecommerce.payment.direct.server {

    // Direct dependencies modules
    requires java.base;
    requires modality.ecommerce.payment.direct;
    requires modality.ecommerce.payment.gateway.direct;
    requires webfx.platform.async;

    // Exported packages
    exports one.modality.ecommerce.payment.direct.spi.impl.server;

    // Used services
    uses one.modality.ecommerce.payment.gateway.direct.spi.DirectPaymentGatewayProvider;

    // Provided services
    provides one.modality.ecommerce.payment.direct.spi.DirectPaymentProvider with one.modality.ecommerce.payment.direct.spi.impl.server.ServerDirectPaymentProvider;

}
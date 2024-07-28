// File managed by WebFX (DO NOT EDIT MANUALLY)

module modality.crm.client.authn.login.ui.gateway.signup.plugin {

    // Direct dependencies modules
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires modality.base.client.validation;
    requires modality.base.shared.entities;
    requires modality.crm.shared.authn;
    requires webfx.extras.styles.bootstrap;
    requires webfx.kit.util;
    requires webfx.stack.authn.login.ui.gateway;
    requires webfx.stack.hash.md5;
    requires webfx.stack.i18n;
    requires webfx.stack.i18n.controls;
    requires webfx.stack.orm.datasourcemodel.service;
    requires webfx.stack.orm.domainmodel;
    requires webfx.stack.orm.entity;
    requires webfx.stack.session.state.client.fx;

    // Exported packages
    exports one.modality.crm.client.authn.login.ui.gateway.signup;

    // Provided services
    provides dev.webfx.stack.authn.login.ui.spi.impl.gateway.UiLoginGatewayProvider with one.modality.crm.client.authn.login.ui.gateway.signup.ModalitySignupUiLoginGatewayProvider;

}
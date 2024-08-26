// File managed by WebFX (DO NOT EDIT MANUALLY)

module modality.crm.server.authn.gateway.magiclink.plugin {

    // Direct dependencies modules
    requires modality.base.server.mail;
    requires modality.base.shared.context;
    requires modality.base.shared.entities;
    requires modality.crm.shared.authn;
    requires webfx.platform.async;
    requires webfx.platform.console;
    requires webfx.platform.util;
    requires webfx.stack.authn;
    requires webfx.stack.authn.logout.server;
    requires webfx.stack.authn.server.gateway;
    requires webfx.stack.mail;
    requires webfx.stack.orm.datasourcemodel.service;
    requires webfx.stack.orm.domainmodel;
    requires webfx.stack.orm.entity;
    requires webfx.stack.push.server;
    requires webfx.stack.session.state;

    // Exported packages
    exports one.modality.crm.server.authn.gateway.magiclink;

    // Provided services
    provides dev.webfx.stack.authn.server.gateway.spi.ServerAuthenticationGatewayProvider with one.modality.crm.server.authn.gateway.magiclink.ModalityMagicLinkAuthenticationGatewayProvider;

}
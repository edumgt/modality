// File managed by WebFX (DO NOT EDIT MANUALLY)

module modality.ecommerce.document.service.server {

    // Direct dependencies modules
    requires modality.base.shared.entities;
    requires modality.ecommerce.document.service;
    requires modality.ecommerce.history.server;
    requires webfx.platform.ast;
    requires webfx.platform.async;
    requires webfx.platform.util;
    requires webfx.stack.com.serial;
    requires webfx.stack.db.query;
    requires webfx.stack.orm.datasourcemodel.service;
    requires webfx.stack.orm.entity;

    // Exported packages
    exports one.modality.ecommerce.document.service.spi.impl.server;

    // Provided services
    provides one.modality.ecommerce.document.service.spi.DocumentServiceProvider with one.modality.ecommerce.document.service.spi.impl.server.ServerDocumentServiceProvider;

}
// File managed by WebFX (DO NOT EDIT MANUALLY)

module mongoose.crm.backoffice.activities.authorizations {

    // Direct dependencies modules
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires mongoose.crm.client.authn;
    requires webfx.extras.visual.controls.grid;
    requires webfx.framework.client.activity;
    requires webfx.framework.client.orm.domainmodel.activity;
    requires webfx.framework.client.orm.entity.controls;
    requires webfx.framework.client.orm.reactive.visual;
    requires webfx.framework.client.uirouter;
    requires webfx.framework.shared.operation;
    requires webfx.framework.shared.orm.dql;
    requires webfx.framework.shared.orm.entity;
    requires webfx.framework.shared.router;
    requires webfx.platform.client.windowhistory;
    requires webfx.platform.shared.util;

    // Exported packages
    exports mongoose.crm.backoffice.activities.authorizations;
    exports mongoose.crm.backoffice.activities.authorizations.routing;
    exports mongoose.crm.backoffice.activities.operations.authorizations;

    // Provided services
    provides dev.webfx.framework.client.operations.route.RouteRequestEmitter with mongoose.crm.backoffice.activities.authorizations.RouteToAuthorizationsRequestEmitter;
    provides dev.webfx.framework.client.ui.uirouter.UiRoute with mongoose.crm.backoffice.activities.authorizations.AuthorizationsUiRoute;

}
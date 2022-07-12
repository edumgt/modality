// File managed by WebFX (DO NOT EDIT MANUALLY)

module modality.ecommerce.backoffice.activities.income {

    // Direct dependencies modules
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires modality.base.backoffice.masterslave;
    requires modality.base.client.activity;
    requires modality.base.client.entities;
    requires modality.base.client.util;
    requires modality.base.shared.entities;
    requires webfx.extras.visual;
    requires webfx.extras.visual.grid;
    requires webfx.platform.util;
    requires webfx.stack.orm.domainmodel;
    requires webfx.stack.orm.domainmodel.activity;
    requires webfx.stack.orm.dql;
    requires webfx.stack.orm.entity.controls;
    requires webfx.stack.orm.reactive.dql;
    requires webfx.stack.orm.reactive.visual;
    requires webfx.stack.platform.windowhistory;
    requires webfx.stack.routing.activity;
    requires webfx.stack.routing.router;
    requires webfx.stack.routing.uirouter;
    requires webfx.stack.ui.operation;
    requires webfx.stack.ui.operation.action;

    // Exported packages
    exports org.modality_project.ecommerce.backoffice.activities.income;
    exports org.modality_project.ecommerce.backoffice.activities.income.routing;
    exports org.modality_project.ecommerce.backoffice.operations.routes.income;

    // Provided services
    provides dev.webfx.stack.routing.uirouter.UiRoute with org.modality_project.ecommerce.backoffice.activities.income.IncomeUiRoute;
    provides dev.webfx.stack.routing.uirouter.operations.RouteRequestEmitter with org.modality_project.ecommerce.backoffice.activities.income.RouteToIncomeRequestEmitter;

}
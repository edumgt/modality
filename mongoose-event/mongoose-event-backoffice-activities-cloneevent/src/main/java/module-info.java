// File managed by WebFX (DO NOT EDIT MANUALLY)

module mongoose.event.backoffice.activities.cloneevent {

    // Direct dependencies modules
    requires java.base;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires mongoose.base.client.activity;
    requires mongoose.base.shared.domainmodel;
    requires mongoose.base.shared.entities;
    requires mongoose.ecommerce.backoffice.activities.bookings;
    requires mongoose.event.backoffice.activities.cloneevent.routing;
    requires webfx.framework.client.activity;
    requires webfx.framework.client.controls;
    requires webfx.framework.client.orm.domainmodel.activity;
    requires webfx.framework.client.uirouter;
    requires webfx.framework.shared.orm.entity;
    requires webfx.kit.util;
    requires webfx.platform.client.uischeduler;
    requires webfx.platform.shared.submit;
    requires webfx.platform.shared.util;

    // Exported packages
    exports mongoose.event.backoffice.activities.cloneevent;

    // Provided services
    provides dev.webfx.framework.client.ui.uirouter.UiRoute with mongoose.event.backoffice.activities.cloneevent.CloneEventUiRoute;

}
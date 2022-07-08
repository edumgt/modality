package org.modality_project.ecommerce.backoffice.activities.payments;

import org.modality_project.ecommerce.backoffice.operations.routes.payments.RouteToPaymentsRequest;
import dev.webfx.framework.client.activity.impl.elementals.uiroute.UiRouteActivityContext;
import dev.webfx.framework.client.operations.route.RouteRequestEmitter;
import dev.webfx.framework.shared.router.auth.authz.RouteRequest;

/**
 * @author Bruno Salmon
 */
public final class RouteToPaymentsRequestEmitter implements RouteRequestEmitter {

    @Override
    public RouteRequest instantiateRouteRequest(UiRouteActivityContext context) {
        return new RouteToPaymentsRequest(context.getParameter("eventId"), context.getHistory());
    }
}

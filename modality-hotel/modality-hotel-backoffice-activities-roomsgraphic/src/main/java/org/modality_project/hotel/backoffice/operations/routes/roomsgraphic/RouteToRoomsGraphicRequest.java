package org.modality_project.hotel.backoffice.operations.routes.roomsgraphic;

import org.modality_project.hotel.backoffice.activities.roomsgraphic.routing.RoomsGraphicRouting;
import dev.webfx.stack.routing.uirouter.operations.RoutePushRequest;
import dev.webfx.stack.ui.operation.HasOperationCode;
import dev.webfx.stack.platform.windowhistory.spi.BrowsingHistory;

/**
 * @author Bruno Salmon
 */
public final class RouteToRoomsGraphicRequest extends RoutePushRequest implements HasOperationCode {

    private final static String OPERATION_CODE = "RouteToRoomsGraphic";

    public RouteToRoomsGraphicRequest(Object eventId, BrowsingHistory history) {
        super(RoomsGraphicRouting.getEventPath(eventId), history);
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }

}

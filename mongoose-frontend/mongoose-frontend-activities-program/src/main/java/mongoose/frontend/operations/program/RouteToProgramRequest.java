package mongoose.frontend.operations.program;

import mongoose.frontend.activities.program.routing.ProgramRouting;
import dev.webfx.framework.client.operations.route.RoutePushRequest;
import dev.webfx.platform.client.services.windowhistory.spi.BrowsingHistory;

/**
 * @author Bruno Salmon
 */
public final class RouteToProgramRequest extends RoutePushRequest {

    public RouteToProgramRequest(Object eventId, BrowsingHistory history) {
        super(ProgramRouting.getProgramPath(eventId), history);
    }

}

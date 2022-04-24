package mongoose.catering.backoffice.operations.entities.allocationrule;

import javafx.scene.layout.Pane;
import mongoose.base.shared.entities.Event;
import dev.webfx.platform.shared.async.Future;

final class AddNewAllocationRuleExecutor {

    static Future<Void> executeRequest(AddNewAllocationRuleRequest rq) {
        return execute(rq.getEvent(), rq.getParentContainer());
    }

    private static Future<Void> execute(Event documentLine, Pane parentContainer) {
        // Not yet implemented
        return Future.succeededFuture();
    }
}
package mongoose.catering.backoffice.operations.entities.allocationrule;

import javafx.scene.layout.Pane;
import dev.webfx.framework.shared.operation.HasOperationCode;
import dev.webfx.framework.shared.operation.HasOperationExecutor;
import dev.webfx.framework.shared.orm.entity.Entity;
import dev.webfx.platform.shared.async.AsyncFunction;

public final class EditAllocationRuleRequest implements HasOperationCode,
        HasOperationExecutor<EditAllocationRuleRequest, Void> {

    private final static String OPERATION_CODE = "EditAllocationRule";

    private final Entity allocationRule;
    private final Pane parentContainer;

    public EditAllocationRuleRequest(Entity allocationRule, Pane parentContainer) {
        this.allocationRule = allocationRule;
        this.parentContainer = parentContainer;
    }

    Entity getAllocationRule() {
        return allocationRule;
    }

    Pane getParentContainer() {
        return parentContainer;
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }

    @Override
    public AsyncFunction<EditAllocationRuleRequest, Void> getOperationExecutor() {
        return EditAllocationRuleExecutor::executeRequest;
    }
}

package one.modality.ecommerce.backoffice.operations.entities.moneyflow;

import dev.webfx.platform.async.AsyncFunction;
import dev.webfx.stack.i18n.HasI18nKey;
import dev.webfx.stack.i18n.I18nKeys;
import dev.webfx.stack.ui.operation.HasOperationCode;
import dev.webfx.stack.ui.operation.HasOperationExecutor;
import javafx.scene.layout.Pane;
import one.modality.base.client.i18n.ModalityI18nKeys;

public final class AddNewMoneyFlowRequest implements HasOperationCode, HasI18nKey,
        HasOperationExecutor<AddNewMoneyFlowRequest, Void> {

    private final static String OPERATION_CODE = "AddNewMoneyFlow";

    private final Pane parentContainer;

    public AddNewMoneyFlowRequest(Pane parentContainer) {
        this.parentContainer = parentContainer;
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }

    @Override
    public Object getI18nKey() {
        return I18nKeys.appendEllipsis(ModalityI18nKeys.Add);
    }


    public Pane getParentContainer() {
        return parentContainer;
    }

    @Override
    public AsyncFunction<AddNewMoneyFlowRequest, Void> getOperationExecutor() {
        return AddNewMoneyFlowExecutor::executeRequest;
    }
}

package mongoose.ecommerce.backoffice.operations.entities.moneytransfer;

import javafx.scene.layout.Pane;
import mongoose.base.shared.entities.MoneyTransfer;
import dev.webfx.framework.shared.operation.HasOperationCode;
import dev.webfx.framework.shared.operation.HasOperationExecutor;
import dev.webfx.platform.shared.async.AsyncFunction;

public final class EditPaymentRequest implements HasOperationCode,
        HasOperationExecutor<EditPaymentRequest, Void> {

    private final static String OPERATION_CODE = "EditPayment";

    private final MoneyTransfer payment;
    private final Pane parentContainer;

    public EditPaymentRequest(MoneyTransfer payment, Pane parentContainer) {
        this.payment = payment;
        this.parentContainer = parentContainer;
    }

    MoneyTransfer getPayment() {
        return payment;
    }

    Pane getParentContainer() {
        return parentContainer;
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }

    @Override
    public AsyncFunction<EditPaymentRequest, Void> getOperationExecutor() {
        return EditPaymentExecutor::executeRequest;
    }
}

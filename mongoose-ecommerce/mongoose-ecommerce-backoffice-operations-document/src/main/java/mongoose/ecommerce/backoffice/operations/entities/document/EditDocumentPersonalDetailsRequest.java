package mongoose.ecommerce.backoffice.operations.entities.document;

import javafx.scene.layout.Pane;
import mongoose.base.shared.entities.Document;
import dev.webfx.framework.client.ui.controls.button.ButtonFactoryMixin;
import dev.webfx.framework.shared.operation.HasOperationCode;
import dev.webfx.framework.shared.operation.HasOperationExecutor;
import dev.webfx.platform.shared.async.AsyncFunction;

public final class EditDocumentPersonalDetailsRequest implements HasOperationCode,
        HasOperationExecutor<EditDocumentPersonalDetailsRequest, Void> {

    private final static String OPERATION_CODE = "EditDocumentPersonalDetails";

    private final Document document;
    private final ButtonFactoryMixin buttonFactoryMixin;
    private final Pane parentContainer;

    public EditDocumentPersonalDetailsRequest(Document document, ButtonFactoryMixin buttonFactoryMixin, Pane parentContainer) {
        this.document = document;
        this.buttonFactoryMixin = buttonFactoryMixin;
        this.parentContainer = parentContainer;
    }

    Document getDocument() {
        return document;
    }

    public ButtonFactoryMixin getButtonFactoryMixin() {
        return buttonFactoryMixin;
    }

    Pane getParentContainer() {
        return parentContainer;
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }

    @Override
    public AsyncFunction<EditDocumentPersonalDetailsRequest, Void> getOperationExecutor() {
        return EditDocumentPersonalDetailsExecutor::executeRequest;
    }
}

package mongoose.ecommerce.backoffice.operations.entities.documentline;

import javafx.scene.layout.Pane;
import mongoose.base.shared.entities.DocumentLine;
import dev.webfx.framework.shared.operation.HasOperationCode;
import dev.webfx.framework.shared.operation.HasOperationExecutor;
import dev.webfx.platform.shared.async.AsyncFunction;

public final class EditDocumentLineRequest implements HasOperationCode,
        HasOperationExecutor<EditDocumentLineRequest, Void> {

    private final static String OPERATION_CODE = "EditDocumentLine";

    private final DocumentLine documentLine;
    private final Pane parentContainer;

    public EditDocumentLineRequest(DocumentLine documentLine, Pane parentContainer) {
        this.documentLine = documentLine;
        this.parentContainer = parentContainer;
    }

    DocumentLine getDocumentLine() {
        return documentLine;
    }

    Pane getParentContainer() {
        return parentContainer;
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }

    @Override
    public AsyncFunction<EditDocumentLineRequest, Void> getOperationExecutor() {
        return EditDocumentLineExecutor::executeRequest;
    }
}

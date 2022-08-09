package org.modality_project.ecommerce.backoffice.operations.entities.document;

import javafx.scene.layout.Pane;
import org.modality_project.base.shared.entities.Document;
import dev.webfx.stack.ui.operation.HasOperationCode;
import dev.webfx.stack.ui.operation.HasOperationExecutor;
import dev.webfx.platform.async.AsyncFunction;

public final class SendLetterRequest implements HasOperationCode,
        HasOperationExecutor<SendLetterRequest, Void> {

    private final static String OPERATION_CODE = "SendLetter";

    private final Document document;
    private final Pane parentContainer;

    public SendLetterRequest(Document document, Pane parentContainer) {
        this.document = document;
        this.parentContainer = parentContainer;
    }

    Document getDocument() {
        return document;
    }

    Pane getParentContainer() {
        return parentContainer;
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }

    @Override
    public AsyncFunction<SendLetterRequest, Void> getOperationExecutor() {
        return SendLetterExecutor::executeRequest;
    }
}

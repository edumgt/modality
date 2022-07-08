package org.modality_project.ecommerce.backoffice.operations.entities.document.multiplebookings;

import javafx.scene.layout.Pane;
import org.modality_project.base.backoffice.operations.entities.generic.SetEntityFieldRequest;
import org.modality_project.base.shared.entities.Document;

public final class CancelOtherMultipleBookingsRequest extends SetEntityFieldRequest {

    private final static String OPERATION_CODE = "CancelOtherMultipleBookings";

    public CancelOtherMultipleBookingsRequest(Document document, Pane parentContainer) {
        super(document, "triggerCancelOtherMultipleBookings", "true", "Please confirm", parentContainer);
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }
}

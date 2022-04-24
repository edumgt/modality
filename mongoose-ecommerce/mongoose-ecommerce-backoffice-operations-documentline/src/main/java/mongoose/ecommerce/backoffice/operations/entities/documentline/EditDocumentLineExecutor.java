package mongoose.ecommerce.backoffice.operations.entities.documentline;

import javafx.scene.layout.Pane;
import mongoose.base.shared.entities.DocumentLine;
import dev.webfx.framework.client.ui.controls.entity.sheet.EntityPropertiesSheet;
import dev.webfx.platform.shared.async.Future;

final class EditDocumentLineExecutor {

    static Future<Void> executeRequest(EditDocumentLineRequest rq) {
        return execute(rq.getDocumentLine(), rq.getParentContainer());
    }

    private static Future<Void> execute(DocumentLine documentLine, Pane parentContainer) {
        EntityPropertiesSheet.editEntity(documentLine,
                "[" +
                        "'site'," +
                        "'item'," +
                        "'price_isCustom'," +
                        "'price_custom'," +
                        "'price_discount'," +
                        "'share_owner'," +
                        "'share_owner_quantity'," +
                        "'share_mate'," +
                        "'share_mate_ownerName','share_mate_ownerDocumentLine','share_owner_mate1Name','share_owner_mate2Name','share_owner_mate3Name','share_owner_mate4Name','share_owner_mate5Name','share_owner_mate6Name','share_owner_mate7Name'," +
                         "'allocate'," +
                        //"'resourceConfiguration'," + // Buggy for now
                        "'lockAllocation'," +
                        "'comment'," +
                        "'cancelled'," +
                        "'abandoned'," +
                        "'cancellationDate'," +
                        "'backend_released'," +
                        "'frontend_released'," +
                        "'read'" +
                        "]",
                parentContainer);
        return Future.succeededFuture();
    }
}

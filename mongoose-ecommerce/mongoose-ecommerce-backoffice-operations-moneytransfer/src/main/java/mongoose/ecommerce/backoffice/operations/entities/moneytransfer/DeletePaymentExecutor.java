package mongoose.ecommerce.backoffice.operations.entities.moneytransfer;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import mongoose.base.shared.entities.MoneyTransfer;
import dev.webfx.framework.client.ui.controls.dialog.DialogContent;
import dev.webfx.framework.client.ui.controls.dialog.DialogUtil;
import dev.webfx.framework.shared.orm.entity.UpdateStore;
import dev.webfx.platform.shared.util.async.Future;

final class DeletePaymentExecutor {

    static Future<Void> executeRequest(DeletePaymentRequest rq) {
        return execute(rq.getPayment(), rq.getParentContainer());
    }

    private static Future<Void> execute(MoneyTransfer payment, Pane parentContainer) {
        Future<Void> future = Future.future();
        DialogContent dialogContent = new DialogContent().setContent(new Text("Are you sure you want to delete this payment?"));
        DialogUtil.showModalNodeInGoldLayout(dialogContent, parentContainer).addCloseHook(future::complete);
        DialogUtil.armDialogContentButtons(dialogContent, dialogCallback -> {
            UpdateStore updateStore = UpdateStore.create(payment.getStore().getDataSourceModel());
            updateStore.deleteEntity(payment);
            updateStore.submitChanges().setHandler(ar -> {
                if (ar.failed())
                    dialogCallback.showException(ar.cause());
                else
                    dialogCallback.closeDialog();
            });
        });
        return future;
    }
}
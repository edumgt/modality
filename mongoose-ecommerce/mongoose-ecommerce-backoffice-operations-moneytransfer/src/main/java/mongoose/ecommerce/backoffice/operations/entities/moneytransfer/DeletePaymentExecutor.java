package mongoose.ecommerce.backoffice.operations.entities.moneytransfer;

import dev.webfx.framework.client.ui.controls.dialog.DialogContent;
import dev.webfx.framework.client.ui.controls.dialog.DialogUtil;
import dev.webfx.framework.shared.orm.entity.UpdateStore;
import dev.webfx.platform.shared.async.Future;
import dev.webfx.platform.shared.async.Promise;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import mongoose.base.shared.entities.MoneyTransfer;

final class DeletePaymentExecutor {

    static Future<Void> executeRequest(DeletePaymentRequest rq) {
        return execute(rq.getPayment(), rq.getParentContainer());
    }

    private static Future<Void> execute(MoneyTransfer payment, Pane parentContainer) {
        Promise<Void> promise = Promise.promise();
        DialogContent dialogContent = new DialogContent().setContent(new Text("Are you sure you want to delete this payment?"));
        DialogUtil.showModalNodeInGoldLayout(dialogContent, parentContainer).addCloseHook(promise::complete);
        DialogUtil.armDialogContentButtons(dialogContent, dialogCallback -> {
            UpdateStore updateStore = UpdateStore.create(payment.getStore().getDataSourceModel());
            updateStore.deleteEntity(payment);
            updateStore.submitChanges()
                    .onFailure(dialogCallback::showException)
                    .onSuccess(resultBatch -> dialogCallback.closeDialog());
        });
        return promise.future();
    }
}

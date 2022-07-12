package org.modality_project.ecommerce.backoffice.operations.entities.document;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.modality_project.crm.client.controls.personaldetails.PersonalDetailsPanel;
import org.modality_project.base.shared.entities.Document;
import dev.webfx.stack.ui.controls.button.ButtonFactoryMixin;
import dev.webfx.stack.ui.controls.dialog.DialogContent;
import dev.webfx.stack.ui.controls.dialog.DialogUtil;
import dev.webfx.stack.async.Future;

final class EditDocumentPersonalDetailsExecutor {

    static Future<Void> executeRequest(EditDocumentPersonalDetailsRequest rq) {
        return execute(rq.getDocument(), rq.getButtonFactoryMixin(), rq.getParentContainer());
    }

    private static Future<Void> execute(Document document, ButtonFactoryMixin buttonFactoryMixin, Pane parentContainer) {
        PersonalDetailsPanel details = new PersonalDetailsPanel(document.getEvent(), buttonFactoryMixin, parentContainer);
        details.setEditable(true);
        details.syncUiFromModel(document);
        BorderPane sectionPanel = details.getSectionPanel();
        ScrollPane scrollPane = new ScrollPane(sectionPanel);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sectionPanel.setPrefWidth(400);
        scrollPane.setPrefWidth(400);
        scrollPane.setPrefHeight(600);
        //scrollPane.setFitToWidth(true);
        DialogContent dialogContent = new DialogContent().setContent(scrollPane);
        DialogUtil.showModalNodeInGoldLayout(dialogContent, parentContainer, 0, 0.9);
        DialogUtil.armDialogContentButtons(dialogContent, dialogCallback -> {
            details.isValid();
            //dialogCallback.closeDialog();
            /*
            syncModelFromUi();
            if (!updateStore.hasChanges())
                dialogCallback.closeDialog();
            else {
                updateStore.executeUpdate().setHandler(ar -> {
                    if (ar.failed())
                        dialogCallback.showException(ar.cause());
                    else
                        dialogCallback.closeDialog();
                });
            }
*/
        });
        return Future.succeededFuture();
    }
}

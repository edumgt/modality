package one.modality.crm.backoffice.activities.organizations;

import dev.webfx.stack.i18n.I18n;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import one.modality.base.client.activity.table.GenericTablePresentationViewActivity;

/**
 * @author Bruno Salmon
 */
final class OrganizationsPresentationViewActivity extends GenericTablePresentationViewActivity<OrganizationsPresentationModel> {

    private CheckBox withEventsCheckBox;

    @Override
    protected void createViewNodes(OrganizationsPresentationModel pm) {
        super.createViewNodes(pm);

        I18n.bindI18nProperties(searchBox, "YourCentre"); // Will translate the prompt

        withEventsCheckBox = newCheckBox("WithEvents");

        // Initialization from the presentation model current state
        withEventsCheckBox.setSelected(pm.withEventsProperty().getValue());

        // Binding the UI with the presentation model for further state changes
        // User inputs: the UI state changes are transferred in the presentation model
        pm.withEventsProperty().bind(withEventsCheckBox.selectedProperty());
    }

    @Override
    protected Node assemblyViewNodes() {
        return new BorderPane(table, searchBox, null, new HBox(10, withEventsCheckBox, limitCheckBox), null);
    }

}

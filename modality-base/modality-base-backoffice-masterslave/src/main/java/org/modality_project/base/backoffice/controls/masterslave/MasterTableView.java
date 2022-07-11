package org.modality_project.base.backoffice.controls.masterslave;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import dev.webfx.stack.framework.client.orm.reactive.dql.statement.conventions.HasLimitProperty;
import dev.webfx.stack.framework.client.orm.reactive.mapping.entities_to_visual.conventions.HasMasterVisualResultProperty;
import dev.webfx.stack.framework.client.orm.reactive.mapping.entities_to_visual.conventions.HasMasterVisualSelectionProperty;
import dev.webfx.stack.framework.client.ui.controls.ControlFactoryMixin;
import dev.webfx.extras.visual.controls.grid.VisualGrid;
import dev.webfx.kit.util.properties.Properties;

public final class MasterTableView implements UiBuilder {

    private final VisualGrid masterTable = new VisualGrid();
    private CheckBox masterLimitCheckBox;

    private MasterTableView(HasMasterVisualResultProperty pm, ControlFactoryMixin mixin) {
        masterTable.visualResultProperty().bind(pm.masterVisualResultProperty());
        if (pm instanceof HasMasterVisualSelectionProperty)
            ((HasMasterVisualSelectionProperty) pm).masterVisualSelectionProperty().bindBidirectional(masterTable.visualSelectionProperty());
        if (pm instanceof HasLimitProperty) {
            masterLimitCheckBox = mixin.newCheckBox("LimitTo100");
            masterLimitCheckBox.setSelected(true);
            Properties.runNowAndOnPropertiesChange(() -> ((HasLimitProperty) pm).limitProperty().setValue(masterLimitCheckBox.isSelected() ? 30 : -1), masterLimitCheckBox.selectedProperty());
            masterTable.fullHeightProperty().bind(masterLimitCheckBox.selectedProperty());
        }
    }

    @Override
    public Node buildUi() {
        if (masterLimitCheckBox == null)
            return masterTable;
        BorderPane.setAlignment(masterTable, Pos.TOP_CENTER);
        return new BorderPane(masterTable, null, null, masterLimitCheckBox, null);
    }

    public static MasterTableView createAndBind(HasMasterVisualResultProperty pm, ControlFactoryMixin mixin) {
        return new MasterTableView(pm, mixin);
    }
}

package org.modality_project.crm.backoffice.activities.authorizations;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import org.modality_project.crm.client.services.authn.ModalityUserPrincipal;
import dev.webfx.extras.visual.controls.grid.VisualGrid;
import dev.webfx.framework.client.activity.impl.combinations.viewdomain.impl.ViewDomainActivityBase;
import dev.webfx.framework.client.orm.reactive.mapping.entities_to_visual.ReactiveVisualMapper;
import dev.webfx.framework.client.ui.controls.entity.sheet.EntityPropertiesSheet;
import dev.webfx.framework.shared.orm.entity.Entity;

import static dev.webfx.framework.shared.orm.dql.DqlStatement.where;

/**
 * @author Bruno Salmon
 */
final class AuthorizationsViewActivity extends ViewDomainActivityBase {

    private final String manageeColumns = "[{label: 'Managee', expression: `active,user.genderIcon,user.firstName,user.lastName`}]";
    private final String assignmentColumns = "[`active`,`operation`,{expression: `rule`, foreignColumns: null, foreignSearchCondition: null, foreignWhere: null},`activityState`]";

    private final VisualGrid usersGrid = new VisualGrid();
    private final VisualGrid assignmentsGrid = new VisualGrid();

    private final ObjectProperty<Entity> selectedManagementProperty = new SimpleObjectProperty<>();

    @Override
    public Node buildUi() {
        assignmentsGrid.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
                EntityPropertiesSheet.editEntity(assignmentVisualMapper.getSelectedEntity(), assignmentColumns, (Pane) getNode().getParent());
        });
        return new SplitPane(usersGrid, assignmentsGrid);
    }


    private ReactiveVisualMapper<Entity> assignmentVisualMapper;

    protected void startLogic() {

        ReactiveVisualMapper.createReactiveChain(this)
                .always("{class: 'AuthorizationManagement', orderBy: 'id'}")
                .ifNotNullOtherwiseEmpty(userPrincipalProperty(), principal -> where("manager=?", ModalityUserPrincipal.getUserPersonId(principal)))
                .setEntityColumns(manageeColumns)
                .visualizeResultInto(usersGrid)
                .setSelectedEntityHandler(selectedManagementProperty::setValue)
                .start();

        assignmentVisualMapper = ReactiveVisualMapper.createReactiveChain(this)
                .always("{class: 'AuthorizationAssignment', orderBy: 'id'}")
                .ifNotNullOtherwiseEmpty(selectedManagementProperty, management -> where("management=?", management))
                .setEntityColumns(assignmentColumns)
                .visualizeResultInto(assignmentsGrid)
                .start();
    }
}
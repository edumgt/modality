package org.modality_project.base.client.activity.eventdependent;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.modality_project.base.client.activity.organizationdependent.OrganizationDependentGenericTablePresentationModel;

/**
 * @author Bruno Salmon
 */
public class EventDependentGenericTablePresentationModel
        extends OrganizationDependentGenericTablePresentationModel
        implements EventDependentPresentationModel {

    private final ObjectProperty<Object> eventIdProperty = new SimpleObjectProperty<>();

    public ObjectProperty<Object> eventIdProperty() {
        return this.eventIdProperty;
    }

}


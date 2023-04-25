package one.modality.event.backoffice.events.pm;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import one.modality.base.client.activity.organizationdependent.OrganizationDependentGenericTablePresentationModel;
import one.modality.base.client.presentationmodel.HasTimeWindowProperties;

import java.time.LocalDate;

/**
 * @author Bruno Salmon
 */
public final class EventsPresentationModel extends OrganizationDependentGenericTablePresentationModel
    implements HasTimeWindowProperties<LocalDate> {

    // Display input

    private final BooleanProperty withBookingsProperty = new SimpleBooleanProperty(true); // Limit initially set to true
    public BooleanProperty withBookingsProperty() { return withBookingsProperty; }

    private final ObjectProperty<LocalDate> timeWindowStartProperty = new SimpleObjectProperty<>(LocalDate.now().minusWeeks(1));
    public ObjectProperty<LocalDate> timeWindowStartProperty() { return timeWindowStartProperty; }

    private final ObjectProperty<LocalDate> timeWindowEndProperty = new SimpleObjectProperty<>(LocalDate.now().plusWeeks(3));
    public ObjectProperty<LocalDate> timeWindowEndProperty() { return timeWindowEndProperty; }

}

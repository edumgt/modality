package one.modality.event.client.event.fx;

import dev.webfx.stack.orm.entity.Entities;
import dev.webfx.stack.orm.entity.EntityId;
import dev.webfx.stack.orm.entity.EntityStore;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import one.modality.base.shared.entities.Event;
import one.modality.crm.backoffice.organization.fx.FXOrganization;

import java.util.Objects;

/**
 * @author Bruno Salmon
 */
public final class FXEvent {

    public static final String EXPECTED_FIELDS = "icon,name,startDate,endDate,organization.(" + FXOrganization.EXPECTED_FIELDS + ")";

    private final static ObjectProperty<Event> eventProperty = new SimpleObjectProperty<>() {
        @Override
        protected void invalidated() {
            FXEventId.setEventId(getEventId());
            if (get() != null) {
                lastNonNullEventProperty.set(get());
            }
        }
    };

    private final static ObjectProperty<Event> lastNonNullEventProperty = new SimpleObjectProperty<>();

    static {
        FXEventId.init();
    }

    static EntityId getEventId() {
        return Entities.getId(getEvent());
    }

    static EntityStore getEventStore() {
        Event event = getEvent();
        return event != null ? event.getStore() : FXOrganization.getOrganizationStore();
    }

    public static ObjectProperty<Event> eventProperty() {
        return eventProperty;
    }

    public static Event getEvent() {
        return eventProperty.get();
    }

    public static void setEvent(Event event) {
        if (!Objects.equals(event, getEvent()))
            eventProperty.set(event);
    }

    public static ObjectProperty<Event> lastNonNullEventProperty() {
        return lastNonNullEventProperty;
    }

}

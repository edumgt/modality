package one.modality.crm.backoffice.organization.fx;

import dev.webfx.kit.util.properties.FXProperties;
import dev.webfx.platform.console.Console;
import dev.webfx.platform.uischeduler.UiScheduler;
import dev.webfx.stack.authn.login.ui.FXLoginContext;
import dev.webfx.stack.orm.entity.Entities;
import dev.webfx.stack.orm.entity.EntityId;
import dev.webfx.stack.orm.entity.EntityStore;
import dev.webfx.stack.session.Session;
import dev.webfx.stack.session.SessionService;
import dev.webfx.stack.session.state.client.fx.FXSession;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import one.modality.base.shared.context.ModalityContext;
import one.modality.base.shared.entities.Organization;

import java.util.Objects;

/**
 * @author Bruno Salmon
 */
public final class FXOrganizationId {

    private static final String SESSION_ORGANIZATION_ID_KEY = "fxOrganizationId";

    private final static ObjectProperty<EntityId> organizationIdProperty = new SimpleObjectProperty<>() {
        @Override
        protected void invalidated() {
            // Getting the organizationId that just changed
            EntityId organizationId = getOrganizationId();
            // Storing this new value (more precisely the primary key) in the session, and save it
            Session session = FXSession.getSession();
            if (session != null) {
                session.put(SESSION_ORGANIZATION_ID_KEY, Entities.getPrimaryKey(organizationId));
                SessionService.getSessionStore().put(session);
            }
            // Also resetting the FXLoginContext
            FXLoginContext.setLoginContext(new ModalityContext(Entities.getPrimaryKey(organizationId), null, null, null));
            // Synchronizing FXOrganization to match that new organization id (FXOrganizationId => FXOrganization)
            if (!Objects.equals(organizationId, FXOrganization.getOrganizationId())) { // Sync only if ids differ.
                // If the new organization id is null, we set the FXOrganization to null
                if (Entities.getPrimaryKey(organizationId) == null)
                    FXOrganization.setOrganization(null);
                else {
                    // Getting the organization store
                    EntityStore organizationStore = FXOrganization.getOrganizationStore();
                    // Checking if we can find the organization in memory in that store
                    Organization organization = organizationStore.getEntity(organizationId);
                    // If yes, there is no need to request the server, we use directly that instance
                    if (organization != null)
                        FXOrganization.setOrganization(organization);
                    else { // Otherwise, we request the server to load that organization from that id
                        organizationStore.<Organization>executeQuery("select " + FXOrganization.EXPECTED_FIELDS + " from Organization where id=?", organizationId)
                            .onFailure(Console::log)
                            .onSuccess(list -> // on successfully receiving the list (should be a singleton list)
                                UiScheduler.runInUiThread(() -> {
                                    if (Objects.equals(organizationId, getOrganizationId())) { // final check it is still relevant
                                        Organization loadedOrganization = list.isEmpty() ? null : list.get(0);
                                        FXOrganization.setOrganization(loadedOrganization); // we finally set FXEvent
                                    }
                                }));
                    }
                }
            }
        }
    };

    static {
        // Initializing the organizationId from the last value stored in the session
        FXProperties.runNowAndOnPropertiesChange(() -> {
            Session session = FXSession.getSession();
            Object primaryKey = session == null ? null : session.get(SESSION_ORGANIZATION_ID_KEY);
            setOrganizationId(primaryKey == null ? null : EntityId.create(Organization.class, primaryKey));
        }, FXSession.sessionProperty());
    }

    static void init() {
        // Do nothing, but this ensures that the static initializer above has been called
    }

    public static ObjectProperty<EntityId> organizationIdProperty() {
        return organizationIdProperty;
    }

    public static EntityId getOrganizationId() {
        return organizationIdProperty.get();
    }

    public static void setOrganizationId(EntityId organizationId) {
        if (!Objects.equals(organizationId, getOrganizationId()))
            organizationIdProperty.set(organizationId);
    }

}

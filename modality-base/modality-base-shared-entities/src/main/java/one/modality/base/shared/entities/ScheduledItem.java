package one.modality.base.shared.entities;

import dev.webfx.stack.orm.entity.Entity;
import one.modality.base.shared.entities.markers.*;

/**
 * @author Bruno Salmon
 */
public interface ScheduledItem extends Entity,
        EntityHasEvent,
        EntityHasLocalDate,
        EntityHasSiteAndItem,
        EntityHasStartAndEndTime {

}

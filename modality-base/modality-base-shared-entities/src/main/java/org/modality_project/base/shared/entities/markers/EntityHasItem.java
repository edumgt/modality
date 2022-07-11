package org.modality_project.base.shared.entities.markers;

import org.modality_project.base.shared.entities.Item;
import dev.webfx.stack.framework.shared.orm.entity.Entity;
import dev.webfx.stack.framework.shared.orm.entity.EntityId;

/**
 * @author Bruno Salmon
 */
public interface EntityHasItem extends Entity, HasItem {

    @Override
    default void setItem(Object item) {
        setForeignField("item", item);
    }

    @Override
    default EntityId getItemId() {
        return getForeignEntityId("item");
    }

    @Override
    default Item getItem() {
        return getForeignEntity("item");
    }
}

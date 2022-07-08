package org.modality_project.base.shared.entities;

import dev.webfx.framework.shared.orm.entity.Entity;

/**
 * @author Bruno Salmon
 */
public interface Cart extends Entity {

    default void setUuid(String uuid) {
        setFieldValue("uuid", uuid);
    }

    default String getUuid() {
        return getStringFieldValue("uuid");
    }
    
}

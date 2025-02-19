package one.modality.base.shared.entities.markers;

import dev.webfx.stack.orm.entity.Entity;

/**
 * @author Bruno Salmon
 */
public interface EntityHasIcon extends Entity, HasIcon {

    String icon = "icon";

    @Override
    default String getIcon() {
        return evaluate(icon);
    }
}
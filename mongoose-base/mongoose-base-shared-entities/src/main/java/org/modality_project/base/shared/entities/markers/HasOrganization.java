package org.modality_project.base.shared.entities.markers;

import org.modality_project.base.shared.entities.Organization;
import dev.webfx.framework.shared.orm.entity.EntityId;

/**
 * @author Bruno Salmon
 */
public interface HasOrganization {

    void setOrganization(Object organization);

    EntityId getOrganizationId();

    Organization getOrganization();

}
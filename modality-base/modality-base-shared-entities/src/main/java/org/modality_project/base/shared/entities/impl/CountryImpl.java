package org.modality_project.base.shared.entities.impl;

import org.modality_project.base.shared.entities.Country;
import dev.webfx.framework.shared.orm.entity.EntityId;
import dev.webfx.framework.shared.orm.entity.EntityStore;
import dev.webfx.framework.shared.orm.entity.impl.DynamicEntity;
import dev.webfx.framework.shared.orm.entity.impl.EntityFactoryProviderImpl;

/**
 * @author Bruno Salmon
 */
public final class CountryImpl extends DynamicEntity implements Country {

    public CountryImpl(EntityId id, EntityStore store) {
        super(id, store);
    }

    public static final class ProvidedFactory extends EntityFactoryProviderImpl<Country> {
        public ProvidedFactory() {
            super(Country.class, CountryImpl::new);
        }
    }
}

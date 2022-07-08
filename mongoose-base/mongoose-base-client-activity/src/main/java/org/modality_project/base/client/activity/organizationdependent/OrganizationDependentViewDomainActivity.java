package org.modality_project.base.client.activity.organizationdependent;

import dev.webfx.framework.client.activity.impl.combinations.viewdomain.impl.ViewDomainActivityBase;
import dev.webfx.framework.client.activity.impl.combinations.viewdomain.impl.ViewDomainActivityContextFinal;
import org.modality_project.base.client.activity.MongooseButtonFactoryMixin;
import org.modality_project.base.client.activity.eventdependent.EventDependentPresentationModelImpl;

/**
 * @author Bruno Salmon
 */
public abstract class OrganizationDependentViewDomainActivity
        extends ViewDomainActivityBase
        implements OrganizationDependentActivityMixin<ViewDomainActivityContextFinal>,
        MongooseButtonFactoryMixin {

    protected OrganizationDependentPresentationModel pm;

    @Override
    public OrganizationDependentPresentationModel getPresentationModel() {
        if (pm == null)
            pm = new EventDependentPresentationModelImpl();
        return pm;
    }

    @Override
    protected void updateModelFromContextParameters() {
        updateOrganizationDependentPresentationModelFromContextParameters();
        super.updateModelFromContextParameters();
    }
}

package org.modality_project.base.backoffice.activities.filters;

import org.modality_project.base.backoffice.activities.filters.routing.FiltersRouting;
import dev.webfx.stack.framework.client.activity.impl.combinations.viewdomain.impl.ViewDomainActivityContextFinal;
import dev.webfx.stack.framework.client.ui.uirouter.UiRoute;
import dev.webfx.stack.framework.client.ui.uirouter.impl.UiRouteImpl;
import dev.webfx.stack.framework.shared.router.util.PathBuilder;

public final class FiltersUiRoute extends UiRouteImpl {

    public FiltersUiRoute() {
        super(uiRoute());
    }

    public static UiRoute<?> uiRoute() {
        return UiRoute.createRegex(PathBuilder.toRegexPath(FiltersRouting.getPath())
                , false
                , FiltersActivity::new
                , ViewDomainActivityContextFinal::new
        );
    }
}
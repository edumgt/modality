package mongoose.event.backoffice.activities.cloneevent;

import mongoose.ecommerce.backoffice.operations.routes.bookings.RouteToBookingsRequest;
import mongoose.base.client.activity.eventdependent.EventDependentPresentationLogicActivity;
import mongoose.base.shared.entities.Event;
import dev.webfx.kit.util.properties.Properties;
import dev.webfx.platform.client.services.uischeduler.UiScheduler;
import dev.webfx.platform.shared.services.submit.SubmitArgument;
import dev.webfx.platform.shared.services.submit.SubmitService;

import java.time.LocalDate;

/**
 * @author Bruno Salmon
 */
public final class CloneEventPresentationLogicActivity extends EventDependentPresentationLogicActivity<CloneEventPresentationModel> {

    public CloneEventPresentationLogicActivity() {
        super(CloneEventPresentationModel::new);
    }

    @Override
    protected void startLogic(CloneEventPresentationModel pm) {
        // Load and display fees groups now but also on event change
        Properties.runNowAndOnPropertiesChange(() -> {
            pm.setName(null);
            pm.setDate(null);
            onEventOptions().onSuccess(options -> {
                Event event = getEvent();
                pm.setName(event.getName());
                pm.setDate(event.getStartDate());
            });
        }, pm.eventIdProperty());

        pm.setOnSubmit(event -> {
            LocalDate startDate = pm.getDate();
            SubmitService.executeSubmit(SubmitArgument.builder()
                    .setStatement("select copy_event(?,?,?)")
                    .setParameters(getEventId(), pm.getName(), startDate)
                    .setReturnGeneratedKeys(true)
                    .setDataSourceId(getDataSourceId())
                    .build())
                    .onSuccess(result ->
                        UiScheduler.runInUiThread(() ->
                            new RouteToBookingsRequest(result.getGeneratedKeys()[0], getHistory()).execute()
                    ));
        });
    }
}

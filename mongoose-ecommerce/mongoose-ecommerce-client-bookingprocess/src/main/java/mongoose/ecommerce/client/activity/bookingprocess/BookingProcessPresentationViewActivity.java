package mongoose.ecommerce.client.activity.bookingprocess;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import mongoose.base.client.activity.MongooseButtonFactoryMixin;
import mongoose.base.client.aggregates.event.EventAggregate;
import mongoose.base.shared.entities.Event;
import dev.webfx.framework.client.activity.impl.elementals.presentation.view.impl.PresentationViewActivityImpl;
import dev.webfx.framework.client.ui.util.background.BackgroundFactory;
import dev.webfx.framework.shared.services.datasourcemodel.DataSourceModelService;
import dev.webfx.kit.util.properties.Properties;
import dev.webfx.platform.shared.util.Strings;

/**
 * @author Bruno Salmon
 */
public abstract class BookingProcessPresentationViewActivity<PM extends BookingProcessPresentationModel>
        extends PresentationViewActivityImpl<PM>
        implements MongooseButtonFactoryMixin {

    protected Button backButton;
    protected Button nextButton;

    @Override
    protected void createViewNodes(PM pm) {
        if (backButton == null)
            backButton = newTransparentButton("<<Back");
        if (nextButton == null)
            nextButton = newLargeGreenButton("Next>>");
        backButton.onActionProperty().bind(pm.onPreviousActionProperty());
        nextButton.onActionProperty().bind(pm.onNextActionProperty());
    }

    @Override
    protected Node assemblyViewNodes() {
        return new BorderPane(null, null, null, new HBox(backButton, nextButton), null);
    }

    @Override
    protected Node styleUi(Node uiNode, PM pm) {
        if (uiNode instanceof Region)
            Properties.runNowAndOnPropertiesChange(() -> EventAggregate.getOrCreate(pm.getEventId(), DataSourceModelService.getDefaultDataSourceModel()).onEvent()
                    .onComplete(ar -> {
                        Event event = ar.result();
                        if (event != null) {
                            String css = event.getStringFieldValue("cssClass");
                            if (Strings.startsWith(css, "linear-gradient")) {
                                Background eventBackground = BackgroundFactory.newLinearGradientBackground(css);
                                ((Region) uiNode).setBackground(eventBackground);
                            }
                        }
                    }), pm.eventIdProperty());
        return uiNode;
    }
}

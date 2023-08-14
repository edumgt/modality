package one.modality.event.frontoffice.activities.booking;

import dev.webfx.extras.imagestore.ImageStore;
import dev.webfx.extras.util.layout.LayoutUtil;
import dev.webfx.kit.util.properties.FXProperties;
import dev.webfx.stack.orm.domainmodel.activity.viewdomain.impl.ViewDomainActivityBase;
import dev.webfx.stack.orm.reactive.entities.entities_to_objects.IndividualEntityToObjectMapper;
import dev.webfx.stack.orm.reactive.entities.entities_to_objects.ReactiveObjectsMapper;
import dev.webfx.stack.ui.controls.button.ButtonFactoryMixin;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import one.modality.base.frontoffice.fx.FXBooking;
import one.modality.base.frontoffice.utility.GeneralUtility;
import one.modality.base.frontoffice.utility.StyleUtility;
import one.modality.base.shared.entities.Event;
import one.modality.event.frontoffice.activities.booking.views.CenterDisplayView;
import one.modality.event.frontoffice.activities.booking.views.EventView;
import one.modality.event.frontoffice.activities.booking.views.SearchBarView;

import static dev.webfx.stack.orm.dql.DqlStatement.where;

public final class BookingActivity extends ViewDomainActivityBase implements ButtonFactoryMixin {
    private final VBox internationalEventsContainer = new VBox(20);
    private final VBox localEventsContainer = new VBox(20);

    @Override
    public Node buildUi() {
        Label headerLabel = GeneralUtility.getMainHeaderLabel("YOUR NEXT MEANINGFUL EVENT IS HERE");
        headerLabel.setTextAlignment(TextAlignment.CENTER);

        ImageView headerImageView = ImageStore.createImageView("https://kadampafestivals.org/wp-content/uploads/2022/08/Puja_0028.jpg");
        headerImageView.setPreserveRatio(true);

        Label internationalEventsLabel = GeneralUtility.createLabel("International Festivals", Color.web(StyleUtility.VICTOR_BATTLE_BLACK), 16);

        Node centerDisplay = new CenterDisplayView().getView( this, this);

        Label localEventsLabel = GeneralUtility.createLabel("Local Events", Color.web(StyleUtility.VICTOR_BATTLE_BLACK), 16);

        Node searchBar = new SearchBarView().getView();

        VBox.setMargin(headerLabel, new Insets(5, 0, 5, 0));
        VBox.setMargin(internationalEventsLabel, new Insets(20));
        VBox.setMargin(centerDisplay, new Insets(10, 0, 25, 0));
        VBox.setMargin(searchBar, new Insets(25));

        VBox container = new VBox(
                headerLabel,
                headerImageView,
                internationalEventsLabel,
                internationalEventsContainer,
                centerDisplay,
                localEventsLabel,
                searchBar,
                localEventsContainer);
        container.setAlignment(Pos.CENTER);
        container.setBackground(Background.fill(Color.WHITE));

        FXProperties.runOnPropertiesChange(() -> {
            double width = container.getWidth();
            boolean fillWidth = width <= 600;
            headerImageView.setFitWidth(fillWidth ? width : 0);
            headerImageView.setFitHeight(fillWidth ? 0 : 600);

            GeneralUtility.screenChangeListened(container.getWidth());
        }, container.widthProperty());

        return LayoutUtil.createVerticalScrollPane(container);
    }

    protected void startLogic() {
        // Loading NKT Festivals
        ReactiveObjectsMapper.<Event, Node>createPushReactiveChain(this)
                .always("{class: 'Event', fields:'name, label.<loadAll>, startDate, endDate', where: 'organization.type.code = `CORP` and endDate > now()', orderBy: 'startDate desc, id'}")
                .setIndividualEntityToObjectMapperFactory(IndividualEntityToObjectMapper.createFactory(EventView::new, EventView::setEvent, EventView::getView))
                .storeMappedObjectsInto(internationalEventsContainer.getChildren())
                .start();

        // Loading local events
        ReactiveObjectsMapper.<Event, Node>createPushReactiveChain(this)
                .always("{class: 'Event', fields:'name, label.<loadAll>, startDate, endDate', where: 'endDate > now()', orderBy: 'startDate'}")
                .ifNotNullOtherwiseEmpty(FXBooking.displayCenterProperty, localCenter -> where("organization=?", localCenter))
                .setIndividualEntityToObjectMapperFactory(IndividualEntityToObjectMapper.createFactory(EventView::new, EventView::setEvent, EventView::getView))
                .storeMappedObjectsInto(localEventsContainer.getChildren())
                .start();
    }
}
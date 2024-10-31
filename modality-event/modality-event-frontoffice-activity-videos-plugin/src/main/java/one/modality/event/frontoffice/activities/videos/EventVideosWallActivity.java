package one.modality.event.frontoffice.activities.videos;

import dev.webfx.extras.panes.CenteredPane;
import dev.webfx.extras.panes.CollapsePane;
import dev.webfx.extras.panes.MonoPane;
import dev.webfx.extras.player.video.web.GenericWebVideoPlayer;
import dev.webfx.extras.styles.bootstrap.Bootstrap;
import dev.webfx.kit.util.properties.FXProperties;
import dev.webfx.kit.util.properties.ObservableLists;
import dev.webfx.platform.console.Console;
import dev.webfx.platform.util.Numbers;
import dev.webfx.platform.util.Strings;
import dev.webfx.platform.util.collection.Collections;
import dev.webfx.platform.util.time.Times;
import dev.webfx.stack.i18n.controls.I18nControls;
import dev.webfx.stack.i18n.spi.impl.I18nSubKey;
import dev.webfx.stack.orm.domainmodel.activity.viewdomain.impl.ViewDomainActivityBase;
import dev.webfx.stack.orm.entity.EntityId;
import dev.webfx.stack.orm.entity.EntityStore;
import dev.webfx.stack.orm.entity.EntityStoreQuery;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import one.modality.base.client.icons.SvgIcons;
import one.modality.base.frontoffice.utility.activity.FrontOfficeActivityUtil;
import one.modality.base.shared.entities.Event;
import one.modality.base.shared.entities.KnownItemFamily;
import one.modality.base.shared.entities.ScheduledItem;
import one.modality.crm.shared.services.authn.fx.FXUserPersonId;
import one.modality.event.client.mediaview.Players;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Bruno Salmon
 */
final class EventVideosWallActivity extends ViewDomainActivityBase {

    static final String VIDEO_SCHEDULED_ITEM_DYNAMIC_BOOLEAN_FIELD_HAS_PUBLISHED_MEDIAS = "hasPublishedMedias";
    private static final double PAGE_TOP_BOTTOM_PADDING = 100;

    private final ObjectProperty<Object> pathEventIdProperty = new SimpleObjectProperty<>();

    private final ObjectProperty<Event> eventProperty = new SimpleObjectProperty<>();
    private final ObservableList<ScheduledItem> videoScheduledItems = FXCollections.observableArrayList();

    private final VBox livestreamVBox = new VBox(20);
    private final GenericWebVideoPlayer livestreamVideoPlayer = new GenericWebVideoPlayer();

    @Override
    protected void updateModelFromContextParameters() {
        pathEventIdProperty.set(Numbers.toInteger(getParameter(EventVideosWallRouting.PATH_EVENT_ID_PARAMETER_NAME)));
    }

    @Override
    protected void startLogic() {
        // Creating our own entity store to hold the loaded data without interfering with other activities
        EntityStore entityStore = EntityStore.create(getDataSourceModel()); // Activity datasource model is available at this point
        FXProperties.runNowAndOnPropertiesChange(() -> {
            Object eventId = pathEventIdProperty.get();
            EntityId userPersonId = FXUserPersonId.getUserPersonId();
            if (eventId == null || userPersonId == null) {
                videoScheduledItems.clear();
                eventProperty.set(null);
            } else {
                entityStore.executeQueryBatch(
                        new EntityStoreQuery("select name, label.(de,en,es,fr,pt), shortDescription, audioExpirationDate, startDate, endDate, livestreamUrl, vodExpirationDate" +
                                             " from Event" +
                                             " where id=? limit 1",
                            new Object[]{eventId}),
                        new EntityStoreQuery("select date, expirationDate, event, vodDelayed, parent.(name, timeline.(startTime, endTime), item.imageUrl)," +
                                             " exists(select Media where scheduledItem=si and published) as " + VIDEO_SCHEDULED_ITEM_DYNAMIC_BOOLEAN_FIELD_HAS_PUBLISHED_MEDIAS +
                                             " from ScheduledItem si" +
                                             " where item.family.code=? and online and exists(select Attendance where scheduledItem=si and documentLine.(!cancelled and document.(event= ? and person=? and price_balance<=0)))" +
                                             " order by date, parent.timeline.startTime",
                            new Object[]{KnownItemFamily.VIDEO.getCode(), eventId, userPersonId}))
                    .onFailure(Console::log)
                    .onSuccess(entityLists -> Platform.runLater(() -> {
                        videoScheduledItems.setAll(entityLists[1]);
                        eventProperty.set((Event) Collections.first(entityLists[0]));
                    }));
            }
        }, pathEventIdProperty, FXUserPersonId.userPersonIdProperty());
    }

    @Override
    public void onResume() {
        super.onResume();
        // Restarting the livestream video player (if relevant) when reentering this activity. This will also ensure that
        // any possible previous playing player (ex: podcast) will be paused if/when the livestream video player restarts.
        updateLivestreamVideoPlayerStateAndVisibility();
    }

    @Override
    public Node buildUi() { // Reminder: called only once (rebuild = bad UX) => UI is reacting to parameter changes

        // *************************************************************************************************************
        // ********************************* Building the static part of the UI ****************************************
        // *************************************************************************************************************

        // Back arrow and event title
        MonoPane backArrow = SvgIcons.createButtonPane(SvgIcons.createBackArrow(), getHistory()::goBack);

        Label eventLabel = Bootstrap.h2(Bootstrap.strong(I18nControls.bindI18nProperties(new Label(), new I18nSubKey("expression: i18n(this)", eventProperty), eventProperty)));
        eventLabel.setWrapText(true);
        eventLabel.setTextAlignment(TextAlignment.CENTER);

        Label eventDescriptionLabel = I18nControls.bindI18nProperties(new Label(), new I18nSubKey("expression: shortDescription", eventProperty), eventProperty);
        eventDescriptionLabel.setWrapText(true);
        eventDescriptionLabel.setTextAlignment(TextAlignment.CENTER);
        eventDescriptionLabel.managedProperty().bind(FXProperties.compute(eventDescriptionLabel.textProperty(), Strings::isNotEmpty));

        VBox titleVBox = new VBox(
            eventLabel,
            eventDescriptionLabel
        );
        titleVBox.setAlignment(Pos.CENTER);
        //VBox.setMargin(eventLabel, new Insets(0, 0, 0, 40));

        CenteredPane backArrowAndTitlePane = new CenteredPane();
        backArrowAndTitlePane.setLeft(backArrow);
        backArrowAndTitlePane.setCenter(titleVBox);

        // Livestream box
        Label livestreamLabel = Bootstrap.h4(Bootstrap.strong(I18nControls.bindI18nProperties(new Label(), VideosI18nKeys.LivestreamTitle)));
        livestreamLabel.setWrapText(true);

        Node livestreamVideoView = livestreamVideoPlayer.getVideoView();
        /*if (livestreamVideoView instanceof Region) {
            Region videoRegion = (Region) livestreamVideoView;
            videoRegion.prefHeightProperty().bind(FXProperties.compute(videoRegion.widthProperty(), w -> w.doubleValue() / 16d * 9d));
        }*/

        Label pastVideoLabel = Bootstrap.h4(Bootstrap.strong(I18nControls.bindI18nProperties(new Label(), VideosI18nKeys.PastRecordings)));

        livestreamVBox.getChildren().setAll(
            livestreamLabel,
            livestreamVideoView
        );

        // Videos box (see below for population)
        VBox videosVBox = new VBox(30);

        // Assembling all together in the page container
        VBox pageContainer = new VBox(50,
            backArrowAndTitlePane,
            livestreamVBox,
            videosVBox
        );


        // *************************************************************************************************************
        // *********************************** Reacting to parameter changes *******************************************
        // *************************************************************************************************************

        ObservableList<DayVideosWallView> dayVideosWallViews = FXCollections.observableArrayList();
        BooleanProperty collapsedAllProperty = new SimpleBooleanProperty() {
            @Override
            protected void invalidated() {
                dayVideosWallViews.forEach(view -> view.setCollapsed(get()));
            }
        };
        Node collapsedAllChevron = CollapsePane.armChevron(CollapsePane.createPlainChevron(Color.BLACK), collapsedAllProperty);
        HBox pastVideoLabelAndChevronLine = new HBox(30, pastVideoLabel, collapsedAllChevron);
        pastVideoLabelAndChevronLine.setAlignment(Pos.CENTER_LEFT);

        ObservableLists.runNowAndOnListChange(change -> {
            Map<LocalDate, List<ScheduledItem>> perDayGroups =
                videoScheduledItems.stream().collect(Collectors.groupingBy(ScheduledItem::getDate));
            dayVideosWallViews.clear();
            new TreeMap<>(perDayGroups)
                .forEach((day, dayScheduledVideos) -> dayVideosWallViews.add(
                    new DayVideosWallView(day, dayScheduledVideos, getHistory())
                ));
        }, videoScheduledItems);

        // Populating the videos box (reacting to attendances changes)
        ObservableLists.runNowAndOnListChange(change -> {
            if (dayVideosWallViews.isEmpty()) {
                Label noContentLabel = Bootstrap.h3(Bootstrap.textWarning(I18nControls.bindI18nProperties(new Label(), VideosI18nKeys.NoVideosForThisEvent)));
                noContentLabel.setPadding(new Insets(150, 0, 100, 0));
                videosVBox.getChildren().setAll(noContentLabel);
            } else {
                videosVBox.getChildren().setAll(pastVideoLabelAndChevronLine);
                videosVBox.getChildren().addAll(Collections.map(dayVideosWallViews, DayVideosWallView::getView));
            }
        }, dayVideosWallViews);

        // Hiding / showing the livestream box (in dependence of the event)
        FXProperties.runOnPropertiesChange(this::updateLivestreamVideoPlayerStateAndVisibility, eventProperty);

        // *************************************************************************************************************
        // ************************************* Building final container **********************************************
        // *************************************************************************************************************

        pageContainer.setPadding(new Insets(PAGE_TOP_BOTTOM_PADDING, 0, PAGE_TOP_BOTTOM_PADDING, 0));
        return FrontOfficeActivityUtil.createActivityPageScrollPane(pageContainer, true);
    }

    private void updateLivestreamVideoPlayerStateAndVisibility() {
        Event event = eventProperty.get();
        //If the event has a GlobalLiveStreamLink, and the event is not finished, we display the livestream screen.
        //TODO see how to we manage the timezone of the user.
        String eventLivestreamUrl = event == null || Times.isPast(event.getEndDate()) ? null : event.getLivestreamUrl();
        boolean showLivestream = Strings.isNotEmpty(eventLivestreamUrl);
        if (showLivestream) {
            livestreamVideoPlayer.getPlaylist().setAll(eventLivestreamUrl);
            livestreamVideoPlayer.play(); // Will display and start the video (silent if before or after session)
            // The livestream player (Castr) doesn't support notification (unfortunately), so we don't wait onPlay()
            // to be called, we just inform right now that the livestream player is now playing, which will stop
            // any possible previous player (such as podcasts) immediately.
            Players.setPlayingPlayer(livestreamVideoPlayer);
        } else {
            // If there is no livestream, we pause the player (will actually stop it because pause is not supported)
            Players.pausePlayer(livestreamVideoPlayer); // ensures we silent the possible previous playing livestream
        }
        livestreamVBox.setVisible(showLivestream);
        livestreamVBox.setManaged(showLivestream);
    }

}

package one.modality.base.frontoffice.activities.mainframe;

import dev.webfx.kit.util.properties.FXProperties;
import dev.webfx.platform.util.collection.Collections;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Bruno Salmon
 */
final class SegmentedButton<T> {

    private static final Color SEGMENTED_BUTTON_COLOR = Color.web("#0096D6");
    private static final double RADII = 10;

    private final ButtonSegment<T>[] buttonSegments;
    private final BorderPane[] frames;
    private final HBox hBox;
    private final ObjectProperty<T> stateProperty = FXProperties.newObjectProperty(this::updateFrames);

    @SafeVarargs
    public SegmentedButton(ButtonSegment<T>... buttonSegments) {
        this(null, buttonSegments);
    }

    @SafeVarargs
    public SegmentedButton(T initialState, ButtonSegment<T>... buttonSegments) {
        this.buttonSegments = buttonSegments;
        frames = Arrays.stream(buttonSegments).map(this::createSegmentFrame).toArray(BorderPane[]::new);
        hBox = new HBox(frames);
        hBox.getStyleClass().setAll("segmented-button");
        setState(initialState);
    }

    public HBox getView() {
        return hBox;
    }

    public Object getState() {
        return stateProperty.get();
    }

    public ObjectProperty<T> stateProperty() {
        return stateProperty;
    }

    public void setState(T state) {
        stateProperty.set(state);
    }

    private BorderPane createSegmentFrame(ButtonSegment<T> buttonSegment) {
        Node graphic = buttonSegment.getGraphic();
        graphic.setMouseTransparent(true);
        BorderPane frame = new BorderPane(graphic);
        frame.getStyleClass().setAll("button-segment");
        boolean first = buttonSegment == buttonSegments[0];
        frame.setBorder(new Border(new BorderStroke(SEGMENTED_BUTTON_COLOR, BorderStrokeStyle.SOLID, segmentRadii(buttonSegment), new BorderWidths(1, 1 , 1, first ? 1 : 0), null)));
        frame.setCursor(Cursor.HAND);
        frame.setOnMouseClicked(e -> {
            setState(buttonSegment.getState());
        });
        frame.setOnMousePressed(e -> updateFramesFromState(buttonSegment.getState()));
        frame.setOnMouseReleased(e -> updateFrames());
        return frame;
    }

    private CornerRadii segmentRadii(ButtonSegment<T> buttonSegment) {
        boolean first = buttonSegment == buttonSegments[0];
        boolean last = buttonSegment == buttonSegments[buttonSegments.length - 1];
        return new CornerRadii(first ? RADII : 0, last ? RADII : 0, last ? RADII : 0, first ? RADII : 0, false);
    }

    private void updateFrames() {
        updateFramesFromState(getState());
    }

    private void updateFramesFromState(Object state) {
        for (int i = 0, n = buttonSegments.length; i < n; i++) {
            ButtonSegment<T> buttonSegment = buttonSegments[i];
            BorderPane frame = frames[i];
            if (Objects.equals(state, buttonSegment.getState())) {
                Collections.addIfNotContains("selected", frame.getStyleClass());
                frame.setBackground(new Background(new BackgroundFill(SEGMENTED_BUTTON_COLOR, segmentRadii(buttonSegment), null)));
            } else {
                frame.getStyleClass().remove("selected");
                frame.setBackground(null);
            }
        }
    }
}

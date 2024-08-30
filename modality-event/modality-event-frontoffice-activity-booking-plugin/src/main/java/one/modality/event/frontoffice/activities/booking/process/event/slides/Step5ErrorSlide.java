package one.modality.event.frontoffice.activities.booking.process.event.slides;

import dev.webfx.extras.styles.bootstrap.Bootstrap;
import dev.webfx.extras.webtext.HtmlText;
import dev.webfx.stack.i18n.I18n;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import one.modality.event.frontoffice.activities.booking.process.event.BookEventActivity;

final class Step5ErrorSlide extends StepSlide {

    private static final double MAX_SLIDE_WIDTH = 800;

    private final HtmlText errorMessage = Bootstrap.h5(new HtmlText());

    Step5ErrorSlide(BookEventActivity bookEventActivity) {
        super(bookEventActivity);
    }

    @Override
    void buildSlideUi() {
        mainVbox.setSpacing(10);
        mainVbox.setMaxWidth(MAX_SLIDE_WIDTH);

        Label title = Bootstrap.textDanger(Bootstrap.h3(new Label("An error has occurred")));
        title.setWrapText(true);
        title.setPadding(new Insets(50, 0, 30, 0));

        mainVbox.getChildren().setAll(title, errorMessage);
    }

    void reset() {
        errorMessage.setText("");
        super.reset();
    }

    void setErrorMessage(String errorMessageI18nKey) {
        errorMessage.setText("<center>" + I18n.getI18nText(errorMessageI18nKey) + "</center>");
    }
}

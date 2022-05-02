package mongoose.ecommerce.backoffice.activities.moneyflows;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import mongoose.base.shared.entities.MoneyAccount;

/**
 * @author Dan Newman
 */
public class MoneyAccountPane extends HBox {

    private final ObjectProperty<MoneyAccount> moneyAccountProperty = new SimpleObjectProperty<>();
    public ObjectProperty<MoneyAccount> moneyAccountProperty() { return moneyAccountProperty; }
    private final ObservableObjectValue<MoneyAccount> selectedMoneyAccount;
    private final Label label = new Label();

    public MoneyAccountPane(MoneyAccount moneyAccount, ObservableObjectValue<MoneyAccount> selectedMoneyAccount) {
        this.selectedMoneyAccount = selectedMoneyAccount;
        selectedMoneyAccount.addListener(e -> updateBorder());
        moneyAccountProperty.addListener(e -> label.setText(moneyAccountProperty.get().getName()));
        moneyAccountProperty.set(moneyAccount);
        setPadding(new Insets(8));
        label.setAlignment(Pos.CENTER);
        updateBorder();
        getChildren().addAll(label);
    }

    private void updateBorder() {
        String color = moneyAccountProperty.get().equals(selectedMoneyAccount.get()) ? "yellow" : "lightgray";
        setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: " + color + ";");
    }

}

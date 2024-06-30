package one.modality.ecommerce.document.service.events;

import one.modality.base.shared.entities.MoneyTransfer;

/**
 * @author Bruno Salmon
 */
public class AbstractMoneyTransferEvent extends AbstractDocumentEvent {

    protected MoneyTransfer moneyTransfer;
    private Object moneyTransferPrimaryKey;
    private final int amount;
    private final boolean pending;
    private final boolean successful;

    public AbstractMoneyTransferEvent(Object documentPrimaryKey, Object moneyTransferPrimaryKey, int amount, boolean pending, boolean successful) {
        super(documentPrimaryKey);
        this.moneyTransferPrimaryKey = moneyTransferPrimaryKey;
        this.amount = amount;
        this.pending = pending;
        this.successful = successful;
    }

    public AbstractMoneyTransferEvent(MoneyTransfer moneyTransfer) {
        super(moneyTransfer.getDocument());
        this.moneyTransfer = moneyTransfer;
        moneyTransferPrimaryKey = moneyTransfer.getPrimaryKey();
        amount = moneyTransfer.getAmount();
        pending = moneyTransfer.isPending();
        successful = moneyTransfer.isSuccessful();
    }

    public MoneyTransfer getMoneyTransfer() {
        if (moneyTransfer == null && entityStore != null) {
            moneyTransfer = entityStore.getOrCreateEntity(MoneyTransfer.class, getMoneyTransferPrimaryKey());
            moneyTransfer.setDocument(getDocument());
            moneyTransfer.setAmount(getAmount());
            moneyTransfer.setPending(isPending());
            moneyTransfer.setSuccessful(isSuccessful());
        }
        return moneyTransfer;
    }

    public Object getMoneyTransferPrimaryKey() {
        return moneyTransferPrimaryKey;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPending() {
        return pending;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setMoneyTransferPrimaryKey(Object moneyTransferPrimaryKey) {
        this.moneyTransferPrimaryKey = moneyTransferPrimaryKey;
    }
}

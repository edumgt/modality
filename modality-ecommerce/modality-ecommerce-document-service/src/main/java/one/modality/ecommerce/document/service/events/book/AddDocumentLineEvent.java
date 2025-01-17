package one.modality.ecommerce.document.service.events.book;

import dev.webfx.stack.orm.entity.Entities;
import one.modality.base.shared.entities.DocumentLine;
import one.modality.base.shared.entities.Item;
import one.modality.base.shared.entities.Site;
import one.modality.ecommerce.document.service.events.AbstractDocumentLineEvent;

/**
 * @author Bruno Salmon
 */
public final class AddDocumentLineEvent extends AbstractDocumentLineEvent {

    private final Object sitePrimaryKey;
    private final Object itemPrimaryKey;

    public AddDocumentLineEvent(DocumentLine documentLine) {
        super(documentLine);
        sitePrimaryKey = Entities.getPrimaryKey(documentLine.getSite());
        itemPrimaryKey = Entities.getPrimaryKey(documentLine.getItem());
    }

    public AddDocumentLineEvent(Object documentPrimaryKey, Object documentLinePrimaryKey, Object sitePrimaryKey, Object itemPrimaryKey) {
        super(documentPrimaryKey, documentLinePrimaryKey);
        this.sitePrimaryKey = sitePrimaryKey;
        this.itemPrimaryKey = itemPrimaryKey;
    }

    public Object getItemPrimaryKey() {
        return itemPrimaryKey;
    }

    public Object getSitePrimaryKey() {
        return sitePrimaryKey;
    }

    @Override
    protected void createDocumentLine() {
        if (isForSubmit()) {
            documentLine = updateStore.insertEntity(DocumentLine.class, getDocumentLinePrimaryKey());
        } else {
            super.createDocumentLine();
        }
    }

    @Override
    public void replayEventOnDocumentLine() {
        super.replayEventOnDocumentLine();
        documentLine.setSite(isForSubmit() ? sitePrimaryKey : entityStore.getEntity(Site.class, sitePrimaryKey, true)); // Should be found from PolicyAggregate
        documentLine.setItem(isForSubmit() ? itemPrimaryKey : entityStore.getEntity(Item.class, itemPrimaryKey, true)); // Should be found from PolicyAggregate
    }
}

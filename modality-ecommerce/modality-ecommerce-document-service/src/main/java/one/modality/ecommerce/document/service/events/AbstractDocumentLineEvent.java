package one.modality.ecommerce.document.service.events;

import one.modality.base.shared.entities.DocumentLine;

/**
 * @author Bruno Salmon
 */
public abstract class AbstractDocumentLineEvent extends AbstractDocumentEvent {

    protected DocumentLine documentLine;   // Working entity on client-side only (not serialised)
    private Object documentLinePrimaryKey; // Its primary key (serialised)

    public AbstractDocumentLineEvent(Object documentPrimaryKey, Object documentLinePrimaryKey) {
        super(documentPrimaryKey);
        this.documentLinePrimaryKey = documentLinePrimaryKey;
    }

    public AbstractDocumentLineEvent(DocumentLine documentLine) {
        super(documentLine.getDocument());
        this.documentLine = documentLine;
        this.documentLinePrimaryKey = documentLine.getPrimaryKey();
    }

    public DocumentLine getDocumentLine() {
        if (documentLine == null && entityStore != null) {
            documentLine = entityStore.getEntity(DocumentLine.class, documentLinePrimaryKey);
        }
        return documentLine;
    }

    public Object getDocumentLinePrimaryKey() {
        return documentLinePrimaryKey;
    }

    public void setDocumentLinePrimaryKey(Object documentLinePrimaryKey) { // Used to refactor new primary keys once inserted on server
        this.documentLinePrimaryKey = documentLinePrimaryKey;
    }
}

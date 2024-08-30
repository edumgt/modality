package one.modality.base.shared.entities;

import dev.webfx.stack.orm.entity.Entity;
import dev.webfx.stack.orm.entity.EntityId;
import one.modality.base.shared.entities.markers.EntityHasDocument;
import one.modality.base.shared.entities.markers.EntityHasUserPerson;

/**
 * @author Bruno Salmon
 */
public interface History extends Entity, EntityHasDocument, EntityHasUserPerson {

    default void setUsername(String username) {
        setFieldValue("username", username);
    }

    default String getUsername() {
        return getStringFieldValue("username");
    }

    default void setComment(String comment) {
        setFieldValue("comment", comment);
    }

    default String getComment() {
        return getStringFieldValue("comment");
    }

    default void setChanges(String changes) {
        setFieldValue("changes", changes);
    }

    default String getChanges() {
        return getStringFieldValue("changes");
    }

    default void setMail(Object mail) {
        setForeignField("mail", mail);
    }

    default EntityId getMailId() {
        return getForeignEntityId("mail");
    }

    default Mail getMail() {
        return getForeignEntity("mail");
    }

    default void setMoneyTransfer(Object document) {
        setForeignField("moneyTransfer", document);
    }

    default EntityId getMoneyTransferId() {
        return getForeignEntityId("moneyTransfer");
    }

    default Document getMoneyTransfer() {
        return getForeignEntity("moneyTransfer");
    }

}

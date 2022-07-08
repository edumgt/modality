package org.modality_project.base.shared.entities;

import org.modality_project.base.shared.entities.markers.EntityHasIcon;
import org.modality_project.base.shared.entities.markers.EntityHasOrganization;

/**
 * @author Bruno Salmon
 */
public interface Label extends
        EntityHasIcon,
        EntityHasOrganization {

    default void setDe(String de) {
        setFieldValue("de", de);
    }

    default String getDe() {
        return getStringFieldValue("de");
    }

    default void setEn(String en) {
        setFieldValue("en", en);
    }

    default String getEn() {
        return getStringFieldValue("en");
    }

    default void setEs(String es) {
        setFieldValue("es", es);
    }

    default String getEs() {
        return getStringFieldValue("es");
    }

    default void setFr(String fr) {
        setFieldValue("fr", fr);
    }

    default String getFr() {
        return getStringFieldValue("fr");
    }

    default void setPt(String pt) {
        setFieldValue("pt", pt);
    }

    default String getPt() {
        return getStringFieldValue("pt");
    }

}

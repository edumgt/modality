package one.modality.base.client.entities.util.functions;

import dev.webfx.extras.type.PrimType;
import dev.webfx.extras.type.Type;
import dev.webfx.stack.i18n.I18n;
import dev.webfx.stack.orm.entity.EntityId;
import dev.webfx.stack.orm.expression.lci.DomainReader;
import dev.webfx.stack.orm.expression.terms.function.Function;
import one.modality.base.shared.entities.Label;
import one.modality.base.shared.entities.markers.HasLabel;
import one.modality.base.shared.entities.markers.HasName;

/**
 * i18n() function that can be used in expressions. Special cases are handled depending on the passed argument:
 * - if an entity (or EntityId) is passed, it will try to find the translation in the associated label, or name otherwise
 * - if a label is passed, it will return the translation in the current i18n language.
 * - in other cases, it will call I18n.getI18nText() with the passed argument
 *
 * @author Bruno Salmon
 */
public final class I18nFunction extends Function {

    public I18nFunction() {
        super("i18n", new String[] {"label"}, new Type[] {null}, PrimType.STRING, true);
    }

    @Override
    public Object evaluate(Object argument, DomainReader domainReader) {
        Object result;
        if (argument instanceof EntityId)
            argument = domainReader.getDomainObjectFromId(argument, null);
        Label label = null;
        if (argument instanceof Label)
            label = (Label) argument;
        else if (argument instanceof HasLabel)
            label = ((HasLabel) argument).getLabel();
        if (label != null) {
            result = domainReader.getDomainFieldValue(label, I18n.getLanguage());
            if (result == null)
                result = domainReader.getDomainFieldValue(label, I18n.getDefaultLanguage());
        } else {
            result = I18n.getI18nText(argument);
        }
        if (result == null && argument instanceof HasName)
            result = ((HasName) argument).getName();
        return result;
    }
}

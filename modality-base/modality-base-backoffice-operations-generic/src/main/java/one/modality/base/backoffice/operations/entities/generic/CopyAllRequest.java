package one.modality.base.backoffice.operations.entities.generic;

import dev.webfx.stack.orm.reactive.entities.entities_to_grid.EntityColumn;
import dev.webfx.stack.orm.entity.Entity;

import java.util.Collection;

public final class CopyAllRequest extends CopyRequest {

    private final static String OPERATION_CODE = "CopyAll";

    public CopyAllRequest(Collection<? extends Entity> entities, EntityColumn... columns) {
        super(entities, columns);
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }
}

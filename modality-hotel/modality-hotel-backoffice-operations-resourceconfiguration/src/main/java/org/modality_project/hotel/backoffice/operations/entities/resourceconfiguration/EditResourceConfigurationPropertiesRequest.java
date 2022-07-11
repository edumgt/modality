package org.modality_project.hotel.backoffice.operations.entities.resourceconfiguration;

import javafx.scene.layout.Pane;
import dev.webfx.stack.framework.shared.operation.HasOperationCode;
import dev.webfx.stack.framework.shared.operation.HasOperationExecutor;
import dev.webfx.stack.framework.shared.orm.entity.Entity;
import dev.webfx.stack.platform.async.AsyncFunction;

public final class EditResourceConfigurationPropertiesRequest implements HasOperationCode,
        HasOperationExecutor<EditResourceConfigurationPropertiesRequest, Void> {

    private final static String OPERATION_CODE = "EditResourceConfigurationProperties";

    private final Entity resourceConfiguration;
    private final Pane parentContainer;

    public EditResourceConfigurationPropertiesRequest(Entity resourceConfiguration, Pane parentContainer) {
        this.resourceConfiguration = resourceConfiguration;
        this.parentContainer = parentContainer;
    }

    Entity getResourceConfiguration() {
        return resourceConfiguration;
    }

    Pane getParentContainer() {
        return parentContainer;
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }

    @Override
    public AsyncFunction<EditResourceConfigurationPropertiesRequest, Void> getOperationExecutor() {
        return EditResourceConfigurationPropertiesExecutor::executeRequest;
    }
}
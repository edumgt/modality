package org.modality_project.hotel.backoffice.operations.entities.resourceconfiguration;

import dev.webfx.stack.framework.shared.operation.HasOperationCode;
import dev.webfx.stack.framework.shared.operation.HasOperationExecutor;
import dev.webfx.stack.framework.shared.orm.entity.Entity;
import dev.webfx.stack.platform.shared.services.submit.SubmitResult;
import dev.webfx.stack.platform.async.AsyncFunction;
import dev.webfx.stack.platform.async.Batch;

public final class ToggleResourceConfigurationOnlineOfflineRequest implements HasOperationCode,
        HasOperationExecutor<ToggleResourceConfigurationOnlineOfflineRequest, Batch<SubmitResult>> {

    private final static String OPERATION_CODE = "ToggleResourceConfigurationOnlineOffline";

    private final Entity resourceConfiguration;

    public ToggleResourceConfigurationOnlineOfflineRequest(Entity resourceConfiguration) {
        this.resourceConfiguration = resourceConfiguration;
    }

    Entity getResourceConfiguration() {
        return resourceConfiguration;
    }

    @Override
    public Object getOperationCode() {
        return OPERATION_CODE;
    }

    @Override
    public AsyncFunction<ToggleResourceConfigurationOnlineOfflineRequest, Batch<SubmitResult>> getOperationExecutor() {
        return ToggleResourceConfigurationOnlineOfflineExecutor::executeRequest;
    }
}
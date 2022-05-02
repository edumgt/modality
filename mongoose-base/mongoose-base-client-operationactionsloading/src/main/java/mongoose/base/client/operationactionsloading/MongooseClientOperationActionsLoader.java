package mongoose.base.client.operationactionsloading;

import dev.webfx.framework.client.services.i18n.I18n;
import dev.webfx.framework.client.ui.action.Action;
import dev.webfx.framework.client.ui.action.ActionFactoryMixin;
import dev.webfx.framework.client.ui.action.operation.OperationActionFactoryMixin;
import dev.webfx.framework.client.ui.action.operation.OperationActionRegistry;
import dev.webfx.framework.client.ui.uirouter.uisession.UiSession;
import dev.webfx.framework.shared.orm.entity.Entity;
import dev.webfx.framework.shared.orm.entity.EntityStore;
import dev.webfx.framework.shared.services.datasourcemodel.DataSourceModelService;
import dev.webfx.platform.shared.services.boot.spi.ApplicationModuleBooter;
import dev.webfx.platform.shared.services.log.Logger;

/**
 * @author Bruno Salmon
 */
public class MongooseClientOperationActionsLoader implements ApplicationModuleBooter,
        OperationActionFactoryMixin,
        ActionFactoryMixin {

    @Override
    public String getModuleName() {
        return "mongoose-base-client-operationactionsloading";
    }

    @Override
    public int getBootLevel() {
        return APPLICATION_BOOT_LEVEL;
    }

    @Override
    public void bootModule() {
        // Temporary load only the operations for the backend (ie back-office) for the demo (because backend and frontend fields are not considered by authz so far)
        EntityStore.create(DataSourceModelService.getDefaultDataSourceModel())
                .executeQuery("select operationCode,i18nCode,public from Operation where backend")
                .onFailure(cause -> Logger.log("Failed loading operations", cause))
                .onSuccess(operations -> {
                    OperationActionRegistry registry = getOperationActionRegistry();
                    // Registering graphical properties for all loaded operations
                    for (Entity operation : operations) {
                        String operationCode = operation.getStringFieldValue("operationCode");
                        String i18nCode = operation.getStringFieldValue("i18nCode");
                        boolean isPublic = operation.getBooleanFieldValue("public");
                        Object i18nKey = new MongooseOperationI18nKey(i18nCode);
                        Action operationGraphicalAction = isPublic ? newAction(i18nKey) : newAuthAction(i18nKey, registry.authorizedOperationActionProperty(operationCode, UiSession.get().userPrincipalProperty(), UiSession.get()::isAuthorized));
                        operationGraphicalAction.setUserData(i18nKey);
                        registry.registerOperationGraphicalAction(operationCode, operationGraphicalAction);
                    }
                    // Telling the registry how to update the graphical properties when needed (ex: ToggleCancel actions text
                    // needs to be updated to say 'Cancel' or 'Uncancel' on selection change)
                    registry.setOperationActionGraphicalPropertiesUpdater(operationAction -> {
                        // Actually since text and graphic properties come from I18n, we just need to inform it about the
                        // change, and it will refresh all translations, including therefore these graphical properties.
                        // The possible expressions used by operations like ToggleCancel will be recomputed through this
                        // refresh thanks to the I18n evaluation system.
                        // Instantiating an operation request just to have the request class or operation code
                        Object operationRequest = registry.newOperationActionRequest(operationAction);
                        // Then getting the graphical action from it
                        Action graphicalAction = registry.getGraphicalActionFromOperationRequest(operationRequest);
                        if (graphicalAction != null) {
                            Object i18nKey = graphicalAction.getUserData();
                            if (i18nKey instanceof MongooseOperationI18nKey)
                                ((MongooseOperationI18nKey) i18nKey).setOperationRequest(operationRequest);
                            I18n.refreshMessageTranslations(i18nKey);
                        }
                    });
                });
    }

}

package one.modality.crm.server.authn.gateway;

import dev.webfx.platform.async.Future;
import dev.webfx.platform.console.Console;
import dev.webfx.stack.authn.PasswordUpdate;
import dev.webfx.stack.authn.UserClaims;
import dev.webfx.stack.authn.UsernamePasswordCredentials;
import dev.webfx.stack.authn.logout.server.LogoutPush;
import dev.webfx.stack.authn.server.gateway.spi.ServerAuthenticationGatewayProvider;
import dev.webfx.stack.hash.md5.Md5;
import dev.webfx.stack.orm.datasourcemodel.service.DataSourceModelService;
import dev.webfx.stack.orm.domainmodel.DataSourceModel;
import dev.webfx.stack.orm.domainmodel.HasDataSourceModel;
import dev.webfx.stack.orm.entity.Entities;
import dev.webfx.stack.orm.entity.Entity;
import dev.webfx.stack.orm.entity.EntityStore;
import dev.webfx.stack.orm.entity.UpdateStore;
import dev.webfx.stack.push.server.PushServerService;
import dev.webfx.stack.session.state.StateAccessor;
import dev.webfx.stack.session.state.ThreadLocalStateHolder;
import one.modality.base.shared.entities.Person;
import one.modality.crm.shared.services.authn.ModalityUserPrincipal;

import java.util.Objects;


/**
 * @author Bruno Salmon
 */
public final class ModalityUsernamePasswordAuthenticationGatewayProvider implements ServerAuthenticationGatewayProvider, HasDataSourceModel {

    private final DataSourceModel dataSourceModel;

    public ModalityUsernamePasswordAuthenticationGatewayProvider() {
        this(DataSourceModelService.getDefaultDataSourceModel());
    }

    public ModalityUsernamePasswordAuthenticationGatewayProvider(DataSourceModel dataSourceModel) {
        this.dataSourceModel = dataSourceModel;
    }

    @Override
    public DataSourceModel getDataSourceModel() {
        return dataSourceModel;
    }

    @Override
    public boolean acceptsUserCredentials(Object userCredentials) {
        return userCredentials instanceof UsernamePasswordCredentials;
    }

    @Override
    public Future<Void> authenticate(Object userCredentials) {
        if (!acceptsUserCredentials(userCredentials))
            return Future.failedFuture(getClass().getSimpleName() + " requires a " + UsernamePasswordCredentials.class.getSimpleName() + " argument");
        String runId = ThreadLocalStateHolder.getRunId();
        UsernamePasswordCredentials usernamePasswordCredentials = (UsernamePasswordCredentials) userCredentials;
        String username = usernamePasswordCredentials.getUsername();
        String password = usernamePasswordCredentials.getPassword();
        if (username == null || password == null)
            return Future.failedFuture("Username and password must be non-null");
        username = username.trim(); // Ignoring leading and tailing spaces in username
        if (username.contains("@")) // If username is an email address, it shouldn't be case-sensitive
            username = username.toLowerCase(); // emails are stored in lowercase in the database
        String encryptedPassword = encryptPassword(username, password);
        return EntityStore.create(dataSourceModel)
            .<Person>executeQuery("select id,frontendAccount.id from Person where frontendAccount.(corporation=? and username=? and password=?) order by id limit 1", 1, username, encryptedPassword)
            .compose(persons -> {
                if (persons.size() != 1)
                    return Future.failedFuture("Wrong user or password");
                Person userPerson = persons.get(0);
                Object personId = userPerson.getPrimaryKey();
                Object accountId = Entities.getPrimaryKey(userPerson.getForeignEntityId("frontendAccount"));
                ModalityUserPrincipal modalityUserPrincipal = new ModalityUserPrincipal(personId, accountId);
                return PushServerService.pushState(StateAccessor.createUserIdState(modalityUserPrincipal), runId);
            });
    }

    private String encryptPassword(String username, String password) {
        String toEncrypt = username + ":" + Md5.hash(password);
        String encrypted = Md5.hash(toEncrypt);
        return encrypted;
    }

    @Override
    public boolean acceptsUserId() {
        Object userId = ThreadLocalStateHolder.getUserId();
        return userId instanceof ModalityUserPrincipal;
    }

    @Override
    public Future<?> verifyAuthenticated() {
        Object userId = ThreadLocalStateHolder.getUserId();
        Console.log("👮👮👮👮👮 Checking userId=" + userId);
        return queryModalityUserPerson("id")
                .map(ignoredQueryResult -> userId);
    }

    @Override
    public Future<UserClaims> getUserClaims() {
        return queryModalityUserPerson("frontendAccount.username,email,phone")
                .map(userPerson -> {
                    String username = userPerson.evaluate("frontendAccount.username");
                    String email = userPerson.getEmail();
                    String phone = userPerson.getPhone();
                    return new UserClaims(username, email, phone, null);
                });
    }

    private Future<Person> queryModalityUserPerson(String fields) {
        Object userId = ThreadLocalStateHolder.getUserId();
        if (!(userId instanceof ModalityUserPrincipal))
            return Future.failedFuture("This userId object is not recognized by Modality");
        ModalityUserPrincipal modalityUserPrincipal = (ModalityUserPrincipal) userId;
        return EntityStore.create(dataSourceModel)
            .<Person>executeQuery("select " + fields + " from Person where id=? and frontendAccount=?", modalityUserPrincipal.getUserPersonId(), modalityUserPrincipal.getUserAccountId())
            .compose(persons -> {
                if (persons.size() != 1)
                    return Future.failedFuture("No such user in Modality database");
                return Future.succeededFuture(persons.get(0));
            });
    }

    @Override
    public boolean acceptsUpdateCredentialsArgument(Object updateCredentialsArgument) {
        return updateCredentialsArgument instanceof PasswordUpdate;
    }

    @Override
    public Future<?> updateCredentials(Object updateCredentialsArgument) {
        if (!acceptsUpdateCredentialsArgument(updateCredentialsArgument))
            return Future.failedFuture(getClass().getSimpleName() + ".updateCredentials() requires a " + PasswordUpdate.class.getSimpleName() + " argument");
        PasswordUpdate passwordUpdate = (PasswordUpdate) updateCredentialsArgument;
        // 1) We first check that the passed old password matches with the one in database
        return queryModalityUserPerson("frontendAccount.(username,password)")
            .compose(userPerson -> {
                Entity frontendAccount = userPerson.getForeignEntity("frontendAccount");
                String username = frontendAccount.getStringFieldValue("username");
                String dbPassword = frontendAccount.getStringFieldValue("password");
                if (!Objects.equals(dbPassword, passwordUpdate.getOldPassword()))
                    return Future.failedFuture("The old password doesn't match");
                String encryptedPassword = encryptPassword(username, passwordUpdate.getNewPassword());
                // 2) We update the password in the database
                UpdateStore updateStore = UpdateStore.createAbove(frontendAccount.getStore());
                Entity ufa = updateStore.updateEntity(frontendAccount);
                ufa.setFieldValue("password", encryptedPassword);
                return updateStore.submitChanges();
            });
    }

    @Override
    public Future<Void> logout() {
        return LogoutPush.pushLogoutMessageToClient();
    }
}

package mongoose.crm.client.services.authn;

import dev.webfx.framework.shared.orm.domainmodel.DataSourceModel;
import dev.webfx.framework.shared.orm.domainmodel.HasDataSourceModel;
import dev.webfx.framework.shared.services.authn.UsernamePasswordCredentials;
import dev.webfx.framework.shared.services.authn.spi.AuthenticationServiceProvider;
import dev.webfx.framework.shared.services.datasourcemodel.DataSourceModelService;
import dev.webfx.platform.shared.services.query.QueryArgument;
import dev.webfx.platform.shared.services.query.QueryService;
import dev.webfx.platform.shared.async.Future;

/**
 * @author Bruno Salmon
 */
public final class MongooseAuthenticationServiceProvider implements AuthenticationServiceProvider, HasDataSourceModel {

    private final DataSourceModel dataSourceModel;

    public MongooseAuthenticationServiceProvider() {
        this(DataSourceModelService.getDefaultDataSourceModel());
    }

    public MongooseAuthenticationServiceProvider(DataSourceModel dataSourceModel) {
        this.dataSourceModel = dataSourceModel;
    }

    @Override
    public DataSourceModel getDataSourceModel() {
        return dataSourceModel;
    }

    @Override
    public Future<MongooseUserPrincipal> authenticate(Object userCredentials) {
        if (!(userCredentials instanceof UsernamePasswordCredentials))
            return Future.failedFuture(new IllegalArgumentException("MongooseAuthenticationServiceProvider requires a UsernamePasswordCredentials argument"));
        UsernamePasswordCredentials usernamePasswordCredentials = (UsernamePasswordCredentials) userCredentials;
        return QueryService.executeQuery(QueryArgument.builder()
                .setLanguage("DQL")
                .setStatement("select id,frontendAccount.id from Person where frontendAccount.(corporation=? and username=? and (password=? or true)) order by id limit 1")
                .setParameters(1, usernamePasswordCredentials.getUsername(), usernamePasswordCredentials.getPassword()) // "or true" is temporary to bypass the password checking which is now encrypted TODO: implement encrypted version of password checking
                .setDataSourceId(getDataSourceId())
                .build()
        ).compose(result -> result.getRowCount() != 1 ? Future.failedFuture("Wrong user or password")
                : Future.succeededFuture(new MongooseUserPrincipal(result.getValue(0, 0), result.getValue(0, 1)))
        );
    }
}

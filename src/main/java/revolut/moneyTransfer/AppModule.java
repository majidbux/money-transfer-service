package revolut.moneyTransfer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.typesafe.config.Config;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcConnectionPool;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public class AppModule implements Module {
    private final Config config;

    public AppModule(Config config){
        this.config = config;
    }

    @Override
    public void configure(Binder binder) {
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return  new GsonBuilder().setPrettyPrinting().create();
    }

    @Singleton
    @Provides
    public Flyway providesFlyway(DataSource dataSource) {
       Flyway flyway = Flyway.configure().dataSource(dataSource).load();
       flyway.getConfiguration().getLocations();
       return flyway;
    }

    @Singleton
    @Provides
    public DataSource providesDataSource() {
        final String dbURL = config.getString("datasource.jdbcUrl");
        final String userName = config.getString("datasource.username");
        final String password = config.getString("datasource.password");
        return JdbcConnectionPool.create(dbURL, userName, password);
    }

    @Singleton
    @Provides
    public DSLContext providesDSLContext(DataSource dataSource) {
        return DSL.using(dataSource, SQLDialect.H2);
    }


    @Singleton
    @Provides
    public Validator provideValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
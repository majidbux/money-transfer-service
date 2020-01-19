package revolut.moneyTransfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.jooq.exception.DataAccessException;
import revolut.moneyTransfer.exception.ExceptionHandler;
import revolut.moneyTransfer.exception.MoneyTransferBaseException;

import java.io.File;

import static spark.Spark.*;

@Slf4j
public class Application {

    private final Injector applicationInjector;
    private final Config config;

    public Application(Config config) {
        this.config = config;
        this.applicationInjector = Guice.createInjector(new AppModule(config));
    }

    public static void main(String[] args) {
        Config config = ConfigFactory
                .parseFile(new File("application.properties"))
                .withFallback(ConfigFactory.load());
        Application application = new Application(config);
        application.startApplication();
    }

    void startApplication() {
        log.info("Starting {} on port {}", config.getString("application.name"), config.getInt("port"));
        port(config.getInt("port"));
        migrateSchema();
        before((request, response) -> response.type(config.getString("application.contentType")));
        TransferController transferController = getInstance(TransferController.class);
        transferController.init();
        ExceptionHandler exceptionHandler = getInstance(ExceptionHandler.class);
        exception(MoneyTransferBaseException.class, exceptionHandler::handleMoneyTransferExceptions);
        exception(DataAccessException.class, exceptionHandler::handleInternalServerError);

    }

    public void migrateSchema() {
        Flyway flyway = getInstance(Flyway.class);
        flyway.migrate();
    }

    public void cleanSchema() {
        Flyway flyway = getInstance(Flyway.class);
        flyway.clean();
    }

    public <T> T getInstance(Class<T> var) {
        return applicationInjector.getInstance(var);
    }

    void stopApplication() {
        stop();
    }

}
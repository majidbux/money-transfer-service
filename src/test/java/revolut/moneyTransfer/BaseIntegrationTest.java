package revolut.moneyTransfer;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import spark.Spark;

import java.io.File;
import java.util.Objects;

public abstract class BaseIntegrationTest {

    protected static Application application;

    static {
        RestAssured.baseURI = "http://localhost:" + 8080;
        RestAssured.basePath = "/api";
    }

    @BeforeAll
    static void start() {
        Config config = ConfigFactory
                .parseFile(new File("application.properties"))
                .withFallback(ConfigFactory.load());
        application = new Application(config);
        application.startApplication();
        Spark.awaitInitialization();
    }

    @AfterAll
    static void stop() {
        if (Objects.nonNull(application)) {
            application.stopApplication();
            application.cleanSchema();
        }
    }

    @BeforeEach
    public void setup() {
        if (Objects.nonNull(application)) {
            application.cleanSchema();
            application.migrateSchema();
        }
    }

    @AfterEach
    public void after() {
        if (Objects.nonNull(application)) {
            application.cleanSchema();
        }
    }
}
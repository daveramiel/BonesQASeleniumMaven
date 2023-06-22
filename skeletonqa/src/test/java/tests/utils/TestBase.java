package tests.utils;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBase {

    public static final Logger LOGGER = Logger.getLogger(String.valueOf(TestBase.class));
    public static WebDriver driver;
    public static final  String CERRAR = "El usuario cierra navegador web";
    public static final  String ABRIR = "El usuario abre navegador web";

    @BeforeAll
    @Step(ABRIR + "Algo")
    public static void init() {
        driver = ar.globallogic.qa.webdriver.WebDriver.getInstance();
    }

    @AfterAll
    @Step(CERRAR + "Algo")
    public static void out() {
        ar.globallogic.qa.webdriver.WebDriver.closeDriver();
        driver.quit();
        LOGGER.log(Level.INFO, "Test execution completed!");
    }

    private void executeAllure() {
        try {
            String userdir = System.getProperty("user.dir");
            Process p = Runtime
                    .getRuntime()
                    .exec("cmd /c start cmd.exe /K \"cd " + userdir + " && allure serve allure-results\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

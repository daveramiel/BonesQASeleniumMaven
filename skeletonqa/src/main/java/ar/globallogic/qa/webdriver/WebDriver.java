package ar.globallogic.qa.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriver {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(WebDriver.class));
    public static org.openqa.selenium.WebDriver driver;
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private WebDriver() {

    }

    public static org.openqa.selenium.WebDriver getInstance() {
        if (driver == null) {
            driver = WebDriver.createDriver();
        }
        return driver;
    }

    public static org.openqa.selenium.WebDriver createDriver() {
        switch (ConfigHelper.getBrowserName()) {
            case CHROME:
                driver = WebDriverManager.chromedriver().capabilities(CapabilitiesBrowser.getChromeOptions()).create();
                break;
            case FIREFOX:
                driver = WebDriverManager.firefoxdriver().capabilities(CapabilitiesBrowser.getFirefoxOptions()).create();
                break;
            default:
                unsupportedBrowser(ConfigHelper.getBrowserName());
                break;
        }
        BannerPage.getBannerPage();
        LOGGER.log(Level.INFO, "Navigated to: " + ConfigHelper.getAppUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ConfigHelper.getAppDefaultWait(), TimeUnit.SECONDS);
        driver.get(ConfigHelper.getAppUrl());
        return driver;
    }

    private static void unsupportedBrowser(String browserName) {
        LOGGER.log(Level.WARNING, "Browser '{0}' is either unknown or not supported", browserName);
    }

    /**
     * Quits the driver, closes all open windows and resets the this singleton
     */
    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}

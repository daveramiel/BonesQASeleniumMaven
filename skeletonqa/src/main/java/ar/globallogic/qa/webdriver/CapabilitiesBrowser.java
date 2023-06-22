package ar.globallogic.qa.webdriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class CapabilitiesBrowser {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (ConfigHelper.getPrivate() && ConfigHelper.getHeadless()) {
            chromeOptions.addArguments("incognito");
            chromeOptions.addArguments("start-maximized");
        } else if (ConfigHelper.getPrivate() && !ConfigHelper.getHeadless()){
            chromeOptions.addArguments("incognito");
            chromeOptions.addArguments("--window-size=1400,600");
            chromeOptions.addArguments("--headless");
        } else if (!ConfigHelper.getPrivate() && ConfigHelper.getHeadless()) {
            chromeOptions.addArguments("start-maximized");
        }
        return chromeOptions;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("-private");
        if (ConfigHelper.getHeadless()) {
            firefoxOptions.addArguments("-fullscreen");
        } else {
            firefoxOptions.addArguments("-headless");
        }
        return firefoxOptions;
    }
}

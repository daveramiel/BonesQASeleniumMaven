package ar.globallogic.qa.webdriver;

import java.util.logging.Logger;

public class BannerPage {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BannerPage.class));

    public static void getBannerPage() {

        String banner2Auto = "  ___          _                             _    _               \n" +
                " / _ \\        | |                           | |  (_)              \n" +
                "/ /_\\ \\ _   _ | |_   ___   _ __ ___    __ _ | |_  _   ___   _ __  \n" +
                "|  _  || | | || __| / _ \\ | '_ ` _ \\  / _` || __|| | / _ \\ | '_ \\ \n" +
                "| | | || |_| || |_ | (_) || | | | | || (_| || |_ | || (_) || | | |\n" +
                "\\_| |_/ \\__,_| \\__| \\___/ |_| |_| |_| \\__,_| \\__||_| \\___/ |_| |_|";

        String bannerProduct = "\n" +
                " _____  _     ___________  ___   _      _     _____ _____ _____ _____ \n" +
                "|  __ \\| |   |  _  | ___ \\/ _ \\ | |    | |   |  _  |  __ \\_   _/  __ \\\n" +
                "| |  \\/| |   | | | | |_/ / /_\\ \\| |    | |   | | | | |  \\/ | | | /  \\/\n" +
                "| | __ | |   | | | | ___ \\  _  || |    | |   | | | | | __  | | | |    \n" +
                "| |_\\ \\| |___\\ \\_/ / |_/ / | | || |____| |___\\ \\_/ / |_\\ \\_| |_| \\__/\\\n" +
                " \\____/\\_____/\\___/\\____/\\_| |_/\\_____/\\_____/\\___/ \\____/\\___/ \\____/\n" +
                "                                                                      \n" +
                "                                                                      \n";
        LOGGER.info("\n" + bannerProduct + "\n" +  banner2Auto);
    }
}

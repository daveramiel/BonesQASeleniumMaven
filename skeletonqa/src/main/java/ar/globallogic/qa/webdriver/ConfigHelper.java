package ar.globallogic.qa.webdriver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import ar.globallogic.qa.base.PropertiesReader;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {

    public static final String BROWSER_NAME = "browserName";
    public static final String APP_URL = "appUrl";
    public static final String APP_DEFAULT_WAIT = "implicitlyWait";
    public static final String PAGE_LOAD_WAIT = "pageLoadTimeOut";
    public static final String NAVEGADOR_VISIBLE = "verNavegador";
    public static final String INCOGNITO = "navegadorPrivado";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    private static Configuracion configuracion;
    private static final String DATABASE_PROPERTIES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\db.properties";
    private static final String CONFIGURACION_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties.json";

    static {
        getConfiguracion();
    }

    public static String getBrowserName() {
        return configuracion.getBrowserName();
    }

    public static String getAppUrl() {
        return configuracion.getAppUrl();
    }

    public static void setAppUrl(String url){
        System.setProperty(APP_URL, url);
    }

    public static int getAppDefaultWait() {
        return configuracion.getImplicitlyWait();
    }

    public static int getPageLoadTimeOut() {
        return configuracion.getPageLoadTimeOut();
    }

    public static Boolean getHeadless() {
        return configuracion.isVerNavegador();
    }

    public static Boolean getPrivate() {
        return configuracion.isNavegadorPrivado();
    }

    public static String getUsuario() {
        return configuracion.getUsuario();
    }

    public static String getPassword() {
        return configuracion.getPassword();
    }

    public static String getDatabasePropertiesFilePath() {
        return DATABASE_PROPERTIES_FILE_PATH;
    }

    public static Properties getDatabaseProperties() {
        return new PropertiesReader(getDatabasePropertiesFilePath()).getProperties();
    }

    public static void getConfiguracion() {
        ObjectMapper mapper = new ObjectMapper();
        YAMLFactory factory = new YAMLFactory();
        YAMLParser parser = null;
        if(CONFIGURACION_PATH == null){
            configuracion = new Configuracion(
                    "https://www.google.com.ar/",
                    true,
                    false,
                    10,
                    200,
                    "chrome",
                    "Usuario",
                    "Password");
        } else {
            try {
                parser = factory.createParser(new File(CONFIGURACION_PATH));
                mapper.readerFor(Configuracion.class);
                ObjectReader reader = mapper.readerForUpdating(new Configuracion());
                JsonNode configJson = reader.readTree(parser);
                configuracion = reader.treeToValue(configJson.at("/default"), Configuracion.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package ar.globallogic.qa.base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static FileReader reader;
    private static Properties p;

    public PropertiesReader(String path) {
        try {
            reader = new FileReader(path);
            p = new Properties();
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return p;
    }

}

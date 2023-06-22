package ar.globallogic.qa.base;

public enum DataBaseProperties {

    SERVER("server"),
    PORT("port"),
    DATABASENAME("dataBaseName"),
    USER("user"),
    PASSWORD("password");


    private String property;

    DataBaseProperties(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}

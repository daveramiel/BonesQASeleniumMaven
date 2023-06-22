package ar.globallogic.qa.base;

import ar.globallogic.qa.webdriver.ConfigHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static ConexionBD conexionBD;
    private  String server;
    private  String port;
    private  String dataBaseName;
    private  String user;
    private  String password;

    private ConexionBD(String server, String port, String dataBaseName, String user,
                     String password) {
        this.server = server;
        this.port = port;
        this.dataBaseName = dataBaseName;
        this.user = user;
        this.password = password;
    }

    public static ConexionBD getConexionDB() {
        if (conexionBD == null) {
            Properties dbProperties = ConfigHelper.getDatabaseProperties();
            conexionBD = new ConexionBD(dbProperties.getProperty(DataBaseProperties.SERVER.getProperty()),
                    dbProperties.getProperty(DataBaseProperties.PORT.getProperty()),
                    dbProperties.getProperty(DataBaseProperties.DATABASENAME.getProperty()),
                    dbProperties.getProperty(DataBaseProperties.USER.getProperty()),
                    dbProperties.getProperty(DataBaseProperties.PASSWORD.getProperty())
                    );
        }
        return conexionBD;
    }

    public Connection getInstance() throws ClassNotFoundException, SQLException {
        Connection connection;
        String connectionUrl = String.format("jdbc:mysql://%s:%s/%s?useSsl=true",
                server,
                port,
                dataBaseName
        );
        connection = DriverManager.getConnection(connectionUrl, user, password);
        return connection;
    }
}

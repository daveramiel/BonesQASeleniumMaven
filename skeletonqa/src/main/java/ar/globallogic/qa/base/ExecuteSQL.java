package ar.globallogic.qa.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExecuteSQL {

    private ResultSet rs;
    private Statement stmt;
    private Connection conn;


    public List<HashMap<String, Object>> executeSQLQuery(String query) {
        List<HashMap<String, Object>> mapaList = new ArrayList<>();

        try {
            conn = ConexionBD.getConexionDB().getInstance();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                HashMap<String, Object> mapRow = new HashMap<>();
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i < columnCount + 1; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    Object columnValue = rs.getObject(i);
                    mapRow.put(columnName, columnValue);
                }
                mapaList.add(mapRow);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mapaList;
    }
}

package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMetaDataCollector {
    public static void main(String[] args) {
//        getTableByName("IFP_PORTFOLIO");
        getAllTablesByMeta();
    }

    public static void getTableByName(String tableName) {
        String url = "jdbc:oracle:thin:@121.41.112.100:1521:orcl";
        String username = "jgsb";
        String password = "jgsb";
        String driverName = "oracle.jdbc.driver.OracleDriver";

        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, username, password);
            DatabaseMetaData meta = conn.getMetaData();

            System.out.println("\n=== TABLE: " + tableName);

            String catalog = null, schemaPattern = null;
            String columnNamePattern = null;
            ResultSet rsColumns = meta.getColumns(catalog, schemaPattern, tableName, columnNamePattern);

            ResultSet rsPK = meta.getPrimaryKeys(catalog, schemaPattern, tableName);

            StringBuilder stringBuilder = new StringBuilder();
            while (rsColumns.next()) {
                String columnName = rsColumns.getString("COLUMN_NAME");
                String columnType = rsColumns.getString("TYPE_NAME");
                int columnSize = rsColumns.getInt("COLUMN_SIZE");
                String isNullable = rsColumns.getString("IS_NULLABLE");

                stringBuilder.append(columnName);
                System.out.println("\t" + columnName + " - " + columnType + "(" + columnSize + ")" + isNullable);
            }

            while (rsPK.next()) {
                String primaryKeyColumn = rsPK.getString("COLUMN_NAME");
                System.out.println("\tPrimary Key Column: " + primaryKeyColumn);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void getAllTablesByMeta() {
        String url = "jdbc:oracle:thin:@121.41.112.100:1521:orcl";
        String username = "jgsb";
        String password = "jgsb";
        String driverName = "oracle.jdbc.driver.OracleDriver";

        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, username, password);
            DatabaseMetaData meta = conn.getMetaData();

            String catalog = null, schemaPattern = null, tableNames = null;
            String[] types = {"TABLE"};

            ResultSet rsTables = meta.getTables(catalog, schemaPattern, tableNames, types);


            while (rsTables.next()) {
                String tableName = rsTables.getString(3);
                System.out.println("\n=== TABLE: " + tableName);


            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     *
     * https://stackoverflow.com/questions/205736/get-list-of-all-tables-in-oracle
     *
     * @param url
     * @param driver
     * @param user
     * @param password
     * @return
     */
    public List<String> getAllTables(String url,
                                     String driver,
                                     String user,
                                     String password) {

        Connection connection = C3P0Util.getConnection(driver, url, user, password);

        //SELECT owner, table_name FROM all_tables
        // SELECT owner, table_name FROM dba_tables
        String allTablesSql = "SELECT TABLE_NAME FROM USER_TABLES";

        List<String> allTables = new ArrayList<>();
        try {

            ResultSet resultSet = C3P0Util.query(connection, allTablesSql);

            while (resultSet.next()) {
                String table_name = resultSet.getString("TABLE_NAME");
                allTables.add(table_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0Util.close(connection);
        }

        return allTables;
    }

}

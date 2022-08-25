package utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;

/**
 * https://www.programcreek.com/java-api-examples/?api=com.mchange.v2.c3p0.ComboPooledDataSource
 */
public class C3P0Util {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();


    public static void main(String[] args) throws SQLException {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.100.118:3306/business?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "root";
        String password = "123456";
        Connection connection = C3P0Util.getConnection(driver, url, user, password);
        System.out.println(connection);
        System.out.println(connection.getMetaData());
        DatabaseMetaData metadata = connection.getMetaData();
        ResultSet resultSet = metadata.getColumns(null, null, "user_info", null);
        while (resultSet.next()) {
            String name = resultSet.getString("COLUMN_NAME");
            String type = resultSet.getString("TYPE_NAME");
            int size = resultSet.getInt("COLUMN_SIZE");

            System.out.println("Column name: [" + name + "]; type: [" + type + "]; size: [" + size + "]");
        }

//        PreparedStatement preparedStatement = connection.prepareStatement("create table t3 (id bigint(20) not null )");
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into t1 values (1),(2)");
//        preparedStatement.executeUpdate();

//        connection.createStatement();


        C3P0Util.close(connection);

    }

    public static Connection getConnection(String driverClass, String jdbcUrl,
                                           String user, String password) {

        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        try {
            dataSource.setDriverClass(driverClass);
        } catch (PropertyVetoException e) {
            System.out.println("C3p0Plugin start error");
            throw new RuntimeException(e);
        }
        dataSource.setMaxPoolSize(50);
        dataSource.setMinPoolSize(10);
        dataSource.setInitialPoolSize(20);
        dataSource.setMaxIdleTime(30 * 60); // 30 minutes
        dataSource.setAcquireIncrement(50);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setMaxStatements(50);

        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ResultSet query(Connection conn, String sql) throws SQLException {
        PreparedStatement pstmt;

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * https://www.programcreek.com/java-api-examples/?api=com.mchange.v2.c3p0.ComboPooledDataSource
 */
public class C3P0Util {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

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

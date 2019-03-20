package utils;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.val;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtil {

    public static Connection getConnection(String url,
                                           String driverClassName,
                                           String userName,
                                           String password) {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        dataSource.setInitialSize(10);
        dataSource.setMinIdle(10);
        dataSource.setMaxActive(100);

        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);

        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(10);

        Connection connection = null;
        try {
            dataSource.setFilters("stat");
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
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

    public static void main(String[] args) {
        Properties prop = LoadFile.loadByResource("config.properties");
        String url = prop.getProperty("url");
        String driverClassName = prop.getProperty("driver");
        String username = prop.getProperty("username");
//        String password = prop.getProperty("password");

        String sql = "select * from XXL_JOB_QRTZ_CRON_TRIGGERS";

        Connection connection = getConnection(url, driverClassName, username, "");
        try {
            ResultSet rs = query(connection, sql);

            while (rs.next()) {
                val name = rs.getString("SCHED_NAME");
                val age = rs.getString("TRIGGER_GROUP");
                System.out.println(name);
                System.out.println(age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }
}

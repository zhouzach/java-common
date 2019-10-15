package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.uuid.Generators;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

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

        /**
         * 让连接池知道数据库已经断开了，并且自动测试连接查询:
         * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。
         * 如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用
         */
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

    public static Object query(Connection conn, String sql, final ResultSetHandler handler) throws SQLException {
        PreparedStatement pstmt;

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            return handler.handle(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
    }

    public static void execUpdate(Connection conn, String updateStr) {

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(updateStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void execBatchUpdate(Connection conn, String updateStr, final PreparedStatementHandler handler) {

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(updateStr);
            handler.handle(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
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


    public static void run(Connection connection) {
        String sql = "SELECT * FROM data_field where business_type = 'order' and is_condition_field = 1";
        try {
            ResultSetHandler handler = new ResultSetHandler() {
                @Override
                public Object handle(ResultSet rs) {
                    try {
                        while (rs.next()) {
                            UUID uuid = Generators.timeBasedGenerator().generate();
                            String id = uuid.toString().replaceAll("-", "");
                            Long create_at = rs.getLong("create_at");
                            String creator_id = rs.getString("creator_id");
                            String last_modifier_id = rs.getString("last_modifier_id");
                            Long last_modify_at = rs.getLong("last_modify_at");
                            String business_type = "tag_order";
                            String field_category = rs.getString("field_category");
                            String field_category_name = rs.getString("field_category_name");
                            String field_name = rs.getString("field_name");
                            String field_operators = rs.getString("field_operators");
                            String field_type = rs.getString("field_type");
                            String field_values = rs.getString("field_values");
                            Integer is_update_value = rs.getInt("is_update_value");
                            Integer power = rs.getInt("power");
                            String table_name = rs.getString("table_name");
                            Integer is_condition_field = rs.getInt("is_condition_field");
                            Integer is_explore_field = rs.getInt("is_explore_field");
                            String field_show_name = rs.getString("field_show_name");
                            Integer is_updated = rs.getInt("is_updated");
                            System.out.println(id);
                            System.out.println(create_at);
                            System.out.println(creator_id);
                            System.out.println(last_modifier_id);
                            System.out.println(last_modify_at);
                            System.out.println(business_type);
                            System.out.println(field_category);
                            System.out.println(field_category_name);
                            System.out.println(field_name);
                            System.out.println(field_operators);
                            System.out.println(field_type);
                            System.out.println(field_values);
                            System.out.println(is_update_value);
                            System.out.println(power);
                            System.out.println(table_name);
                            System.out.println(is_condition_field);
                            System.out.println(is_explore_field);
                            System.out.println(field_show_name);
                            System.out.println(is_updated);


                            String insertSql = "insert into data_field value(" + "'" + id + "',"
                                    + create_at
                                    + ",'" + creator_id + "',"
                                    + "'" + last_modifier_id + "',"
                                    + last_modify_at + ","
                                    + "'" + business_type + "',"
                                    + "'" + field_category + "',"
                                    + "'" + field_category_name + "',"
                                    + "'" + field_name + "',"
                                    + "'" + field_operators + "',"
                                    + "'" + field_type + "',"
                                    + "'" + field_values + "',"
                                    + is_update_value + ","
                                    + power + ","
                                    + "'" + table_name + "',"
                                    + is_condition_field + ","
                                    + is_explore_field + ","
                                    + "'" + field_show_name + "',"
                                    + is_updated + ")";
                            execUpdate(connection, insertSql);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };

            query(connection, sql, handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertBatch(Connection connection) {
        String insertSql = "INSERT INTO APP.stu VALUES(?, ?, ?, ?)";
        PreparedStatementHandler handler = new PreparedStatementHandler() {
            public void handle(PreparedStatement preparedStmt) {

                try {
                    preparedStmt.setString(1, "hanmei");
                    preparedStmt.setInt(2, 12);
                    preparedStmt.setBigDecimal(3, BigDecimal.valueOf(100.2));
                    preparedStmt.setInt(4, 7);
                    preparedStmt.addBatch();

                    preparedStmt.setString(1, "lilei");
                    preparedStmt.setInt(2, 11);
                    preparedStmt.setBigDecimal(3, BigDecimal.valueOf(101.2));
                    preparedStmt.setInt(4, 8);
                    preparedStmt.addBatch();

                    preparedStmt.executeBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        preparedStmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        execBatchUpdate(connection, insertSql, handler);
    }

    public static void main(String[] args) {
        Properties prop = LoadFile.loadByResource("app.yml");
        String url = prop.getProperty("url");
        String driverClassName = prop.getProperty("driver");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
//
//        String url = "jdbc:mysql://localhost:3306/app?characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
//        String driverClassName = "com.mysql.cj.jdbc.Driver";
//        String username = "root";
//        String password = "";

        Connection connection = getConnection(url, driverClassName, username, password);
//        run(connection);

        insertBatch(connection);
        close(connection);
    }
}

interface PreparedStatementHandler {
    void handle(PreparedStatement ps);
}

interface ResultSetHandler {
    Object handle(ResultSet rs);
}

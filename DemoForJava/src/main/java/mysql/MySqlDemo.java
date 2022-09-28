package mysql;

import java.sql.*;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
public class MySqlDemo {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sgw?useSSL=false&serverTimezone=UTC";

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "mysqlpw";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from user_info");
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String user_name = resultSet.getString("user_name");
                int age = resultSet.getInt("age");
                System.out.printf("id:%d,name:%s,age:%d\n", user_id, user_name, age);
            }
            resultSet.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

}

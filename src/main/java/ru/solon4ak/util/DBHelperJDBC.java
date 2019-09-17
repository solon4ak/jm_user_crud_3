package ru.solon4ak.util;

import java.sql.*;

public class DBHelperJDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/db_example?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String USER_PASSWORD = "root";

    private static Connection connection;

    private static DBHelperJDBC instance;

    private DBHelperJDBC() {
    }

    public static DBHelperJDBC getInstance() {
        if (instance == null) {
            instance = new DBHelperJDBC();
        }
        return instance;
    }

    public Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
        }
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

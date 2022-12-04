package ua.nure.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/phones";
    private static final String USER = "root";
    private static final String PASSWORD = "11017811";


    public static Connection getConnection(boolean autocommit) throws SQLException {

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        connection.setAutoCommit(autocommit);

        if (!autocommit) {

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        }

        return connection;
    }

    public static Connection getConnection() throws SQLException {

        return getConnection(true);
    }
}

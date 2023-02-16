package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    private final String URL = "jdbc:mysql://localhost:3306/java_scrapper?serverTimezone=UTC";
    private final String username = "root";
    private final String password = "";
    private Connection connection = null;

    public DatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, username, password);
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(query);
        return preparedStatement;
    }
}
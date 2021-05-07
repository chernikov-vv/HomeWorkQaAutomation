package ru.fintech.qa.homework.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBClient {

    private Connection connection = null;

    public final Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection("jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1", "sa", "sa");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

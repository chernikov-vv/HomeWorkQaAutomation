package ru.fintech.qa.homework.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {

    public final int executeQueryGetCount(final String sql) {
        Connection connection = new DBClient().getConnection();
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public final boolean executeQueryUpdate(final String sql) {
        Connection connection = new DBClient().getConnection();

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);

            connection.close();
            return true;
        } catch (SQLException throwables) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

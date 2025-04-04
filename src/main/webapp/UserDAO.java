package com.yourpackage.dao;

import java.sql.*;

public class UserDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/postgres"; // or replace 'postgres' with your DB name
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "pgeor012";

    private static final String SELECT_USER = "SELECT * FROM project.users WHERE email = ? AND password = ?";

    public boolean validate(String email, String password) throws SQLException {
        boolean status = false;

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
}
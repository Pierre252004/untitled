package src.dao;

import src.utils.DatabaseConnection;

import java.sql.*;
import java.util.Date;

public class BookingDAO {
    public boolean addBooking(int clientId, int roomNumber, Date startDate, Date endDate) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO BookingHistory(client_id, room_number, start_date, end_date) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, clientId);
        statement.setInt(2, roomNumber);
        statement.setDate(3, new java.sql.Date(startDate.getTime()));
        statement.setDate(4, new java.sql.Date(endDate.getTime()));

        return statement.executeUpdate() > 0;
    }
}
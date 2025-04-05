package src.dao;

import src.models.BookingHistory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingHistoryDAO {
    private final Connection connection;

    public BookingHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBooking(BookingHistory booking) throws SQLException {
        String sql = "INSERT INTO BookingHistory (booking_number, client_id, room_number, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, booking.getBookingNumber());
            stmt.setInt(2, booking.getClientID());
            stmt.setInt(3, booking.getRoomNumber());
            stmt.setDate(4, Date.valueOf(booking.getStartDate()));
            stmt.setDate(5, Date.valueOf(booking.getEndDate()));
            stmt.executeUpdate();
        }
    }

    public List<BookingHistory> getAllBookings() throws SQLException {
        List<BookingHistory> bookings = new ArrayList<>();
        String sql = "SELECT * FROM BookingHistory";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bookings.add(new BookingHistory(
                        rs.getInt("booking_number"),
                        rs.getInt("client_id"),
                        rs.getInt("room_number"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                ));
            }
        }
        return bookings;
    }
}

package src.dao;

import src.models.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private final Connection connection;

    public RoomDAO(Connection connection) {
        this.connection = connection;
    }

    public void addRoom(Room room) throws SQLException {
        String sql = "INSERT INTO Room (room_number, hotel_address, capacity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, room.getRoomNumber());
            stmt.setString(2, room.getHotelAddress());
            stmt.setString(3, room.getCapacity());
            stmt.setDouble(4, room.getPrice());
            stmt.executeUpdate();
        }
    }

    public List<Room> getRoomsByHotel(String hotelAddress) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Room WHERE hotel_address = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hotelAddress);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rooms.add(new Room(
                            rs.getInt("room_number"),
                            rs.getString("hotel_address"),
                            rs.getString("capacity"),
                            rs.getDouble("price")
                    ));
                }
            }
        }
        return rooms;
    }
}


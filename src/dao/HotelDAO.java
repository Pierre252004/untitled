package src.dao;


import src.models.Hotel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private final Connection connection;

    public HotelDAO(Connection connection) {
        this.connection = connection;
    }

    public void addHotel(Hotel hotel) throws SQLException {
        String sql = "INSERT INTO Hotel (hotel_address, chain_address, name, category) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hotel.getHotelAddress());
            stmt.setString(2, hotel.getChainAddress());
            stmt.setString(3, hotel.getName());
            stmt.setInt(4, hotel.getCategory());
            stmt.executeUpdate();
        }
    }

    public List<Hotel> getAllHotels() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM Hotel";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                hotels.add(new Hotel(
                        rs.getString("hotel_address"),
                        rs.getString("chain_address"),
                        rs.getString("name"),
                        rs.getInt("category")
                ));
            }
        }
        return hotels;
    }
}

package src.dao;


import src.models.HotelChain;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelChainDAO {
    private final Connection connection;

    public HotelChainDAO(Connection connection) {
        this.connection = connection;
    }

    public void addHotelChain(HotelChain chain) throws SQLException {
        String sql = "INSERT INTO HotelChain (chain_address, name, num_hotels) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, chain.getChainAddress());
            stmt.setString(2, chain.getName());
            stmt.setInt(3, chain.getNumHotels());
            stmt.executeUpdate();
        }
    }

    public List<HotelChain> getAllHotelChains() throws SQLException {
        List<HotelChain> chains = new ArrayList<>();
        String sql = "SELECT * FROM HotelChain";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                chains.add(new HotelChain(
                        rs.getString("chain_address"),
                        rs.getString("name"),
                        rs.getInt("num_hotels")
                ));
            }
        }
        return chains;
    }
}

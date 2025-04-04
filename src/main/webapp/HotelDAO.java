@Repository
public class HotelDAO {

    @Autowired
    private DataSource dataSource;

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();

        String sql = "SELECT name, address FROM project.Hotels";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Hotel h = new Hotel();
                h.setName(rs.getString("name"));
                h.setAddress(rs.getString("address"));
                hotels.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }
}

package src.test;

import src.dao.*;
import src.models.*;
import src.utils.DatabaseConnection;
import java.sql.Connection;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("✅ Connected to PostgreSQL!");

            // Initialize DAOs
            HotelChainDAO hotelChainDAO = new HotelChainDAO(connection);
            HotelDAO hotelDAO = new HotelDAO(connection);
            RoomDAO roomDAO = new RoomDAO(connection);
            ClientDAO clientDAO = new ClientDAO(connection);
            BookingHistoryDAO bookingDAO = new BookingHistoryDAO(connection);

            // 1️⃣ Insert a Hotel Chain
            HotelChain chain = new HotelChain("123 Main St", "Luxury Stay", 5);
            hotelChainDAO.addHotelChain(chain);
            System.out.println("🏨 Hotel Chain Added: " + chain.getName());

            // 2️⃣ Insert a Hotel
            Hotel hotel = new Hotel("456 King St", "123 Main St", "Grand Hotel", 4);
            hotelDAO.addHotel(hotel);
            System.out.println("🏨 Hotel Added: " + hotel.getName());

            // 3️⃣ Insert a Room
            Room room = new Room(101, "456 King St", "Double", 150.00);
            roomDAO.addRoom(room);
            System.out.println("🛏️ Room Added: #" + room.getRoomNumber());

            // 4️⃣ Insert a Client
            Client client = new Client(1, "John Doe", "john@example.com");
            clientDAO.addClient(client);
            System.out.println("👤 Client Added: " + client.getFullName());

            // 5️⃣ Insert a Booking
            BookingHistory booking = new BookingHistory(1001, 1, 101, "2025-04-01", "2025-04-07");
            bookingDAO.addBooking(booking);
            System.out.println("📖 Booking Added: #" + booking.getBookingNumber());

            // 🔍 Retrieve and print data
            List<HotelChain> chains = hotelChainDAO.getAllHotelChains();
            System.out.println("\n📌 All Hotel Chains:");
            for (HotelChain c : chains) {
                System.out.println("➡️ " + c.getName() + " at " + c.getChainAddress());
            }

            List<Hotel> hotels = hotelDAO.getAllHotels();
            System.out.println("\n📌 All Hotels:");
            for (Hotel h : hotels) {
                System.out.println("➡️ " + h.getName() + " at " + h.getHotelAddress());
            }

            List<Client> clients = clientDAO.getAllClients();
            System.out.println("\n📌 All Clients:");
            for (Client cl : clients) {
                System.out.println("➡️ " + cl.getFullName() + " (Email: " + cl.getEmail() + ")");
            }

            List<BookingHistory> bookings = bookingDAO.getAllBookings();
            System.out.println("\n📌 All Bookings:");
            for (BookingHistory b : bookings) {
                System.out.println("➡️ Booking #" + b.getBookingNumber() + " for Client ID " + b.getClientID());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

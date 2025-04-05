package src.models;

public class Room {
    private int roomNumber;
    private String hotelAddress;
    private String capacity;
    private double price;

    public Room(int roomNumber, String hotelAddress, String capacity, double price) {
        this.roomNumber = roomNumber;
        this.hotelAddress = hotelAddress;
        this.capacity = capacity;
        this.price = price;
    }

    // Getters
    public int getRoomNumber() { return roomNumber; }
    public String getHotelAddress() { return hotelAddress; }
    public String getCapacity() { return capacity; }
    public double getPrice() { return price; }

    // Setters
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setHotelAddress(String hotelAddress) { this.hotelAddress = hotelAddress; }
    public void setCapacity(String capacity) { this.capacity = capacity; }
    public void setPrice(double price) { this.price = price; }
}

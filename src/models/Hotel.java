package src.models;

public class Hotel {
    private String hotelAddress;
    private String chainAddress;
    private String name;
    private int category;

    public Hotel(String hotelAddress, String chainAddress, String name, int category) {
        this.hotelAddress = hotelAddress;
        this.chainAddress = chainAddress;
        this.name = name;
        this.category = category;
    }

    // Getters
    public String getHotelAddress() { return hotelAddress; }
    public String getChainAddress() { return chainAddress; }
    public String getName() { return name; }
    public int getCategory() { return category; }

    // Setters
    public void setHotelAddress(String hotelAddress) { this.hotelAddress = hotelAddress; }
    public void setChainAddress(String chainAddress) { this.chainAddress = chainAddress; }
    public void setName(String name) { this.name = name; }
    public void setCategory(int category) { this.category = category; }
}

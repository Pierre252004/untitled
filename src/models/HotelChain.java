package src.models;

public class HotelChain {
    private String chainAddress;
    private String name;
    private int rating;

    public HotelChain(String chainAddress, String name, int rating) {
        this.chainAddress = chainAddress;
        this.name = name;
        this.rating = rating;
    }

    // Getters
    public String getChainAddress() { return chainAddress; }
    public String getName() { return name; }
    public int getRating() { return rating; }

    // Setters
    public void setChainAddress(String chainAddress) { this.chainAddress = chainAddress; }
    public void setName(String name) { this.name = name; }
    public void setRating(int rating) { this.rating = rating; }
}

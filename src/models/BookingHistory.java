package src.models;

public class BookingHistory {
    private int bookingNumber;
    private int clientID;
    private int roomNumber;
    private String startDate;
    private String endDate;

    public BookingHistory(int bookingNumber, int clientID, int roomNumber, String startDate, String endDate) {
        this.bookingNumber = bookingNumber;
        this.clientID = clientID;
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public int getBookingNumber() { return bookingNumber; }
    public int getClientID() { return clientID; }
    public int getRoomNumber() { return roomNumber; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }

    // Setters
    public void setBookingNumber(int bookingNumber) { this.bookingNumber = bookingNumber; }
    public void setClientID(int clientID) { this.clientID = clientID; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}

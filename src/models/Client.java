package src.models;

public class Client {
    private int clientID;
    private String fullName;
    private String email;

    public Client(int clientID, String fullName, String email) {
        this.clientID = clientID;
        this.fullName = fullName;
        this.email = email;
    }

    // Getters
    public int getClientID() { return clientID; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    // Setters
    public void setClientID(int clientID) { this.clientID = clientID; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
}

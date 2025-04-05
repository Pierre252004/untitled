package src.models;

public class Employee {
    private String ssn;
    private String fullName;
    private String hotelAddress;
    private double salary;

    public Employee(String ssn, String fullName, String hotelAddress, double salary) {
        this.ssn = ssn;
        this.fullName = fullName;
        this.hotelAddress = hotelAddress;
        this.salary = salary;
    }

    // Getters
    public String getSsn() { return ssn; }
    public String getFullName() { return fullName; }
    public String getHotelAddress() { return hotelAddress; }
    public double getSalary() { return salary; }

    // Setters
    public void setSsn(String ssn) { this.ssn = ssn; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setHotelAddress(String hotelAddress) { this.hotelAddress = hotelAddress; }
    public void setSalary(double salary) { this.salary = salary; }
}

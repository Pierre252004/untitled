package src.test;

import src.dao.PersonDAO;
import java.sql.SQLException;

public class TestPersonDAO {
    public static void main(String[] args) {
        try {
            PersonDAO personDAO = new PersonDAO();

            // Test: Add a person
            boolean success = personDAO.addPerson("John Doe");
            System.out.println("Person added: " + success);

            // Test: Check if person exists
            boolean exists = personDAO.personExists("John Doe");
            System.out.println("Person exists: " + exists);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

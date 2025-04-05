package src.servlets;

import src.dao.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // Can be "client", "manager", etc.

        try {
            PersonDAO personDAO = new PersonDAO();
            if (personDAO.personExists(email)) {
                response.sendRedirect("signup.html?error=exists");
            } else {
                personDAO.addPerson(fullName, email, password, role);
                response.sendRedirect("login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signup.html?error=database");
        }
    }
}

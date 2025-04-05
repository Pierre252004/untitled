package src.servlets;

import src.dao.PersonDAO;
import src.models.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PersonDAO personDAO = new PersonDAO();
        Person person;
        try {
            person = personDAO.getPersonByEmailAndPassword(email, password);
            if (person != null) {
                HttpSession session = request.getSession();
                session.setAttribute("person", person);
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("login.html?error=invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.html?error=server");
        }
    }
}

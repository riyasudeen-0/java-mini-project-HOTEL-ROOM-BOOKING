package com.example.hotel.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // MySQL credentials
    private final String DB_URL = "jdbc:mysql://localhost:3306/hotel_booking";
    private final String DB_USER = "hoteluser";                 // your MySQL username
    private final String DB_PASSWORD = "hotelpass";   // your MySQL password

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Insert query
            String sql = "INSERT INTO users (first_name, last_name, email, phone, password) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, password);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                // Success → redirect to success page
                response.sendRedirect("success.html");
            } else {
                // Failed → redirect with error
                response.sendRedirect("signuphotel.html?error=Failed+to+register!");
            }

        } catch (Exception e) {
    e.printStackTrace(); // prints in Output window
    response.sendRedirect("signuphotel.html?error=Server+error!");
}

    }
}

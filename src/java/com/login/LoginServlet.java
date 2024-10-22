package com.login;
import com.hospital.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = DatabaseConnection.getConnection();

            // Query to check if the user exists and fetch the role
            String query = "SELECT * FROM Users WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("role", role);

                // Redirect to appropriate home page based on role
                if (role.equals("doctor")) {
                    response.sendRedirect("doctor_home.jsp");
                } else if (role.equals("patient")) {
                    response.sendRedirect("patient_home.jsp");
                }
            } else {
                // If login fails, redirect back to login page with error
                response.sendRedirect("login.jsp?error=Invalid Credentials");
            }
        } catch (SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}

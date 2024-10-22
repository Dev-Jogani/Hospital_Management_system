package com.login;
import com.hospital.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DoctorRegisterServlet")
public class DoctorRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String specialization = request.getParameter("specialization");
        String availability = request.getParameter("availability");
        String contact = request.getParameter("contact");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        try {
            Connection con = DatabaseConnection.getConnection();
            
            // Insert into Users table
            String userQuery = "INSERT INTO Users (email, password, role) VALUES (?, ?, 'doctor')";
            PreparedStatement userStmt = con.prepareStatement(userQuery);
            userStmt.setString(1, email);
            userStmt.setString(2, password);
            userStmt.executeUpdate();
            
            // Insert into Doctors table
            String doctorQuery = "INSERT INTO Doctors (email, name, specialization, availability, contact_info, date_of_birth, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement doctorStmt = con.prepareStatement(doctorQuery);
            doctorStmt.setString(1, email);
            doctorStmt.setString(2, name);
            doctorStmt.setString(3, specialization);
            doctorStmt.setString(4, availability);
            doctorStmt.setString(5, contact);
            doctorStmt.setString(6, dob);
            doctorStmt.setString(7, gender);
            doctorStmt.executeUpdate();

            response.sendRedirect("doctor_login.jsp");
        } catch (SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}

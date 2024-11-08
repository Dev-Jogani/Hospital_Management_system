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

@WebServlet("/PatientRegisterServlet")
public class PatientRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String medicalHistory = request.getParameter("medical_history");
        String password = request.getParameter("password");

        try {
            Connection con = DatabaseConnection.getConnection();
            
            // Insert into Users table
            String userQuery = "INSERT INTO Users (email, password, role) VALUES (?, ?, 'patient')";
            PreparedStatement userStmt = con.prepareStatement(userQuery);
            userStmt.setString(1, email);
            userStmt.setString(2, password);
            userStmt.executeUpdate();
            
            // Insert into Patients table
            String patientQuery = "INSERT INTO Patients (email, name, date_of_birth, contact_info, gender, medical_history) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement patientStmt = con.prepareStatement(patientQuery);
            patientStmt.setString(1, email);
            patientStmt.setString(2, name);
            patientStmt.setString(3, dob);
            patientStmt.setString(4, contact);
            patientStmt.setString(5, gender);
            patientStmt.setString(6, medicalHistory);
            patientStmt.executeUpdate();

            response.sendRedirect("appointmentBooking.jsp");
        } catch (SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}

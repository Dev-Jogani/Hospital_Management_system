package com.hospital;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String patientName = request.getParameter("patientName");
        String email = request.getParameter("email");
        String disease = request.getParameter("disease");
        String doctorEmail = request.getParameter("doctorEmail");
        String appointmentDate = request.getParameter("appointmentDate");

        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Appointments (patient_name, email, disease, doctor_email, appointment_date, status) " +
                           "VALUES (?, ?, ?, ?, ?, 'pending')";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, patientName);
            pst.setString(2, email);
            pst.setString(3, disease);
            pst.setString(4, doctorEmail);
            pst.setString(5, appointmentDate);
            
            int result = pst.executeUpdate();

            if (result > 0) {
                request.setAttribute("message", "Appointment booked successfully!");
            } else {
                request.setAttribute("message", "Failed to book appointment.");
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while booking the appointment.");
        }
        request.getRequestDispatcher("/jsp/appointmentBooking.jsp").forward(request, response);
    }
}

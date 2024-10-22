package com.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminAppointmentServlet")
public class AdminAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String doctorEmail = (String) session.getAttribute("email");  // Get logged-in doctor's email

        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        String newStatus = request.getParameter("newStatus");

        try (Connection con = DatabaseConnection.getConnection()) {
            // Ensure that the logged-in doctor is assigned to this appointment
            String query = "UPDATE Appointments SET status = ? WHERE appointment_id = ? AND doctor_email = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, newStatus);
            pst.setInt(2, appointmentId);
            pst.setString(3, doctorEmail);  // Ensure doctor can only update their own appointments
            int result = pst.executeUpdate();

            if (result > 0) {
                request.setAttribute("message", "Appointment status updated successfully!");
            } else {
                request.setAttribute("message", "Failed to update appointment status.");
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred.");
        }
        request.getRequestDispatcher("/jsp/adminManageAppointments.jsp").forward(request, response);
    }
}
//so how was the dayyyyy.........
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

@WebServlet("/UpdateAppointmentStatusServlet")
public class UpdateAppointmentStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the appointment ID and new status from the request
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        String newStatus = request.getParameter("status");

        // Establish the database connection
        try (Connection con = DatabaseConnection.getConnection()) {
            // SQL query to update the status of the appointment
            String updateQuery = "UPDATE appointments SET status = ? WHERE appointment_id = ?";
            PreparedStatement pst = con.prepareStatement(updateQuery);
            pst.setString(1, newStatus);       // Set the new status
            pst.setInt(2, appointmentId);      // Set the appointment ID
            int result = pst.executeUpdate();  // Execute the update

            // Check if the update was successful
            if (result > 0) {
                request.setAttribute("message", "Appointment status updated successfully!");
            } else {
                request.setAttribute("message", "Failed to update appointment status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while updating the status.");
        }

        // Redirect back to the doctor dashboard after updating the status
        request.getRequestDispatcher("/doctor_home.jsp").forward(request, response);
    }
}

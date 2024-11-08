package com.hospital;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/UpdateAppointmentStatusServlet")
public class UpdateAppointmentStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        String patientEmail = request.getParameter("patientEmail");
        String newStatus = request.getParameter("status");

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            // Get connection
            conn = DatabaseConnection.getConnection();

            // Update the appointment status
            String sql = "UPDATE appointments SET status = ? WHERE appointment_id = ? OR email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, newStatus);
            pst.setInt(2, appointmentId);
            pst.setString(3, patientEmail);

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("doctor_home.jsp");  // Redirect to the dashboard after update
            } else {
                response.getWriter().println("Failed to update the appointment status.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error updating status: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

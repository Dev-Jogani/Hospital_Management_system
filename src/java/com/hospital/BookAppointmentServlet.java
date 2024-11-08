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

        String patientName = request.getParameter("patient_name");
        String email = request.getParameter("email");
        String disease = request.getParameter("disease");
        String appointmentDate = request.getParameter("appointment_date");

        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO appointments (patient_name, email, disease, appointment_date, status) "
                    + "VALUES (?, ?, ?, ?, 'pending')";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, patientName);
            pst.setString(2, email);
            pst.setString(3, disease);
            pst.setString(4, appointmentDate);

            int result = pst.executeUpdate();

            if (result > 0) {
                request.setAttribute("message", "Appointment booked successfully!");
            } else {
                request.setAttribute("message", "Failed to book appointment.");
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error while booking appointment: " + e.getMessage());
            e.printStackTrace(); // Outputs the detailed stack trace to the console
            request.setAttribute("message", "An error occurred while booking the appointment.");
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

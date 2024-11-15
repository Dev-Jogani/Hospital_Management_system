package com.Patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientDashboard extends HttpServlet {
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        try {
            sqldb.connect();  // Establish database connection

            // Query to get patient details
            String patientQuery = "SELECT * FROM Patients WHERE email = '" + email + "'";
            ResultSet patientRs = sqldb.fetchdata(patientQuery);

            if (patientRs.next()) {
                // Set patient details as request attributes
                request.setAttribute("name", patientRs.getString("name"));
                request.setAttribute("email", patientRs.getString("email"));
                request.setAttribute("disease", patientRs.getString("disease"));
            }

            // Query to get appointments for this patient
            String appointmentQuery = "SELECT * FROM Appointments WHERE email = '" + email + "'";
            ResultSet appointmentRs = sqldb.fetchdata(appointmentQuery);

            // Store appointments in a list of maps
            List<Map<String, String>> appointments = new ArrayList<>();
            while (appointmentRs.next()) {
                Map<String, String> appointment = new HashMap<>();
                appointment.put("appointmentId", appointmentRs.getString("appointment_id"));
                appointment.put("date", appointmentRs.getString("appointment_date"));
                appointment.put("status", appointmentRs.getString("status"));
                appointments.add(appointment);
            }
            request.setAttribute("appointments", appointments);

            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("patientDashboard.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqldb.connclose();  // Close the database connection
        }
    }
}
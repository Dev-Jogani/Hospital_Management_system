/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class PatientDetailsServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientId = request.getParameter("id");
        
        if (patientId == null || patientId.isEmpty()) {
            response.sendRedirect("allPatients.jsp");  // Redirect to the list if no ID is provided
            return;
        }

        try {
            // Connect to the database
            sqldb.connect();

            // Query to get patient details from the Users table and Patients table
            String patientQuery = "SELECT p.name, p.contact_info, u.email, p.medical_history " +
                                  "FROM Patients p JOIN Users u ON p.email = u.email " +
                                  "WHERE p.patient_id = '" + patientId + "'";
            ResultSet patientResult = sqldb.fetchdata(patientQuery);
            String email = "";
            // Check if patient details were found
            if (patientResult.next()) {
                // Store patient details in request attributes
                request.setAttribute("name", patientResult.getString("name"));
                request.setAttribute("email", patientResult.getString("email"));
                email = patientResult.getString("email");
                request.setAttribute("contactInfo", patientResult.getString("contact_info"));
                request.setAttribute("medicalHistory", patientResult.getString("medical_history"));
            } else {
                response.sendRedirect("allPatients.jsp"); // Redirect if no patient found
                return;
            }

            // Query to get all appointments for the patient // email base karvu
            String appointmentQuery = "SELECT a.appointment_id, a.appointment_date, a.status " +
                                      "FROM Appointments a " +
                                      "WHERE a.email = '" + email +"'";
            ResultSet appointmentResult = sqldb.fetchdata(appointmentQuery);

            // Process appointments
            List<Map<String, String>> appointments = new ArrayList<>();
            while (appointmentResult.next()) {
                Map<String, String> appointment = new HashMap<>() ;
                appointment.put("appointmentId", appointmentResult.getString("appointment_id"));
                appointment.put("date", appointmentResult.getString("appointment_date"));
                appointment.put("status", appointmentResult.getString("status"));
                appointments.add(appointment);
            }

            // Set appointments list in request attributes
            request.setAttribute("appointments", appointments);

            // Close the database connection
            sqldb.connclose();

            // Forward to JSP page to display patient details
            request.getRequestDispatcher("patientDetails.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
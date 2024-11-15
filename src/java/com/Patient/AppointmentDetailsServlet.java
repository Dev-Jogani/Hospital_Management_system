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
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author DELL
 */
public class AppointmentDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appointmentId = request.getParameter("id");
        
        if (appointmentId != null) {
            sqldb.connect();
            try {
                String query = "SELECT patient_name, email, disease, appointment_date, status " +
                               "FROM Appointments WHERE appointment_id = '" + appointmentId +"'";
                ResultSet rs = sqldb.fetchdata(query);

                if (rs.next()) {
                    // Setting appointment details as request attributes
                    request.setAttribute("patientName", rs.getString("patient_name"));
                    request.setAttribute("email", rs.getString("email"));
                    request.setAttribute("disease", rs.getString("disease"));
                    request.setAttribute("appointmentDate", rs.getTimestamp("appointment_date"));
                    request.setAttribute("status", rs.getString("status"));
                } else {
                    request.setAttribute("error", "Appointment not found.");
                }
                
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error retrieving appointment details.");
            } finally {
                sqldb.connclose();
            }
        } else {
            request.setAttribute("error", "No appointment ID provided.");
        }

        // Forwarding to appointmentDetails.jsp
        request.getRequestDispatcher("/appointmentDetails.jsp").forward(request, response);
    }
}
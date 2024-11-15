package com.Patient;

import jakarta.servlet.RequestDispatcher;
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

public class PatientSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchName = request.getParameter("searchName");

        if (searchName != null && !searchName.trim().isEmpty()) {
            sqldb.connect(); // Establish database connection

            String query = "SELECT * FROM Patients WHERE name LIKE '%" + searchName + "%'";
            try {
                ResultSet rs = sqldb.fetchdata(query);

                // Convert ResultSet to List of Maps
                List<Map<String, Object>> patients = new ArrayList<>();
                while (rs.next()) {
                    Map<String, Object> patient = new HashMap<>();
                    patient.put("name", rs.getString("name"));
                    patient.put("email", rs.getString("email"));
                    patient.put("contact_info", rs.getString("contact_info"));
                    patient.put("patient_id", rs.getInt("patient_id"));
                    patients.add(patient);
                }

                // Set attributes for JSP
                request.setAttribute("patients", patients); // Pass the list of patients
                request.setAttribute("viewType", "search");

                // Forward to JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("allPatients.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                sqldb.connclose(); // Close the database connection
            }
        } else {
            // Redirect to AllPatients if searchName is empty or null
            response.sendRedirect(request.getContextPath() + "/AllPatients");
        }
    }
}
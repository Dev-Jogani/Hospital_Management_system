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

public class AllPatients extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sqldb.connect(); // Establish database connection

            // Query to get all patients
            String query = "SELECT * FROM Patients";
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
            request.setAttribute("viewType", "all");

            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("allPatients.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqldb.connclose(); // Close the database connection
        }
    }
}
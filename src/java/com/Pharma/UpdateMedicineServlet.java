package com.Pharma;

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

public class UpdateMedicineServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sqldb.connect(); // Connect to the database

            // Query to retrieve all medicines
            String query = "SELECT medicine_id, name FROM Medicines";
            ResultSet rs = sqldb.fetchdata(query);

            // List to hold medicines
            List<Map<String, String>> medicines = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> medicine = new HashMap<>();
                medicine.put("id", rs.getString("medicine_id"));
                medicine.put("name", rs.getString("name"));
                medicines.add(medicine);
            }

            request.setAttribute("medicines", medicines);

            // Forward to JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateMedicine.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqldb.connclose(); // Close the connection
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String medicineId = request.getParameter("medicineId");
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            sqldb.connect(); // Connect to the database

            // Update medicine quantity in the database
            String updateQuery = "UPDATE Medicines SET quantity = quantity + " + newQuantity + " WHERE medicine_id = " + medicineId;
            int result = sqldb.iud_data(updateQuery);

            if (result > 0) {
                request.setAttribute("message", "Medicine quantity updated successfully!");
            } else {
                request.setAttribute("error", "Failed to update medicine quantity.");
            }
            
            doGet(request, response); // Refresh medicines list

        }  finally {
            sqldb.connclose(); // Close the connection
        }
    }
}
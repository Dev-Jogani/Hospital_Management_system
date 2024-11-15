package com.Pharma;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMedicineServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String expirationDate = request.getParameter("expiration_date");
        String section = request.getParameter("section");

        // Connect to the database
        sqldb.connect();

        try {
            // Check if the medicine already exists
            String checkQuery = "SELECT * FROM Medicines WHERE name = '"+name+"'";
            
            ResultSet rs = sqldb.fetchdata(checkQuery);

            if (rs.next()) {
                // Medicine already exists, update the quantity
                int existingQuantity = rs.getInt("quantity");
                int newQuantity = existingQuantity + quantity;

                String updateQuery = "UPDATE Medicines SET quantity = ?, price = ?, expiration_date = ?, section = ? WHERE name = ?";
                PreparedStatement updateStmt = sqldb.conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, newQuantity);
                updateStmt.setDouble(2, price);
                updateStmt.setString(3, expirationDate);
                updateStmt.setString(4, section);
                updateStmt.setString(5, name);
                updateStmt.executeUpdate();
                updateStmt.close();

                request.setAttribute("message", "Medicine already exists. Quantity updated to " + newQuantity + ".");
            } else {
                // Insert new medicine record
                String insertQuery = "INSERT INTO Medicines (name, quantity, price, expiration_date, section) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = sqldb.conn.prepareStatement(insertQuery);
                insertStmt.setString(1, name);
                insertStmt.setInt(2, quantity);
                insertStmt.setDouble(3, price);
                insertStmt.setString(4, expirationDate);
                insertStmt.setString(5, section);
                insertStmt.executeUpdate();
                insertStmt.close();
                
                request.setAttribute("message", "Medicine quantity updated successfully!");
            }
            

        } catch (SQLException e) {
            request.setAttribute("error", "Something wrong with DB");
            e.printStackTrace();
        } finally {
            sqldb.connclose();
        }    
        // Forward to confirmation page or display message on the same form page
        RequestDispatcher dispatcher = request.getRequestDispatcher("addNewMedicineForm.jsp");
        dispatcher.forward(request, response);
    }
}
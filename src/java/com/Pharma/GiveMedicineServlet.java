package com.Pharma;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiveMedicineServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] medicineIds = request.getParameterValues("medicineIds");
        String[] quantities = request.getParameterValues("quantities");

        if (medicineIds != null && quantities != null) {
            List<Map<String, Object>> dispensedMedicines = new ArrayList<>();
            double totalBill = 0.0;

            try{
                sqldb.connect();
                String updateQuery = "UPDATE Medicines SET quantity = quantity - ? WHERE medicine_id = ?";
                String selectQuery = "SELECT name, price FROM Medicines WHERE medicine_id = ?";
                PreparedStatement updateStmt = sqldb.conn.prepareStatement(updateQuery);
                PreparedStatement selectStmt = sqldb.conn.prepareStatement(selectQuery);

                for (int i = 0; i < medicineIds.length; i++) {
                    int medicineId = Integer.parseInt(medicineIds[i]);
                    
                    int quantityToGive;
                    if(quantities[i].equals("0") || quantities[i].equals("") || quantities[i] == null){
                        quantityToGive = 0;
                    }else{
                        quantityToGive = Integer.parseInt(quantities[i]);
                    }

                    // Fetch the price and name of the medicine
                    selectStmt.setInt(1, medicineId);
                    ResultSet rs = selectStmt.executeQuery();

                    if (rs.next()) {
                        String name = rs.getString("name");
                        double price = rs.getDouble("price");
                        double totalPrice = price * quantityToGive;
                        totalBill += totalPrice;

                        // Add to dispensed medicines list
                        Map<String, Object> medicine = new HashMap<>();
                        medicine.put("name", name);
                        medicine.put("quantity", quantityToGive);
                        medicine.put("price", price);
                        medicine.put("totalPrice", totalPrice);
                        dispensedMedicines.add(medicine);

                        // Update the quantity in the database
                        updateStmt.setInt(1, quantityToGive);
                        updateStmt.setInt(2, medicineId);
                        updateStmt.executeUpdate();
                    }
                }
                
                // Set attributes for JSP
                request.setAttribute("dispensedMedicines", dispensedMedicines);
                request.setAttribute("totalBill", totalBill);

                // Forward to JSP to display the summary and print button
                request.getRequestDispatcher("printReceipt.jsp").forward(request, response);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
}
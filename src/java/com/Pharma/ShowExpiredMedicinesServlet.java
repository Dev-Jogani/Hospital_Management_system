package com.Pharma;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/showExpiredMedicines")
public class ShowExpiredMedicinesServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> expiredMedicines = new ArrayList<>();
        
        try {
            sqldb.connect();
            String sql = "SELECT * FROM Medicines WHERE expiration_date < '"+java.sql.Date.valueOf(LocalDate.now())+"'";
            ResultSet rs = sqldb.fetchdata(sql);
            

            while (rs.next()) {
                Map<String, Object> medicine = new HashMap<>();
                medicine.put("medicine_id", rs.getInt("medicine_id"));
                medicine.put("name", rs.getString("name"));
                medicine.put("quantity", rs.getInt("quantity"));
                medicine.put("price", rs.getBigDecimal("price"));
                medicine.put("expiration_date", rs.getDate("expiration_date"));
                medicine.put("section", rs.getString("section"));

                expiredMedicines.add(medicine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("expiredMedicines", expiredMedicines);
        request.getRequestDispatcher("/showExpiredMedicines.jsp").forward(request, response);
    }
}
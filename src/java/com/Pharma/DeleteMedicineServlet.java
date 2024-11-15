package com.Pharma;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteMedicineServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int medicineId = Integer.parseInt(request.getParameter("medicineId"));

        try {
            sqldb.connect();
            String sql = "DELETE FROM Medicines WHERE medicine_id = '"+medicineId+"'";
            sqldb.iud_data(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back to the showExpiredMedicines page
        response.sendRedirect(request.getContextPath() + "/showExpiredMedicines");
    }
}
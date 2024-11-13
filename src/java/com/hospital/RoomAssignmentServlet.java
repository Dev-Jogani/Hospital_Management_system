package com.hospital;

import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/RoomAssignmentServlet")
public class RoomAssignmentServlet extends HttpServlet {
    
    // Handle GET requests to display room assignments and status
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String patientName = request.getParameter("patient_name");

        try {
            // Get database connection
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            
            String sql;
            PreparedStatement pst;

            if (patientName != null && !patientName.trim().isEmpty()) {
                // Query to get room assignments by patient name
                sql = "SELECT room_number, room_type, status, patient_name, assignment_date, end_date, assigned_days " +
                      "FROM room_assignments WHERE patient_name = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, patientName);
            } else {
                // Default query to get all room assignments
                sql = "SELECT room_number, room_type, status, patient_name, assignment_date, end_date, assigned_days " +
                      "FROM room_assignments";
                pst = conn.prepareStatement(sql);
            }

            ResultSet rs = pst.executeQuery();

            out.println("<h2>Room Assignment Dashboard</h2>");
            out.println("<form method='get' action='RoomAssignmentServlet'>");
            out.println("    <label for='patient_name'>Search by Patient Name: </label>");
            out.println("    <input type='text' name='patient_name'>");
            out.println("    <input type='submit' value='Search'>");
            out.println("</form>");
            
            out.println("<table border='1'>");
            out.println("<tr><th>Room Number</th><th>Room Type</th><th>Status</th><th>Patient Name</th><th>Assignment Days</th><th>Actions</th></tr>");

            // Loop through results and display room status
            while (rs.next()) {
                String roomNumber = rs.getString("room_number");
                String roomType = rs.getString("room_type");
                String status = rs.getString("status");
                String currentPatientName = rs.getString("patient_name");
                Date assignmentDate = rs.getDate("assignment_date");
                Date endDate = rs.getDate("end_date");
                int assignedDays = rs.getInt("assigned_days");

                String statusColor = status.equalsIgnoreCase("Occupied") ? "red" : "green";
                String assignmentDays = assignmentDate != null ? assignedDays + " days" : "N/A";
                
                out.println("<tr style='background-color:" + (statusColor.equals("red") ? "lightcoral" : "lightgreen") + ";'>");
                out.println("<td>" + roomNumber + "</td>");
                out.println("<td>" + roomType + "</td>");
                out.println("<td style='color:" + statusColor + ";'>" + status + "</td>");
                out.println("<td>" + (currentPatientName != null ? currentPatientName : "N/A") + "</td>");
                out.println("<td>" + (assignmentDate != null ? assignmentDays : "N/A") + "</td>");
                
                // Actions: Assign, Extend, Set Vacant
                out.println("<td>");
                if ("Vacant".equalsIgnoreCase(status)) {
                    out.println("<form action='RoomAssignmentServlet' method='POST'>");
                    out.println("<input type='hidden' name='room_number' value='" + roomNumber + "'>");
                    out.println("<input type='text' name='patient_id' placeholder='Patient ID' required>");
                    out.println("<input type='text' name='patient_name' placeholder='Patient Name' required>");
                    out.println("<input type='number' name='assigned_days' placeholder='Days to Assign' required>");
                    out.println("<input type='submit' value='Assign Room'>");
                    out.println("</form>");
                } else {
                    out.println("<form action='RoomAssignmentServlet' method='POST'>");
                    out.println("<input type='hidden' name='room_number' value='" + roomNumber + "'>");
                    out.println("<input type='number' name='extend_days' placeholder='Extend by Days' required>");
                    out.println("<input type='submit' value='Extend Assignment'>");
                    out.println("</form>");
                }
                out.println("<form action='RoomAssignmentServlet' method='POST'>");
                out.println("<input type='hidden' name='room_number' value='" + roomNumber + "'>");
                out.println("<input type='submit' name='vacant' value='Set Vacant'>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        } finally {
            out.close();
        }
    }
    
    // Handle POST requests for room assignment and status updates
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomNumber = request.getParameter("room_number");
        String patientId = request.getParameter("patient_id");
        String patientName = request.getParameter("patient_name");
        String assignedDays = request.getParameter("assigned_days");
        String extendDays = request.getParameter("extend_days");
        String vacant = request.getParameter("vacant");

        try {
            Connection conn = DatabaseConnection.getConnection();
            
            // Assign Room to Patient
            if (patientId != null && patientName != null && assignedDays != null) {
                String sql = "SELECT room_id FROM room_assignments WHERE room_number = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, roomNumber);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String updateSql = "UPDATE room_assignments SET patient_id = ?, patient_name = ?, status = 'Occupied', " +
                                       "assignment_date = NOW(), end_date = DATE_ADD(NOW(), INTERVAL ? DAY), assigned_days = ? WHERE room_number = ?";
                    pst = conn.prepareStatement(updateSql);
                    pst.setInt(1, Integer.parseInt(patientId));
                    pst.setString(2, patientName);
                    pst.setInt(3, Integer.parseInt(assignedDays));
                    pst.setInt(4, Integer.parseInt(assignedDays));
                    pst.setString(5, roomNumber);
                    pst.executeUpdate();
                }
            }
            
            // Extend Room Assignment
            if (extendDays != null) {
                String sql = "UPDATE room_assignments SET end_date = DATE_ADD(end_date, INTERVAL ? DAY) WHERE room_number = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(extendDays));
                pst.setString(2, roomNumber);
                pst.executeUpdate();
            }
            
            // Set Room Vacant
            if (vacant != null) {
                String sql = "UPDATE room_assignments SET status = 'Vacant', patient_id = NULL, patient_name = NULL, " +
                             "assignment_date = NULL, end_date = NULL, assigned_days = NULL WHERE room_number = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, roomNumber);
                pst.executeUpdate();
            }

            conn.close();
            response.sendRedirect("RoomAssignmentServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }
}

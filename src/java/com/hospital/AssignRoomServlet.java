
package com.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AssignRoomServlet")
public class AssignRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        int patientId = Integer.parseInt(request.getParameter("patientId"));

        try (Connection con = DatabaseConnection.getConnection()) {
            // Update Room Status to "occupied"
            String updateRoomStatusQuery = "UPDATE Rooms SET status = 'occupied' WHERE room_id = ?";
            PreparedStatement pst1 = con.prepareStatement(updateRoomStatusQuery);
            pst1.setInt(1, roomId);
            pst1.executeUpdate();

            // Insert into RoomAssignments
            String insertAssignmentQuery = "INSERT INTO RoomAssignments (patient_id, room_id, assignment_date) VALUES (?, ?, NOW())";
            PreparedStatement pst2 = con.prepareStatement(insertAssignmentQuery);
            pst2.setInt(1, patientId);
            pst2.setInt(2, roomId);
            pst2.executeUpdate();

            request.setAttribute("message", "Room assigned successfully!");
            request.getRequestDispatcher("/adminManageRooms.jsp").forward(request, response);

            pst1.close();
            pst2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while assigning the room.");
            request.getRequestDispatcher("/adminManageRooms.jsp").forward(request, response);
        }
    }
}

<%@ page import="java.sql.*" %>
<%@ page import="com.hospital.DatabaseConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Manage Rooms</title>
</head>
<body>
    <h1>Room Management</h1>

    <h2>Available Rooms</h2>
    <table border="1">
        <tr>
            <th>Room Number</th>
            <th>Room Type</th>
            <th>Status</th>
            <th>Assign Patient</th>
        </tr>
        <%
            Connection con = DatabaseConnection.getConnection();
            String query = "SELECT * FROM Rooms";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("room_number") %></td>
            <td><%= rs.getString("room_type") %></td>
            <td><%= rs.getString("status") %></td>
            <td>
                <form action="AssignRoomServlet" method="post">
                    <input type="hidden" name="roomId" value="<%= rs.getInt("room_id") %>">
                    <label for="patientId">Patient ID:</label>
                    <input type="number" name="patientId" required>
                    <input type="submit" value="Assign">
                </form>
            </td>
        </tr>
        <%
            }
            rs.close();
            pst.close();
            con.close();
        %>
    </table>
</body>
</html>

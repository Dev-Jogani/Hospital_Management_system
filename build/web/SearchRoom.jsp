<%@ page import="java.sql.*" %>
<%@ page import="com.hospital.DatabaseConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Room</title>
</head>
<body>
    <h1>Search for Patient's Room</h1>

    <%
        String patientId = request.getParameter("patientId");
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT Rooms.room_number, Rooms.room_type FROM RoomAssignments " +
                       "JOIN Rooms ON RoomAssignments.room_id = Rooms.room_id " +
                       "WHERE RoomAssignments.patient_id = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, Integer.parseInt(patientId));
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
    %>
    <p>Patient is assigned to Room Number: <%= rs.getString("room_number") %></p>
    <p>Room Type: <%= rs.getString("room_type") %></p>
    <%
        } else {
    %>
    <p>No room found for the given patient ID.</p>
    <%
        }
        rs.close();
        pst.close();
        con.close();
    %>

</body>
</html>

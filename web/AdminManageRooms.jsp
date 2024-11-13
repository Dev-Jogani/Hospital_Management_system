<%@ page import="java.sql.*" %>
<%@ page import="com.hospital.DatabaseConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Manage Rooms</title>
</head>
<body>
    <h1>Room Management</h1>

    <!-- Search Form for Room by Patient Name -->
    <h2>Search Room by Patient Name</h2>
    <form method="get">
        <label for="patientName">Patient Name:</label>
        <input type="text" name="patientName" required>
        <input type="submit" value="Search">
    </form>

    <%
        String patientName = request.getParameter("patientName");
        if (patientName != null && !patientName.trim().isEmpty()) {
            // Query to find room details based on patient name
            Connection con = DatabaseConnection.getConnection();
            String searchQuery = "SELECT Rooms.room_number, Rooms.room_type, Rooms.status, Patients.patient_name "
                               + "FROM Rooms "
                               + "JOIN RoomAssignments ON Rooms.room_id = RoomAssignments.room_id "
                               + "JOIN Patients ON RoomAssignments.patient_id = Patients.patient_id "
                               + "WHERE Patients.patient_name = ?";
            PreparedStatement searchPst = con.prepareStatement(searchQuery);
            searchPst.setString(1, patientName);
            ResultSet searchRs = searchPst.executeQuery();

            if (searchRs.next()) {
    %>
                <!-- Display search results -->
                <h3>Search Results for "<%= patientName %>"</h3>
                <table border="1">
                    <tr>
                        <th>Room Number</th>
                        <th>Room Type</th>
                        <th>Status</th>
                        <th>Patient Name</th>
                    </tr>
                    <tr>
                        <td><%= searchRs.getString("room_number") %></td>
                        <td><%= searchRs.getString("room_type") %></td>
                        <td><%= searchRs.getString("status") %></td>
                        <td><%= searchRs.getString("patient_name") %></td>
                    </tr>
                </table>
    <%
            } else {
    %>
                <p>No room found for patient name "<%= patientName %>".</p>
    <%
            }
            searchRs.close();
            searchPst.close();
            con.close();
        }
    %>

    <!-- Available Rooms Table -->
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

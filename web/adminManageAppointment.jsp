<%@ page import="java.sql.*" %>
<%@ page import="com.hospital.DatabaseConnection" %>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Appointments</title>
</head>
<body>
    <h1>Doctor - Manage Appointments</h1>

    <table border="1">
        <tr>
            <th>Appointment ID</th>
            <th>Patient Name</th>
            <th>Disease</th>
            <th>Appointment Date</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <%
            String doctorEmail = (String) session.getAttribute("email");  // Get logged-in doctor's email
            try {
                Connection con = DatabaseConnection.getConnection();
                // Query to get appointments relevant to the logged-in doctor
                String query = "SELECT * FROM Appointments WHERE doctor_email = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, doctorEmail);
                ResultSet rs = pst.executeQuery();
                
                while (rs.next()) {
        %>
                    <tr>
                        <td><%= rs.getInt("appointment_id") %></td>
                        <td><%= rs.getString("patient_name") %></td>
                        <td><%= rs.getString("disease") %></td>
                        <td><%= rs.getString("appointment_date") %></td>
                        <td><%= rs.getString("status") %></td>
                        <td>
                            <form action="AdminAppointmentServlet" method="post">
                                <input type="hidden" name="appointmentId" value="<%= rs.getInt("appointment_id") %>">
                                <select name="newStatus">
                                    <option value="confirmed">Confirm</option>
                                    <option value="completed">Complete</option>
                                    <option value="canceled">Cancel</option>
                                </select>
                                <input type="submit" value="Update">
                            </form>
                        </td>
                    </tr>
        <%
                }
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </table>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.hospital.DatabaseConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Appointments</title>
</head>
<body>
    <h1>Your Appointments</h1>

    <form action="viewAppointments.jsp" method="get">
        <label for="email">Enter Your Email:</label>
        <input type="email" id="email" name="email" required>
        <input type="submit" value="View Appointments">
    </form>

    <%
        String email = request.getParameter("email");
        if (email != null) {
            try (Connection con = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM Appointments WHERE email = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
    %>
                    <table border="1">
                        <tr>
                            <th>Appointment ID</th>
                            <th>Patient Name</th>
                            <th>Disease</th>
                            <th>Doctor Email</th>
                            <th>Appointment Date</th>
                            <th>Status</th>
                        </tr>
                        <tr>
                            <td><%= rs.getInt("appointment_id") %></td>
                            <td><%= rs.getString("patient_name") %></td>
                            <td><%= rs.getString("disease") %></td>
                            <td><%= rs.getString("doctor_email") %></td>
                            <td><%= rs.getString("appointment_date") %></td>
                            <td><%= rs.getString("status") %></td>
                        </tr>
                    </table>
    <%
                } else {
                    out.println("<p>No appointments found for the given email.</p>");
                }
                rs.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<p>An error occurred while retrieving appointments.</p>");
            }
        }
    %>
</body>
</html>

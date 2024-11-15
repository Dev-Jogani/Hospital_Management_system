<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.hospital.DatabaseConnection" %>
<%@ page session="true" %> <!-- Ensure session is available -->
<!DOCTYPE html>
<html>
<head>
    <title>View Appointments</title>
    <style>
        /* Custom CSS with color code #5D9A9B */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
        }
        
        h1 {
            color: #5D9A9B; /* Custom color */
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #5D9A9B; /* Custom color for header */
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        .message {
            color: #5D9A9B; /* Custom color for messages */
        }

        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Your Appointments</h1>

    <%
        // Retrieve the logged-in user's email from the session
        String email = (String) session.getAttribute("email");

        if (email != null) {
            try (Connection con = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM appointments WHERE email = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
    %>
                    <table>
                        <tr>
                            <th>Appointment ID</th>
                            <th>Patient Name</th>
                            <th>Disease</th>
                            <th>Appointment Date</th>
                            <th>Status</th>
                        </tr>
                        <tr>
                            <td><%= rs.getInt("appointment_id") %></td>
                            <td><%= rs.getString("patient_name") %></td>
                            <td><%= rs.getString("disease") %></td>
                            <td><%= rs.getString("appointment_date") %></td>
                            <td><%= rs.getString("status") %></td>
                        </tr>
                    </table>
    <%
                } else {
                    out.println("<p class='message'>No appointments found for the given email.</p>");
                }
                rs.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<p class='error'>An error occurred while retrieving appointments.</p>");
            }
        } else {
            out.println("<p class='error'>You need to log in first to view your appointments.</p>");
        }
    %>
</body>
</html>

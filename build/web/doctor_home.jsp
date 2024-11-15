<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*, java.sql.*, com.hospital.DatabaseConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Doctor Dashboard</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div class="doctor-dashboard-container">
            <h2>Welcome, Doctor!</h2>

            <%
                if (session != null) {
                    String doctorEmail = (String) session.getAttribute("email");

                    if (doctorEmail != null) {
                        String doctorName = null;
                        String doctorSpecialization = null;
                        Connection conn = null;
                        PreparedStatement pst = null;
                        ResultSet rs = null;

                        try {
                            conn = DatabaseConnection.getConnection();
                            String sql = "SELECT name, specialization FROM doctors WHERE email = ?";
                            pst = conn.prepareStatement(sql);
                            pst.setString(1, doctorEmail);

                            rs = pst.executeQuery();
                            if (rs.next()) {
                                doctorName = rs.getString("name");
                                doctorSpecialization = rs.getString("specialization");
                            }

                            if (doctorName != null && doctorSpecialization != null) {
            %>
            <p>Doctor's Name: <%= doctorName%></p>
            <p>Email: <%= doctorEmail%></p>
            <p>Specialization: <%= doctorSpecialization%></p>

            <h3>Your Appointments</h3>
            <table>
                <tr>
                    <th>Patient Name</th>
                    <th>Patient Email</th>
                    <th>Disease</th>
                    <th>Appointment Date</th>
                    <th>Status</th>
                    <th>Update Status</th>
                </tr>
                <%
                    String appointmentSql = "SELECT appointment_id, patient_name, email, disease, appointment_date, status FROM appointments WHERE disease = ? AND status != 'visited'";
                    PreparedStatement appointmentPst = conn.prepareStatement(appointmentSql);
                    appointmentPst.setString(1, doctorSpecialization);
                    ResultSet appointmentRs = appointmentPst.executeQuery();

                    while (appointmentRs.next()) {
                        int appointmentId = appointmentRs.getInt("appointment_id");
                        String patientName = appointmentRs.getString("patient_name");
                        String patientEmail = appointmentRs.getString("email");
                        String disease = appointmentRs.getString("disease");
                        java.sql.Date appointmentDate = appointmentRs.getDate("appointment_date");
                        String status = appointmentRs.getString("status");
                %>
                <tr>
                    <td><%= patientName%></td>
                    <td><%= patientEmail%></td>
                    <td><%= disease%></td>
                    <td><%= appointmentDate%></td>
                    <td><%= status%></td>
                    <td>
                        <!-- Form for updating the status -->
                        <form action="UpdateAppointmentStatusServlet" method="post">
                            <input type="hidden" name="appointmentId" value="<%= appointmentId%>">
                            <input type="hidden" name="patientEmail" value="<%= patientEmail%>">
                            <select name="status">
                                <option value="pending" <%= "pending".equals(status) ? "selected" : ""%>>Pending</option>
                                <option value="confirmed" <%= "confirmed".equals(status) ? "selected" : ""%>>Confirmed</option>
                                <option value="cancelled" <%= "cancelled".equals(status) ? "selected" : ""%>>Cancelled</option>
                                <option value="visited" <%= "visited".equals(status) ? "selected" : ""%>>Visited</option>
                            </select>
                            <button type="submit">Update</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                    appointmentRs.close();
                    appointmentPst.close();
                %>
            </table>
            <%
            } else {
            %>
            <p>Doctor information not found. Please login again.</p>
            <%
                            }
                        } catch (Exception e) {
                            out.println("<p>Error retrieving appointments. Please try again later.</p>");
                            e.printStackTrace();
                        } finally {
                            try {
                                if (rs != null) {
                                    rs.close();
                                }
                                if (pst != null) {
                                    pst.close();
                                }
                                if (conn != null && !conn.isClosed()) {
                                    conn.close();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        out.println("<p>Session has expired. Please login again.</p>");
                    }
                }
            %>
            
            <p><a href="LogoutServlet">Logout</a></p>
        </div>
    </body>
</html>

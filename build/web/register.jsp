<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register as:</h2>

    <!-- Form to select role -->
    <form action="register.jsp" method="get">
        <button type="submit" name="role" value="doctor">Doctor</button>
        <button type="submit" name="role" value="patient">Patient</button>
    </form>

    <%
        // Role processing logic
        String role = request.getParameter("role");
        if (role != null) {
            if (role.equals("doctor")) {
                response.sendRedirect("doctor_register.jsp");
            } else if (role.equals("patient")) {
                response.sendRedirect("patient_register.jsp");
            }
        }
    %>
</body>
</html>
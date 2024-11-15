<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hospital Management System</title>
</head>
<body>

    <h2>Hospital Management System Dashboard</h2>

    <!-- Button to redirect to all patient details -->
    <form action="PatientSection" method="get">
        <button type="submit">View All Patient Details</button>
    </form>

    <br>

    <!-- Button to redirect to room assignment dashboard -->
    <form action="RoomAssignmentServlet" method="get">
        <button type="submit">Room Assignment Dashboard</button>
    </form>

</body>
</html>

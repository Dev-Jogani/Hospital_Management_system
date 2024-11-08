<!DOCTYPE html>
<html>
<head>
    <title>Hospital Management System</title>
</head>
<body>
    <h1>Welcome to the Hospital Management System</h1>

    <!-- Display success or error message from previous action -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <p style="color: green;"><%= message %></p>
    <%
        }
    %>

    <!-- Login Form Section -->
    <h2>Login</h2>
    <form action="login.jsp" method="get">
        <input type="submit" value="Go to Login Page">
    </form>

    <br>

    <p>Please select your role:</p>
    <br>

    <!-- Admin Side: Manage Rooms -->
    <form action="RoomAssignmentServlet" method="get">
        <input type="submit" value="Admin - Manage Rooms">
    </form>

    <br>

    <!-- Admin Side: Search Room by Patient -->
    <form action="SearchRoom.jsp" method="get">
        <label for="patientId">Enter Patient ID to Find Room:</label>
        <input type="number" id="patientId" name="patientId" required>
        <input type="submit" value="Search Room">
    </form>
</body>
</html>

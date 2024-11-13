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
 
</body>
</html>

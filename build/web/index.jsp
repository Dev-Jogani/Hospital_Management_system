<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hospital Management System</title>
            <link rel="stylesheet" href="styles.css">
</head>
<body>

<header>
    <h1>Welcome to the Hospital Management System</h1>
    <div class="clock" id="clock"></div>
</header>

<div class="container">
    <div class="content">
        <!-- Display success or error message from previous action -->
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
            <p style="color: green;"><%= message %></p>
        <%
            }
        %>

        <!-- Login and Registration Options -->
        <div class="section">
            <h2>Login or Register</h2>
            <p>Please login to access your account or register if you don't have one.</p>
            <a href="login.jsp" class="btn">Login</a>
            <a href="register.jsp" class="btn">Register</a>
        </div>

        <!-- Basic Medical Regulations -->
        <div class="section medical-regulations">
            <h2>Basic Medical Regulations</h2>
            <ul>
                <li>Always follow the prescribed medication schedule.</li>
                <li>Consult a doctor before making any changes to your medication.</li>
                <li>Maintain a balanced diet and engage in regular physical activity.</li>
                <li>Never ignore any unusual symptoms or side effects.</li>
                <li>Ensure your vaccination schedule is up to date.</li>
                <li>Respect hospital rules and maintain a clean and safe environment.</li>
                <li>In case of emergencies, always seek immediate medical attention.</li>
            </ul>
        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    <p>&copy; 2024 Hospital Management System. All rights reserved.</p>
</footer>

<script>
    // Function to update the time in the clock
    function updateClock() {
        var now = new Date();
        var hours = now.getHours().toString().padStart(2, '0');
        var minutes = now.getMinutes().toString().padStart(2, '0');
        var seconds = now.getSeconds().toString().padStart(2, '0');
        var timeString = hours + ":" + minutes + ":" + seconds;
        document.getElementById("clock").innerText = timeString;
    }

    // Call the updateClock function every second
    setInterval(updateClock, 1000);
</script>

</body>
</html>

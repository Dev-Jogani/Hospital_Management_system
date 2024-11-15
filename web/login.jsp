<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="LoginServlet" method="post">
            <label>Email:</label>
            <input type="email" name="email" required><br>

            <label>Password:</label>
            <input type="password" name="password" required><br>

            <label>Role:</label>
            <select name="role" required>
                <option value="doctor">Doctor</option>
                <option value="patient">Patient</option>
                <option value="receptionist">Receptionist</option>
                <option value="pharmacy">Pharmacy</option>
            </select><br>

            <input type="submit" value="Login">
        </form>

        <p><a href="register.jsp">Don't have an account? Register here</a></p>
    </div>
</body>
</html>

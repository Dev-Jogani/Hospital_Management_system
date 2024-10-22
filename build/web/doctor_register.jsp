<html>
<head>
    <title>Doctor Registration</title>
</head>
<body>
    <h2>Doctor Registration</h2>
    <form action="DoctorRegisterServlet" method="post">
        <label>Full Name:</label>
        <input type="text" name="name" required><br>

        <label>Email:</label>
        <input type="email" name="email" required><br>

        <label>Specialization:</label>
        <input type="text" name="specialization" required><br>

        <label>Availability:</label>
        <input type="text" name="availability" required><br>

        <label>Contact Info:</label>
        <input type="text" name="contact" required><br>

        <label>Date of Birth:</label>
        <input type="date" name="dob" required><br>

        <label>Gender:</label>
        <select name="gender" required>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select><br>

        <label>Password:</label>
        <input type="password" name="password" required><br>

        <input type="submit" value="Register">
    </form>
</body>
</html>
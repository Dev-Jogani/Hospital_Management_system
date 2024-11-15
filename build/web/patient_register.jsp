<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Registration</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="patient-registration-container">
        <h2>Patient Registration</h2>
        <form action="PatientRegisterServlet" method="post">
            <label>Full Name:</label>
            <input type="text" name="name" required><br>

            <label>Email:</label>
            <input type="email" name="email" required><br>

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

            <label>Medical History:</label>
            <textarea name="medical_history" required></textarea><br>

            <label>Password:</label>
            <input type="password" name="password" required><br>

            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>

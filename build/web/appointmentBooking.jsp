<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Appointment</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="book-appointment-container">
        <h1>Book an Appointment</h1>
        <form action="BookAppointmentServlet" method="post">
            <label for="patient_name">Patient Name:</label>
            <input type="text" id="patient_name" name="patient_name" required><br><br>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br><br>
            
            <label for="disease">Disease:</label>
            <input type="text" id="disease" name="disease" required><br><br>
            
            <label for="appointment_date">Appointment Date:</label>
            <input type="datetime-local" id="appointment_date" name="appointment_date" required><br><br>
            
            <input type="submit" value="Book Appointment">
        </form>
    </div>
</body>
</html>

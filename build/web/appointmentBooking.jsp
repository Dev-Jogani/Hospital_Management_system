<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Appointment</title>
</head>
<body>
    <h1>Book an Appointment</h1>
    <form action="BookAppointmentServlet" method="post">
        <label for="patientName">Patient Name:</label>
        <input type="text" id="patientName" name="patientName" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="disease">Disease:</label>
        <input type="text" id="disease" name="disease" required><br><br>
        
        <label for="doctorEmail">Doctor's Email:</label>
        <input type="email" id="doctorEmail" name="doctorEmail"><br><br>
        
        <label for="appointmentDate">Appointment Date:</label>
        <input type="datetime-local" id="appointmentDate" name="appointmentDate" required><br><br>
        
        <input type="submit" value="Book Appointment">
    </form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Home</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="patient-home-container">
        <h2>Welcome, Patient!</h2>
        <p>This is your dashboard where you can view your medical history, book appointments, etc.</p>
        <a href="${pageContext.request.contextPath}/PatientSection">Patient Section</a>
        <!-- User Side: Book Appointment -->
        <form action="appointmentBooking.jsp" method="get">
            <input type="submit" value="User - Book Appointment">
        </form>

        <br>

        <!-- User Side: View Appointments -->
        <form action="viewAppointments.jsp" method="get">
            <input type="submit" value="User - View Appointments">
        </form>

        <a href="LogoutServlet">Logout</a>
    </div>
</body>
</html>

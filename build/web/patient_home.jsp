<html>
<head>
    <title>Patient Home</title>
</head>
<body>
    <h2>Welcome, Patient!</h2>
    <p>This is your dashboard where you can view your medical history, book appointments, etc.</p>
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
</body>
</html>
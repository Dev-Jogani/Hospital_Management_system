<!DOCTYPE html>
<html>
<head>
    <title>Hospital Management System</title>
</head>
<body>
    <h1>Welcome to the Hospital Management System</h1>

    <p>Please select your role:</p>

    <!-- User Side: Book Appointment -->
    <form action="appointmentBooking.jsp" method="get">
        <input type="submit" value="User - Book Appointment">
    </form>

    <br>

    <!-- User Side: View Appointments -->
    <form action="viewAppointments.jsp" method="get">
        <input type="submit" value="User - View Appointments">
    </form>

    <br>

    <!-- Admin Side: Manage Appointments -->
    <form action="adminManageAppointment.jsp" method="get">
        <input type="submit" value="Admin - Manage Appointments">
    </form>

    <br>

    <!-- Admin Side: Manage Rooms -->
    <form action="AdminManageRooms.jsp" method="get">
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

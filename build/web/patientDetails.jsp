<%-- 
    Document   : patientDetails
    Created on : 05-Nov-2024, 10:19:00 am
    Author     : DELL
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patient Details</title>
</head>
<body>
    <h1>Patient Details</h1>
    <div class="patient-info">
        <h2>Information</h2>
        <p><strong>Name:</strong> ${name}</p>
        <p><strong>Email:</strong> ${email}</p>
        <p><strong>Contact Info:</strong> ${contactInfo}</p>
        <p><strong>Medical History:</strong> ${medicalHistory}</p>
    </div>

    <h2>Appointments</h2>
    <table border="1">
        <tr>
            <th>Appointment Date</th>
            <th>Status</th>
            <th>Details</th>
        </tr>
        <c:choose>
            <c:when test="${not empty appointments}">
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <td>${appointment.date}</td>
                        <td>${appointment.status}</td>
                        <td><a href="appointmentDetails?id=${appointment.appointmentId}">More Details</a></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="3">No appointments found.</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
    <br>
    <button onclick="window.history.back()">Back To All Patients</button>
</body>
</html>
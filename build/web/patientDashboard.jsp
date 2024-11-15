<%-- 
    Document   : patientDashboard
    Created on : 30-Oct-2024, 8:09:18 pm
    Author     : DELL
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patient Dashboard</title>
</head>
<body>
  
         
    <h1>Welcome, ${name}</h1>
    <div class="patient-info">
        <h2>Patient Information</h2>
        <p>Email: ${email}</p>
        <p>Disease: ${disease}</p>
    </div>
    
    <h2>Your Appointments</h2>
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
</body>
</html>
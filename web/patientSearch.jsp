<%-- 
    Document   : patientSearch
    Created on : 05-Nov-2024, 10:10:46 pm
    Author     : DELL
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Patients</title>
</head>
<body>
    <form action="PatientSearch" method="get">
        <input type="text" name="searchName" placeholder="Enter patient name" required>
        <input type="submit" value="Search">
    </form>
    
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Patients</title>
</head>
<body>
    <%@ include file="patientSearch.jsp" %> <!-- Include the search form -->
    
    <c:choose>
        <c:when test="${viewType == 'search'}">
            <h2>Search Results</h2>
        </c:when>
        <c:otherwise>
            <h2>All Patients</h2>
        </c:otherwise>
    </c:choose>
    
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Contact Info</th>
            <th>Details</th>
        </tr>

        <c:choose>
            <c:when test="${not empty patients}">
                <c:forEach var="patient" items="${patients}">
                    <tr>
                        <td><c:out value="${patient.name}" /></td>
                        <td><c:out value="${patient.email}" /></td>
                        <td><c:out value="${patient.contact_info}" /></td>
                        <td><a href="patientDetails?id=${patient.patient_id}">More Details</a></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">No patients found.</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
           
    <c:if test="${viewType == 'search'}">
        <br>
        <form action="AllPatients" method="get">
            <input type="submit" value="All Patients">
        </form>
    </c:if>
</body>
</html>
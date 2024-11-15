<%-- 
    Document   : showExpiredMedicines
    Created on : 09-Nov-2024, 12:37:29 am
    Author     : DELL
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Expired Medicines</title>
</head>
<body>
    <h2>Expired Medicines</h2>
    
    <table border="1">
        
        <c:set var="check" value="false"/>
        <c:forEach var="medicine" items="${expiredMedicines}">
            <c:if test="${check==false}">
                <tr>
                    <th>Medicine Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Expiration Date</th>
                    <th>Section</th>
                    <th>Delete</th>
                </tr>
                <c:set var="check" value="true"/>
            </c:if>
            <tr>
                <td>${medicine.name}</td>
                <td>${medicine.quantity}</td>
                <td>${medicine.price}</td>
                <td><fmt:formatDate value="${medicine.expiration_date}" pattern="yyyy-MM-dd"/></td>
                <td>${medicine.section}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/deleteMedicine" method="POST">
                        <input type="hidden" name="medicineId" value="${medicine.medicine_id}" />
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${check==false}">
            <h4>You have not expired medicine</h4>
        </c:if>
            
    </table>
    <br>
            
    <a href="medicineDashboard.jsp"> Back </a>
</body>
</html>
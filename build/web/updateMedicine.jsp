<%-- 
    Document   : updateMedicine
    Created on : 08-Nov-2024, 11:52:17 am
    Author     : DELL
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Medicine Quantity</title>
</head>
<body>
    <h1>Update Medicine Quantity</h1>

    <!-- Display success or error message -->
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <!-- Form to update existing medicine -->
    <form action="${pageContext.request.contextPath}/update" method="post">
        <label for="medicineId">Select Medicine:</label>
        <select name="medicineId" id="medicineId" required>
            <option value="">--Select Medicine--</option>
            <c:forEach var="medicine" items="${medicines}">
                <option value="${medicine.id}">${medicine.name}</option>
            </c:forEach>
        </select>

        <label for="quantity">Quantity to Add:</label>
        <input type="number" name="quantity" id="quantity" min="1" required>

        <button type="submit">Update Quantity</button>
    </form>
        <br>
      
        <a href="addMedicine.jsp">Back</a>

</body>
</html>
<%-- 
    Document   : printReceipt
    Created on : 09-Nov-2024, 9:18:08 am
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Print Receipt Page</title>
    </head>
    <script>
        function printSlip() {
            window.print();
        }
    </script>
    <body>
        <c:if test="${not empty dispensedMedicines}">
        <h2>Medicine Receipt</h2>
        <table border="1">
            <tr>
                <th>Medicine Name</th>
                <th>Quantity Given</th>
                <th>Price per Unit</th>
                <th>Total Price</th>
            </tr>
            <c:forEach var="medicine" items="${dispensedMedicines}">
                <c:if test="${medicine.quantity != 0}">
                    <tr>
                        <td>${medicine.name}</td>
                        <td>${medicine.quantity}</td>
                        <td>${medicine.price}</td>
                        <td>${medicine.totalPrice}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        <h3>Total Bill: ₹${totalBill}</h3>
        <p>May you be blessed with good health and a speedy recovery. </p>
        <button onclick="printSlip()">Print Receipt</button>
        
        <br>
        <br>
        <a href="giveMedicine.jsp"> Back </a>
    </c:if>
    </body>
</html>
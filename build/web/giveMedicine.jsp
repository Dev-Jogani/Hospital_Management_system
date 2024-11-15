<%-- 
    Document   : giveMedicine
    Created on : 09-Nov-2024, 8:47:36?am
    Author     : DELL
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Give Medicines to Patient</title>

</head>
<script>
        // JavaScript function to filter table rows based on search input
        function searchMedicine() {
            let input = document.getElementById("searchInput").value.toUpperCase();
            let table = document.getElementById("medicineTable");
            let rows = table.getElementsByTagName("tr");

            // Loop through all table rows, except the header row
            for (let i = 1; i < rows.length; i++) {
                let td = rows[i].getElementsByTagName("td")[0]; // Medicine Name column
                if (td) {
                    let txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(input) > -1) {
                        rows[i].style.display = "";
                    } else {
                        rows[i].style.display = "none";
                    }
                }       
            }
        }
</script>
<body>
    <h2>Dispense Medicines</h2>
    
    <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/hospital"
                       user="root" password=""/>
                       
    <sql:query dataSource="${dataSource}" var="medicines">
        SELECT * FROM Medicines WHERE quantity > 0;
    </sql:query>
        
    <input type="text" id="searchInput" onkeyup="searchMedicine()" placeholder="Search for medicine...">
    <br><br>
    
    <form action="${pageContext.request.contextPath}/GiveMedicineServlet" method="POST">
        <table border="1" id="medicineTable">
            <tr>
                <th>Medicine Name</th>
                <th>Available Quantity</th>
                <th>Price per Unit</th>
                <th>Quantity to Dispense</th>
            </tr>
            <c:forEach var="medicine" items="${medicines.rows}">
                <tr>
                    <td>${medicine.name}</td>
                    <td>${medicine.quantity}</td>
                    <td>${medicine.price}</td>
                    <td>
                        <input type="hidden" name="medicineIds" value="${medicine.medicine_id}">
                        <input type="number" name="quantities" min="1" max="${medicine.quantity}">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <button type="submit">Dispense Medicines</button>
        <br><br>
        <a href="medicineDashboard.jsp">Back</a>
    </form>

    
</body>
</html>
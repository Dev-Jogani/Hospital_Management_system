<%-- 
    Document   : showAllMedicine
    Created on : 08-Nov-2024, 11:00:24?pm
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Medicines</title>
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
</head>
<body>
    <h2>All Medicines</h2>

    <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/hospital"
                       user="root" password=""/>
    
    <sql:query var="medicineData" dataSource="${dataSource}">
        SELECT * FROM Medicines;
    </sql:query>

    <input type="text" id="searchInput" onkeyup="searchMedicine()" placeholder="Search for medicine...">
    <br><br>

    <form action="${pageContext.request.contextPath}/GiveMedicineServlet" method="POST" style="text-align: center">
        <table id="medicineTable" border="1">
            <tr>
                <th>Medicine Name</th>
                <th>Available Quantity</th>
                <th>Price per Unit</th>
                <th>Expiration Date</th>
                <th>Section</th>
            </tr>
            
            <!-- Loop through each medicine retrieved from the database -->
            <c:forEach var="medicine" items="${medicineData.rows}">
                <tr>
                    <td>${medicine.name}</td>
                    <td>${medicine.quantity}</td>
                    <td>${medicine.price}</td>
                    <td>
                        <fmt:formatDate value="${medicine.expiration_date}" pattern="dd-MM-yyyy" />
                    </td>
                    <td>${medicine.section}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
        
    <br>
        
    <a href="medicineDashboard.jsp"> Back </a>

</body>
</html>
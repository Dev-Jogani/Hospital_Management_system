<%-- 
    Document   : addNewMedicineForm
    Created on : 08-Nov-2024, 12:09:01 pm
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Medicine</title>
</head>
<body>
    <h1>Add New Medicine</h1>
    
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <hr>
    <form action="AddMedicineServlet" method="post">
        <div>
            <label for="name">Medicine Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <br>
        <div>
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" min="1" required>
        </div>
        <br>
        <div>
            <label for="price">Price (per unit):</label>
            <input type="number" id="price" name="price" step="0.01" min="0" required>
        </div>
        <br>
        <div>
            <label for="expiration_date">Expiration Date:</label>
            <input type="date" id="expiration_date" name="expiration_date">
        </div>
        <br>
        <div>
            <label for="section">Section:</label>
            <select id="section" name="section" required>
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
                <option value="E">E</option>
                <option value="F">F</option>
                <option value="G">G</option>
                <option value="H">H</option>
                <option value="I">I</option>
                <option value="J">J</option>
            </select>
        </div>
        <br>
        <div>
            <button type="submit">Add Medicine</button>
        </div>
    </form>
    
    <br>
    
    <a href="addMedicine.jsp">Back</a>
    
    

</body>
</html>
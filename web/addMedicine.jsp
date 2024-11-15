<%-- 
    Document   : addMedicine
    Created on : 08-Nov-2024, 11:24:34?am
    Author     : DELL
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Medicine</title>
</head>
<body>
    <h1>Manage Medicine Inventory</h1>

    <div>
        <a href="${pageContext.request.contextPath}/update">Add to Existing Medicine</a>
        <p>Use this option to update the quantity of an existing medicine.</p>
    </div>

    <div>
        <a href="${pageContext.request.contextPath}/addNewMedicineForm.jsp">Add New Medicine</a>
        <p>Use this option to add a new medicine manually.</p>
    </div>
        
        <a href="medicineDashboard.jsp">Back</a>

</body>
</html>
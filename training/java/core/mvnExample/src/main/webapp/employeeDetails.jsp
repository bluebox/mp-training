<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Details</title>
</head>
<body>
<h2>Employee List</h2>
<table border="1">
    <tr>
        <td>id</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Username</td>
        <td>Password</td>
        <td>Address</td>
        <td>Contact</td>
    </tr>
    <c:forEach var="emp" items="${empList}">
        <tr>
             <td>${emp.id }</td>
            <td>${emp.firstName}</td>
            <td>${emp.lastName}</td>
            <td>${emp.userName}</td>
            <td>${emp.password }</td>
            <td>${emp.address}</td>
            <td>${emp.contact}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
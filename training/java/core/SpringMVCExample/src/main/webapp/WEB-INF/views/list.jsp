<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Employee List</h2>
<table border="1">
<tr><th>Name</th><th>Role</th></tr>
<c:forEach var="emp" items="${employees}">
<tr>
<td>${emp.name}</td>
<td>${emp.role}</td>
</tr>
</c:forEach>
</table>
<a href="add"><button>Add New Employee</button></a>
</body>
</html>

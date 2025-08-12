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
<a href="add">Add New Employee</a>
</body>
</html>

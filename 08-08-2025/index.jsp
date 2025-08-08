<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Tree List</title></head>
<body>
    <h2>Tree List</h2>
    <a href="/webapp/addTree">Add Tree</a>
    <table border="1">
        <tr><th>Name</th><th>Type</th><th>Age</th><th>Actions</th></tr>
        <c:forEach var="tree" items="${trees}">
            <tr>
                <td>${tree.name}</td>
                <td>${tree.type}</td>
                <td>${tree.age}</td>
                <td>
                    <a href="/webapp/editTree/${tree.id}">Edit</a>
                    <a href="/webapp/deleteTree/${tree.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
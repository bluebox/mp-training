<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Member List</title>
    <style>
        table, th, td { border: 1px solid black; padding: 8px; border-collapse: collapse; }
        th { background-color: #ddd; }
    </style>
</head>
<body>
    <div align="center">
        <h2>All Members</h2>

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>ID</th><th>Name</th><th>email</th><th>Mobile</th><th>Gender</th><th>Address</th><th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="members" items="${members}">
                    <tr>
                        <td>${members.memberId}</td>
                        <td>${members.name}</td>
                        <td>${members.email}</td>
                        <td>${members.mobile}</td>
                        <td>${members.gender}</td>
                        <td>${members.address}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/updateMemberServlet?memberId=${members.memberId}">
                                <button>Update</button>
                            </a>
                        </td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
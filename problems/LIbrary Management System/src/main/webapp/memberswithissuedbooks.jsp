<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Members with Issued Books</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        .btn-back {
            margin-top: 20px;
        }
    </style>
</head>
<body style="display:flex;flex-direction:column;align-items:center;">

    <h2>Members with Issued Books</h2>

    <c:choose>
        <c:when test="${not empty members}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>Gender</th>
                    <th>Address</th>
                </tr>
                <c:forEach var="member" items="${members}">
                    <tr>
                        <td>${member.id}</td>
                        <td>${member.name}</td>
                        <td>${member.email}</td>
                        <td>${member.mobile}</td>
                        <td>${member.gender}</td>
                        <td>${member.address}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No members with issued books found.</p>
        </c:otherwise>
    </c:choose>

       <div class="back-btn">
        <a href="reports">Back to Reports</a>
    </div>

</body>
</html>


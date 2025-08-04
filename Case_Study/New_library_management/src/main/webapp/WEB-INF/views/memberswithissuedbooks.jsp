<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Members with Issued Books</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('images/download.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .content {
            background-color: rgba(255, 255, 255, 0.5);
            display: inline-block;
            padding: 25px 40px;
            margin: 40px auto;
            border-radius: 8px;
            text-align: center;
        }

        h2 {
            color: #2c3e50;
            margin-bottom: 20px;
        }

        table {
            border-collapse: collapse;
            margin: 10px auto;
            width: 90%;
            max-width: 800px;
        }

        th, td {
            border: 1px solid #444;
            padding: 8px 12px;
            font-size: 14px;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        button {
            margin-top: 20px;
            padding: 8px 16px;
            font-size: 14px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <center>
        <div class="content">
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
                                <td>${member.memberid}</td>
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

            <form action="${pageContext.request.contextPath}/reports.html" method="get">
                <button type="submit">Back to Reports</button>
            </form>
        </div>
    </center>
</body>
</html>

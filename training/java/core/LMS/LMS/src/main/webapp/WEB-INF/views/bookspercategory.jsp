<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books Per Category</title>
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
            margin: 40px auto;
            padding: 25px 40px;
            border-radius: 8px;
        }

        h2 {
            color: #2c3e50;
            margin-bottom: 20px;
        }

        table {
            border-collapse: collapse;
            margin: 10px auto;
            width: 300px;
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
            <h2>Books Per Category</h2>

            <c:choose>
                <c:when test="${not empty categoryMap}">
                    <table>
                        <tr>
                            <th>Category</th>
                            <th>Count</th>
                        </tr>
                        <c:forEach var="entry" items="${categoryMap}">
                            <tr>
                                <td>${entry.key}</td>
                                <td>${entry.value}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>No data available.</p>
                </c:otherwise>
            </c:choose>

            <form action="${pageContext.request.contextPath}/reports.html" method="get">
                <button type="submit">Back to Reports</button>
            </form>
        </div>
    </center>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Books Per Category</title>
    <style>
        body { font-family: Arial; padding: 20px; }
        h2 { color: #333; }
        table { width: 50%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #aaa; padding: 10px; text-align: center; }
        th { background-color: #4CAF50; color: white; }
        .back-btn { margin-top: 20px; }
    </style>
</head>
<body>
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

    <div class="back-btn">
    <form action="reports" method="get">
        <button type="submit">Back to Reports</button>
    </form>
</div>
    
</body>
</html>

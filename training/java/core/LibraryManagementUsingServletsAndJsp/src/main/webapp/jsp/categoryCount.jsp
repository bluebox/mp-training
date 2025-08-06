<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>📚 Count of Books per Category</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
            url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1470&q=80');
        background-size: cover;
        background-position: center;
        min-height: 100vh;
    }

    .container {
        max-width: 800px;
        margin: 50px auto;
        background: rgba(255, 255, 255, 0.96);
        padding: 30px 40px;
        border-radius: 15px;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
        text-align: center;
    }

    h1 {
        text-align: center;
        font-size: 24px;
        color: #0d47a1;
        border-bottom: 2px solid #90caf9;
        padding-bottom: 10px;
        margin-bottom: 25px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
        font-size: 14px;
    }

    th, td {
        padding: 10px 8px;
        border-bottom: 1px solid #ccc;
        text-align: left;
    }

    th {
        background-color: #00796b;
        color: white;
    }

    tr:hover {
        background-color: #f5f5f5;
    }

    .nav-links {
        text-align: center;
        margin-top: 30px;
    }

    .nav-links a {
        text-decoration: none;
        color: #0d47a1;
        font-size: 14px;
        font-weight: bold;
        margin: 0 10px;
        padding: 8px 16px;
        border: 2px solid #0d47a1;
        border-radius: 6px;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .nav-links a:hover {
        background-color: #0d47a1;
        color: white;
    }

    .popup.error {
        background-color: #ffcdd2;
        color: #c62828;
        padding: 12px 25px;
        font-size: 14px;
        font-weight: bold;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
        margin-top: 20px;
        text-align: center;
    }

   
</style>
</head>
<body>

<div class="container">
    <h1>📊 Count of Books per Category</h1>

    <table>
        <thead>
            <tr>
                <th>📁 Category</th>
                <th>🔢 Count</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${categoryCountMap}">
                <tr>
                    <td><c:out value="${entry.key}" /></td>
                    <td><c:out value="${entry.value}" /></td>
                </tr>
            </c:forEach>
            <c:if test="${empty categoryCountMap}">
                <tr>
                    <td colspan="2">
                        <div class="popup error">
                            No data found.
                        </div>
                    </td>
                </tr>
            </c:if>
        </tbody>
    </table>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/jsp/index.jsp">🏠 Home</a>
        <a href="${pageContext.request.contextPath}/jsp/reports.jsp">← Back</a>
    </div>
</div>

</body>
</html>

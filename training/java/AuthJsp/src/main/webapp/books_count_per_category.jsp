<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import="java.util.Map" %>
<%@ page import="com.casestudy.serviceimpl.Service" %>
<%@ page import="java.util.Map.Entry" %>
<%
    // Instantiate your service class and get the data
    Service service = new Service();
    Map<String, Long> categoryCountMap = service.getBooksCountPerCategory();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books Count Per Category</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            border-collapse: collapse;
            margin: 20px auto;
            width: 50%;
        }
        th, td {
            border: 1px solid #888;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f0f0f0;
        }
        h2 {
            text-align: center;
            color: #444;
        }
    </style>
</head>
<body>
    <h2>Book Count Per Category</h2>
    <table>
        <tr>
            <th>Category</th>
            <th>Book Count</th>
        </tr>
        <% for (Entry<String, Long> entry : categoryCountMap.entrySet()) { %>
        <tr>
            <td><%= entry.getKey() %></td>
            <td><%= entry.getValue() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List , model.BookCategoryCount"%>
<%
    List<BookCategoryCount> categoryCounts = (List<BookCategoryCount>) request.getAttribute("categoryCounts");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Count Per Category</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f9fafb;
            padding: 50px;
        }
        .container {
            max-width: 500px;
            margin: auto;
            background: #ffffff;
            padding: 24px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 18px;
            font-size: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 16px;
        }
        th, td {
            border: 1px solid #e2e8f0;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #e2e8f0;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Book Count Per Category</h2>

    <table>
        <thead>
            <tr>
                <th>Category</th>
                <th>Count</th>
            </tr>
        </thead>
        <tbody>
<%
    if (categoryCounts != null) {
        for (BookCategoryCount item : categoryCounts) {
%>
    <tr>
        <td><%= item.getCategory() %></td>
        <td><%= item.getCount() %></td>
    </tr>
<%
        }
    }
%>
</tbody>
    </table>
</div>

</body>
</html>

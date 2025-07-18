<%@page import="java.util.List,model.Book"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View All Books</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
            background: #f9fafb;
        }
        h2 {
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0 0 8px rgba(0, 0, 0, 0.08);
        }
        th, td {
            padding: 12px 16px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #e2e8f0;
        }
    </style>
</head>
<body>

<h2>All Books</h2>

<table>
    <thead>
        <tr>
            <th>Book ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Status</th>
            <th>Availability</th>
        </tr>
    </thead>
    <tbody>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    if (books != null) {
        for (Book book : books) {
            String statusLabel = "";
            String availabilityLabel = "";

            if (book.getStatus() == 'A') {
                statusLabel = "Active";
            } else if (book.getStatus() == 'I') {
                statusLabel = "Inactive";
            }

            if (book.getAvailability() == 'A') {
                availabilityLabel = "Available";
            } else if (book.getAvailability() == 'I') {
                availabilityLabel = "Issued";
            }
%>
    <tr>
        <td><%= book.getBookId() %></td>
        <td><%= book.getTitle() %></td>
        <td><%= book.getAuthor() %></td>
        <td><%= book.getCategory() %></td>
        <td><%= statusLabel %></td>
        <td><%= availabilityLabel %></td>
    </tr>
<%
        }
    }
%>

</tbody>

</table>

</body>
</html>

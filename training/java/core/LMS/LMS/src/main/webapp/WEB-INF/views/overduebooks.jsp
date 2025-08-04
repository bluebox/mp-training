<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Overdue Books</title>
    <style>
        body {
            background-image: url('images/download.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            font-family: sans-serif;
            margin: 0;
            padding: 0;
        }

        .content {
            background-color: rgba(255, 255, 255, 0.5);
            display: inline-block;
            padding: 15px 25px;
            margin: 40px auto;
            border-radius: 8px;
            text-align: center;
        }

        table {
            border-collapse: collapse;
            margin: 10px auto;
        }

        th, td {
            padding: 6px 12px;
            border: 1px solid #444;
            font-size: 14px;
        }

        th {
            background-color: #eee;
        }

        button {
            margin-top: 15px;
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
            <h3>Overdue Books</h3>
            <%
                List<Book> books = (List<Book>) request.getAttribute("books");
                if (books == null || books.isEmpty()) {
            %>
                <p>No overdue books found.</p>
            <%
                } else {
            %>
                <table>
                    <tr>
                        <th>Book ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Category</th>
                    </tr>
                    <%
                        for (Book book : books) {
                    %>
                    <tr>
                        <td><%= book.getBookid() %></td>
                        <td><%= book.getTitle() %></td>
                        <td><%= book.getAuthor() %></td>
                        <td><%= book.getCategory() %></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            <%
                }
            %>

            <form action="${pageContext.request.contextPath}/reports.html" method="get">
                <button type="submit">Back to Reports</button>
            </form>
        </div>
    </center>
</body>
</html>

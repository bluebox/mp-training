<%@page import="java.util.ArrayList"%>
<%@page import="com.lms.model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Books</title>
    <style>
        .table-container {
            font-family: Arial, sans-serif;
            margin: 20px;
            color: #34495e;
            max-height: 700px;
            overflow-y: auto;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .table-container h1 {
            color: #34495e;
            margin-bottom: 30px;
        }

        .table-container table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        .table-container thead {
            position: sticky;
            top: 0;
            z-index: 1;
        }

        .table-container th,
        .table-container td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #7f8c8d;
        }

        .table-container th {
            background-color: #34495e;
            color: white;
        }

        .table-container tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .table-container button {
            background-color: #34495e;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
        }

        .table-container button:hover {
            background-color: #7f8c8d;
        }
    </style>

    <%
        List<Book> books = (List<Book>) request.getAttribute("booksList");
        books = books==null ? new ArrayList<Book>() : books;
    %>
</head>
<body>
    <div class="table-container">
        <h1>View Books</h1>
        <table>
            <thead>
                <tr>
                    <th>Book ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Availability</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for(Book book : books){ %>
                    <tr>
                        <td><%= book.getBook_Id() %></td>
                        <td><%= book.getBook_Title() %></td>
                        <td><%= book.getBook_Author() %></td>
                        <td><%= book.getBook_Category() %></td>
                        <td><%= book.getBook_Status() %></td>
                        <td><%= book.getBook_Availability() %></td>
                        <td>
                            <form action="ViewBooksServlet" method="post" style="margin:0;">
                                <input type="hidden" name="bookId" value="<%= book.getBook_Id() %>" />
                                <input type="hidden" name="title" value="<%= book.getBook_Title() %>" />
                                <input type="hidden" name="author" value="<%= book.getBook_Author() %>" />
                                <input type="hidden" name="category" value="<%= book.getBook_Category() %>" />
                                <input type="hidden" name="status" value="<%= book.getBook_Status() %>" />
                                <input type="hidden" name="availability" value="<%= book.getBook_Availability() %>" />
                                <button type="submit" name="action" value="Update">Update</button>
                            </form>
                        </td>
                    </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</body>
</html>

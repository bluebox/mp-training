<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.casestudy.domain.IssueRecord" %>
<%@ page import="com.casestudy.serviceimpl.Service" %>
<%
    // Fetch overdue books using service layer
    Service service = new Service();
    List<IssueRecord> overdueBooks = service.getOverdueBooks();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Overdue Books</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            border-collapse: collapse;
            width: 90%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #eaeaea;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>

    <h2>Overdue Books Report (Issued Over a Month Ago)</h2>

    <table>
        <tr>
            <th>Issue ID</th>
            <th>Book ID</th>
            <th>Member ID</th>
            <th>Status</th>
            <th>Issue Date</th>
            <th>Return Date</th>
        </tr>
        <%
            if (overdueBooks.isEmpty()) {
        %>
            <tr>
                <td colspan="6">No overdue books found.</td>
            </tr>
        <%
            } else {
                for (IssueRecord record : overdueBooks) {
        %>
            <tr>
                <td><%= record.getIssueId() %></td>
                <td><%= record.getBookId() %></td>
                <td><%= record.getMemberId() %></td>
                <td><%= record.getStatus().name() %></td>
                <td><%= record.getIssueDate() %></td>
                <td><%= record.getReturnDate() != null ? record.getReturnDate() : "-" %></td>
            </tr>
        <%
                }
            }
        %>
    </table>

</body>
</html>

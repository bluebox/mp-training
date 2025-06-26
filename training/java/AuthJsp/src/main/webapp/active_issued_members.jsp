<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.casestudy.domain.IssueRecord" %>
<%@ page import="com.casestudy.dao.IssueRecordDao" %>

<%
    List<IssueRecord> activeIssued = new IssueRecordDao().getActiveIssuedBooks();
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Active Issued Records</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h2 {
            text-align: center;
        }
        table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 90%;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 8px 12px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        td.na {
            color: #888;
        }
    </style>
</head>
<body>
    <h2>📋 All Active Issued Book Records</h2>

    <table>
        <tr>
            <th>Issue ID</th>
            <th>Member ID</th>
            <th>Book ID</th>
            <th>Status</th>
            <th>Issue Date</th>
            <th>Return Date</th>
        </tr>
        <%
            if (activeIssued != null && !activeIssued.isEmpty()) {
                for (IssueRecord r : activeIssued) {
        %>
        <tr>
            <td><%= r.getIssueId() %></td>
            <td><%= r.getMemberId() %></td>
            <td><%= r.getBookId() %></td>
            <td><%= r.getStatus().name() %></td>
            <td><%= r.getIssueDate() %></td>
            <td class="<%= r.getReturnDate() == null ? "na" : "" %>">
                <%= r.getReturnDate() != null ? r.getReturnDate() : "N/A" %>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="6">No active issued books found.</td></tr>
        <% } %>
    </table>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.MemberIssueDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Members With Books</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 40px; background: #f9fafb; }
        h2 { margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; background: #fff; box-shadow: 0 0 8px rgba(0, 0, 0, 0.08); }
        th, td { padding: 12px 16px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #e2e8f0; }
        .error { color: red; margin-bottom: 20px; }
    </style>
</head>
<body>
<h2>Members With Active Issued Books</h2>
<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <div class="error"><%= error %></div>
<% } %>
<table>
    <thead>
        <tr>
            <th>Member ID</th>
            <th>Member Name</th>
            <th>Book ID</th>
            <th>Book Name</th>
            <th>Mobile</th>
            <th>Address</th>
            <th>Issue Date</th>
        </tr>
    </thead>
    <tbody>
<%
    List<MemberIssueDTO> membersWithBooks = (List<MemberIssueDTO>) request.getAttribute("membersWithBooks");
    if (membersWithBooks != null && !membersWithBooks.isEmpty()) {
        for (MemberIssueDTO dto : membersWithBooks) {
%>
        <tr>
            <td><%= dto.getMemberId() %></td>
            <td><%= dto.getMemberName() %></td>
            <td><%= dto.getBookId() %></td>
            <td><%= dto.getBookName() %></td>
            <td><%= dto.getMobile() %></td>
            <td><%= dto.getAddress() %></td>
            <td><%= dto.getIssueDate() %></td>
        </tr>
<%
        }
    } else {
%>
        <tr><td colspan="7">No members with active issued books found.</td></tr>
<% } %>
    </tbody>
</table>
</body>
</html>
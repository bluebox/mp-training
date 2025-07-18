<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.IssueRecord" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issued Records</title>
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
<h2>Issued Records</h2>
<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <div class="error"><%= error %></div>
<% } %>
<table>
    <thead>
        <tr>
            <th>Issue ID</th>
            <th>Book ID</th>
            <th>Member ID</th>
            <th>Issue Date</th>
            <th>Return Date</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
<%
    List<IssueRecord> issuedRecords = (List<IssueRecord>) request.getAttribute("issuedRecords");
    if (issuedRecords != null && !issuedRecords.isEmpty()) {
        for (IssueRecord record : issuedRecords) {
%>
        <tr>
            <td><%= record.getIssueId() %></td>
            <td><%= record.getBookId() %></td>
            <td><%= record.getMemberId() %></td>
            <td><%= record.getIssueDate() %></td>
            <td><%= record.getReturnDate() != null ? record.getReturnDate() : "-" %></td>
            <td><%= record.getStatus() %></td>
        </tr>
<%
        }
    } else {
%>
        <tr><td colspan="6">No issued records found.</td></tr>
<% } %>
    </tbody>
</table>
</body>
</html>
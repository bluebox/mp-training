<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Issue_records" %>
<%@ page import="domain.checking_enum.Status_issue" %>
<%@ page import="DAO.Issue_RecordDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue Records Table</title>
</head>
<body>
    <div align="center">
        <h1>All Issue Records</h1>
        <table border="1">
            <tr>
                
                <th>Book ID</th>
                <th>Member ID</th>
                <th>Status</th>
                <th>Issue Date</th>
                <th>Return Date</th>
            </tr>
            <%
                List<Issue_records> issueList = (List<Issue_records>) request.getAttribute("issueList");
                if (issueList != null && !issueList.isEmpty()) {
                    for (Issue_records record : issueList) {
            %>
            <tr>
                
                <td><%= record.getBookid() %></td>
                <td><%= record.getMemberid() %></td>
                <td><%= record.getStatus_issue() %></td>
                <td><%= record.getIssuedate() %></td>
                <td><%= record.getReturndate() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No records found.</td>
            </tr>
            <% } %>
        </table>
    </div>
    <center>
    <form action="/LMS/issuerecords.html" method="get">
        <button type="submit">Back to Issue Records</button>
    </form>
    </center>
</body>
</html>

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
            padding: 6px 12px; /* ✅ Smaller height */
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
        <h3>All Issue Records</h3>
        <table>
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

        <form action="${pageContext.request.contextPath}/issuerecords.html" method="get">
            <button type="submit">Back to Issue Records</button>
        </form>
    </div>
    </center>
</body>
</html>

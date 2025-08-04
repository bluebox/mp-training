<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Domain.IssueRecord" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue Records Table</title>
    <style>
        body {
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

        .btn-container {
            margin-top: 20px;
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
                List<IssueRecord> issueList = (List<IssueRecord>) request.getAttribute("issueList");
                if (issueList != null && !issueList.isEmpty()) {
                    for (IssueRecord record : issueList) {
            %>
            <tr>
                <td><%= record.getBookId() %></td>
                <td><%= record.getMemberId() %></td>
                <td><%= record.getStatus() %></td>
                <td><%= record.getIssueDate() %></td>
                <td><%= record.getReturnDate() %></td>
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

      
        <div class="btn-container">
            <a href="<%= request.getContextPath() %>/issuerecords.html">
                <button type="button">Back</button>
            </a>
        </div>

    </div>
</center>
</body>
</html>


<%@page import="java.util.ArrayList"%>
<%@page import="com.lms.model.Issue_Records"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Issue Records</title>
    <style>
        .records-container {
            height: 100vh;
            padding: 20px;
            font-family: Arial, sans-serif;
            box-sizing: border-box;
        }

        .records-container h1 {
            color: #34495e;
            margin: 20px 0;
            padding-bottom: 10px;
            border-bottom: 2px solid #7f8c8d;
            position: sticky;
            top: 0;
            background-color: white;
            z-index: 1;
        }

        .table-wrapper {
            max-height: calc(100vh - 120px);
            overflow-y: auto;
            margin-bottom: 30px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .records-container table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        .records-container thead {
            position: sticky;
            top: 0;
            z-index: 1;
        }

        .records-container th {
            background-color: #34495e;
            color: white;
            padding: 12px;
            text-align: left;
        }

        .records-container td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }

        .records-container tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        /* Scrollbar styling */
        .records-container::-webkit-scrollbar,
        .table-wrapper::-webkit-scrollbar {
            width: 8px;
        }

        .records-container::-webkit-scrollbar-track,
        .table-wrapper::-webkit-scrollbar-track {
            background: #f1f1f1;
        }

        .records-container::-webkit-scrollbar-thumb,
        .table-wrapper::-webkit-scrollbar-thumb {
            background: #7f8c8d;
            border-radius: 4px;
        }
    </style>
    
    <% 
        List<Issue_Records> issue_records = (List<Issue_Records>) request.getAttribute("isuueRecords"); 
        issue_records = issue_records==null ? new ArrayList<Issue_Records>() : issue_records;
        String return_date;
    %>
</head>
<body>
    <div class="records-container">
        <h1>Issued Records</h1>
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>Issue ID</th>
                        <th>Book ID</th>
                        <th>Member ID</th>
                        <th>Status</th>
                        <th>Issued Date</th>
                        <th>Return Date</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Issue_Records record : issue_records){ %>
                        <tr>
                            <td><%= record.getIssueId()%></td>
                            <td><%= record.getBookId() %></td>
                            <td><%= record.getMemberId() %></td>
                            <td><%= record.getStatus() %></td>
                            <td><%= record.getIssueDate() %></td>
                            <%
                            if (record.getReturnDate() == null) {
                                return_date = "Not Yet Returned";
                            } else {
                                return_date = record.getReturnDate().toString();
                            }
                            %>
                            <td><%= return_date %></td>
                        </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>

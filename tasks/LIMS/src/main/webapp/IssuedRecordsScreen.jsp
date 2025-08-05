<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Issue Records</title>
    <style>
        .container {
            text-align: center;
            padding: 20px;
        }
        table {
            width: 90%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .center-text {
            text-align: center;
        }
        .message-label {
            margin-top: 10px;
            font-weight: bold;
        }
        .button-group {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 20px;
        }
        .button-group button {
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
            color: white;
        }
       
    </style>
</head>
<body>
    <div class="container">
        <h1>All Issue Records</h1>

        <form action="issuedRecords" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Issue ID</th>
                        <th>Book ID</th>
                        <th>Book Title</th>
                        <th>Member ID</th>
                        <th>Member Name</th>
                        <th>Status</th>
                        <th>Issue Date</th>
                        <th>Issued By</th>
                        <th>Return Date</th>
                        <th>Returned By</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Map<String, Object>> displayRecords = (List<Map<String, Object>>) request.getAttribute("displayRecords");

                        if (displayRecords != null && !displayRecords.isEmpty()) {
                            for (Map<String, Object> record : displayRecords) {
                    %>
                                <tr>
                                    <td><%= record.get("issueId") %></td>
                                    <td><%= record.get("bookId") %></td>
                                    <td><%= record.get("bookTitle") %></td>
                                    <td><%= record.get("memberId") %></td>
                                    <td><%= record.get("memberName") %></td>
                                    <td><%= record.get("status") %></td>
                                    <td><%= record.get("issueDate") %></td>
                                    <td><%= record.get("issuedBy") %></td>
                                    <td><%= record.get("returnDate") %></td>
                                    <td><%= record.get("returnedBy") %></td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="10" class="center-text">No Issued records found in the library.</td>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <p id="messageLabel" class="message-label" style="color: <%= (request.getAttribute("messageType") != null && 
            request.getAttribute("messageType").equals("error")) ? "red" : "red" %>;">
                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                        out.println(message);
                    }
                %>
            </p>

            <div class="button-group">
                <button type="submit" name="action" value="refreshRecords">Refresh Records</button>
                <button type="button" onclick="location.href='main'">Back to Main Menu</button>
            </div>
        </form>
    </div>
</body>
</html>
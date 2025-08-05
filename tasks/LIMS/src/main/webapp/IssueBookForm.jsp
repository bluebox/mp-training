<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="library.model.Book" %>
<%@ page import="library.model.Member" %>
<%@ page import="library.model.enums.BookAvailability" %>
<%@ page import="library.model.enums.BookStatus" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue Book</title>
    <style>
        .container {
            text-align: center;
            padding: 20px;
        }
        .main-content {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
            align-items: flex-start;
        }
        .input-grid {
            display: grid;
            grid-template-columns: auto 1fr;
            gap: 10px;
            text-align: right;
        }
        .input-grid input[type="text"] {
            width: 200px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .display-panel {
            flex-grow: 1;
            max-width: 400px;
            text-align: center;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        .display-panel textarea {
            width: 100%;
            height: 200px;
            resize: vertical;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-family: monospace;
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
        .message-label {
            margin-top: 15px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Issue Book</h1>

        <form action="issueBook" method="post">
            <div class="main-content">
                <div class="input-grid">
                    <label for="bookIdField">Book ID:</label>
                    <input type="text" id="bookIdField" name="bookId" placeholder="Enter Book ID" 
                           value="<%= request.getAttribute("bookId") != null ? request.getAttribute("bookId") : "" %>" />
                    
                    <label for="memberIdField">Member ID:</label>
                    <input type="text" id="memberIdField" name="memberId" placeholder="Enter Member ID"
                           value="<%= request.getAttribute("memberId") != null ? request.getAttribute("memberId") : "" %>" />
                </div>

                <div class="display-panel">
                    <h2 style="font-size: 16px; font-weight: bold;">
                        <%= request.getAttribute("displayListLabel") != null ? request.getAttribute("displayListLabel") : "Available Items List" %>
                    </h2>
                    <textarea id="displayArea" readonly>
                        <%= request.getAttribute("displayContent") != null ? request.getAttribute("displayContent") : "" %>
                    </textarea>
                    <div class="button-group">
                        <button type="submit" name="action" value="showBooks">Show Books</button>
                        <button type="submit" name="action" value="showMembers">Show Members</button>
                    </div>
                </div>
            </div>

            <p id="messageLabel" class="message-label" style="color: <%= (request.getAttribute("messageType") != null && 
            request.getAttribute("messageType").equals("error")) ? "red" : "green" %>;">
                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                        out.println(message);
                    }
                %>
            </p>

            <div class="button-group">
                <button type="submit" name="action" value="issueBook">Issue Book</button>
                <button type="button" onclick="location.href='main'">Back to Main Menu</button>
            </div>
        </form>
    </div>
</body>
</html>
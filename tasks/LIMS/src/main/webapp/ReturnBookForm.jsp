<%@ page import="java.util.List" %>
<%@ page import="library.model.IssueRecord" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Return Book</title>
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
            max-width: 500px; 
            text-align: center;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        .display-panel textarea {
            width: 100%;
            height: 300px;
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
        <h1>Return Book</h1>

        <form action="returnBook" method="post">
            <div class="main-content">
                <div class="input-grid">
                    <label for="bookIdField">Book ID:</label>
                    <input type="text" id="bookIdField" name="bookId" placeholder="Enter Book ID to Return"
                           value="<%= request.getAttribute("bookId") != null ? request.getAttribute("bookId") : "" %>" />
                </div>

                <div class="display-panel">
                    <h2 style="font-size: 16px; font-weight: bold;">Currently Issued Books</h2>
                    <textarea id="displayArea" readonly>
                        <%= request.getAttribute("displayContent") != null ? request.getAttribute("displayContent") : "" %>
                    </textarea>
                    
                    <div class="button-group">
                        <button type="submit" name="action" value="showIssuedBooks">Show Issued Books</button>
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
                <button type="submit" name="action" value="returnBook">Return Book</button>
                <button type="button" onclick="location.href='main'">Back to Main Menu</button>
            </div>
        </form>
    </div>
</body>
</html>
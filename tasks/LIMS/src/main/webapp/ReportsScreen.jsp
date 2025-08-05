<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Reports</title>
    <style>
        .container {
            text-align: center;
            padding: 20px;
        }
        .button-row {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-bottom: 20px;
        }
        .button-row button {
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
            color: white;
        }
        textarea {
            width: 600px; 
            height: 400px; 
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-family: monospace; 
            resize: vertical; 
            white-space: pre; 
            overflow: auto; 
        }
        .message-label {
            margin-top: 10px;
            font-weight: bold;
        }
        .back-button-group {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .back-button-group button {
            color: white;
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Library Reports</h1>

        <form action="reports" method="post">
            <div class="button-row">
                <button type="submit" name="action" value="overdueBooks">Overdue Books</button>
                <button type="submit" name="action" value="booksByCategory">Books Per Category</button>
                <button type="submit" name="action" value="membersWithActiveBooks">Members with Active Books</button>
            </div>
            
            <textarea id="reportDisplayArea" readonly><%= request.getAttribute("reportContent") != null ? request.getAttribute("reportContent") : "" %></textarea>
            
            <p id="messageLabel" class="message-label" style="color: <%= (request.getAttribute("messageType") != null && 
            request.getAttribute("messageType").equals("error")) ? "red" : "green" %>;">
                <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
            </p>

            <div class="back-button-group">
                <button type="button" onclick="location.href='main'">Back to Main Menu</button>
            </div>
        </form>
    </div>
</body>
</html>
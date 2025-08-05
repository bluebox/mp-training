<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="library.model.enums.Gender" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Member Details</title>
    <style>
        .container {
            text-align: center;
            padding: 20px;
        }
        .form-grid {
            display: grid;
            grid-template-columns: auto 1fr;
            gap: 10px;
            max-width: 500px;
            margin: 0 auto;
            text-align: right;
        }
        .form-grid label {
            padding-right: 10px;
            line-height: 25px;
        }
        .form-grid input[type="text"] {
            width: 100%;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        .form-grid input[readonly] {
            background-color: #eee;
        }
        .gender-options {
            display: flex;
            align-items: center;
            gap: 15px;
            justify-content: flex-start;
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
            margin-top: 10px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Update Member Details</h1>

        <form action="updateMember" method="post">
            <div class="form-grid">
                <label for="memberIDField">Member ID:</label>
                <input type="text" id="memberIDField" name="memberID" readonly
                       value="<%= request.getAttribute("memberID") != null ? request.getAttribute("memberID") : "" %>" />

                <label for="nameField">Name:</label>
                <input type="text" id="nameField" name="name"
                       value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>" />

                <label for="emailField">Email:</label>
                <input type="text" id="emailField" name="email"
                       value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" />

                <label for="phoneNumberField">Phone Number:</label>
                <input type="text" id="phoneNumberField" name="phoneNumber"
                       value="<%= request.getAttribute("phoneNumber") != null ? request.getAttribute("phoneNumber") : "" %>" />

                <label>Gender:</label>
                <div class="gender-options">
                    <%
                        String selectedGender = (String) request.getAttribute("selectedGender");
                    %>
                    <input type="radio" id="maleRadioButton" name="gender" value="MALE"
                           <%= "MALE".equals(selectedGender) ? "checked" : "" %> />
                    <label for="maleRadioButton">Male</label>

                    <input type="radio" id="femaleRadioButton" name="gender" value="FEMALE"
                           <%= "FEMALE".equals(selectedGender) ? "checked" : "" %> />
                    <label for="femaleRadioButton">Female</label>

                    <input type="radio" id="otherRadioButton" name="gender" value="OTHER"
                           <%= "OTHER".equals(selectedGender) ? "checked" : "" %> />
                    <label for="otherRadioButton">Other</label>
                </div>

                <label for="addressField">Address:</label>
                <input type="text" id="addressField" name="address"
                       value="<%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %>" />
            </div>

            <p id="updateStatusLabel" class="message-label" style="color: <%= (request.getAttribute("messageType") != null && 
            request.getAttribute("messageType").equals("error")) ? "red" : "green" %>;">
                <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
            </p>

            <div class="button-group">
                <button type="submit">Update</button>
                <%-- Using history.back() to mimic Cancel's behavior of closing and returning --%>
                <button type="button" onclick="history.back()">Cancel</button>
            </div>
        </form>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View All Members</title>
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
        .action-buttons button {
            margin-right: 5px;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            color: white;
            font-size: 0.9em; 
        }
      
        .message-label {
            margin-top: 10px;
            font-weight: bold;
        }
        .bottom-button-group {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 20px;
        }
        .bottom-button-group button {
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
        <h1>View All Members</h1>

        <form action="viewMembers" method="post">
            <table>
                <thead>
                    <tr>
                        <th class="center-text">Select</th>
                        <th>Member ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Map<String, Object>> displayMembers = (List<Map<String, Object>>) request.getAttribute("displayMembers");

                        if (displayMembers != null && !displayMembers.isEmpty()) {
                            for (Map<String, Object> member : displayMembers) {
                    %>
                                <tr>
                                    <td class="center-text">
                                        <input type="checkbox" name="selectedMemberIds" value="<%= member.get("memberID") %>"/>
                                    </td>
                                    <td><%= member.get("memberID") %></td>
                                    <td><%= member.get("name") %></td>
                                    <td><%= member.get("email") %></td>
                                    <td><%= member.get("phoneNumber") %></td>
                                    <td><%= member.get("gender") %></td>
                                    <td><%= member.get("address") %></td>
                                    <td class="action-buttons">
                                        <%-- Changed from <a> tag to <button> --%>
                                        <button type="button" class="update-button" 
                                                onclick="location.href='updateMember?memberId=<%= member.get("memberID") %>'">Update</button>
                                        <button type="submit" name="action" value="deleteMember:<%= member.get("memberID") %>" class="delete-button"
                                                onclick="return confirm('Are you sure you want to delete member ID: <%= member.get("memberID") %> (<%= member.get("name") %>)?')">Delete</button>
                                    </td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="8" class="center-text">No members found in the library.</td>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <p id="statusLabel" class="message-label" style="color: <%= (request.getAttribute("messageType") != null && 
            request.getAttribute("messageType").equals("error")) ? "red" : "green" %>;">
                <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
            </p>

            <div class="bottom-button-group">
                <button type="submit" name="action" value="refreshMembers">Refresh</button>
                <button type="submit" name="action" value="deleteSelectedMembers" 
                        onclick="return confirm('Are you sure you want to delete the selected members?')">Delete Selected</button>
                <button type="button" onclick="location.href='main'">Back to Main Menu</button>
            </div>
        </form>
    </div>
</body>
</html>
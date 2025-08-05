<%@ page language="java" import="java.util.*,com.lms.model.Member" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book's Members</title>
    <style>
        .books-members-container {
            max-width: 800px;
            margin: 20px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .books-members-title {
            color: #34495e;
            text-align: center;
            margin-bottom: 30px;
            font-size: 2em;
            font-weight: 600;
        }

        .books-members-form {
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
            display: flex;
            gap: 10px;
            align-items: flex-end;
        }

        .form-label {
            display: block;
            margin-bottom: 8px;
            color: #34495e;
            font-weight: 500;
            font-size: 0.95em;
        }

        .form-select {
            flex: 1;
            padding: 12px;
            border: 2px solid #7f8c8d;
            border-radius: 6px;
            font-size: 1em;
            color: #34495e;
            background-color: white;
            cursor: pointer;
        }

        .form-select:focus {
            border-color: #34495e;
            outline: none;
        }

        .form-button {
            padding: 12px 24px;
            background-color: #34495e;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 1em;
            font-weight: 500;
            cursor: pointer;
            height: 45px;
        }

        .form-button:hover {
            background-color: #7f8c8d;
        }

        .members-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .members-table th {
            background-color: #34495e;
            color: white;
            padding: 12px;
            text-align: left;
        }

        .members-table td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }

        .members-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .no-content {
            text-align: center;
            color: #7f8c8d;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="books-members-container">
        <h1 class="books-members-title">Books Members</h1>
        <%  
            List<Member> membersList = (List<Member>) request.getAttribute("membersList");
            List<String> booksList = (List<String>) request.getAttribute("booksList");
        %>
        
        <form class="books-members-form" action="BooksMembersServlet" method="post">
            <div class="form-group">
                <div style="flex: 1;">
                    <label class="form-label" for="books">Select Book:</label>
                    <select class="form-select" id="books" name="books" required>
                        <option value="" disabled selected>Select a Book</option>
                        <c:forEach var="book" items="${booksList}">
                            <option value="${book}" <c:if test="${book == selectedBook}">selected</c:if>>${book}</option>
                        </c:forEach>
                    </select>
                </div>
                <input class="form-button" type="submit" name="action" value="GetMembers"/>
            </div>
        </form>

        <table class="members-table">
            <thead>
                <tr>
                    <th>Member Id</th>
                    <th>Name</th>
                    <th>Email Id</th>
                    <th>Mobile No</th>
                </tr>
            </thead>
            <tbody>
                <% if(!(membersList==null) && !(membersList.size()==0)){
                    for(Member member : membersList){%>
                    <tr>
                        <td><%= member.getMember_Id()%></td>
                        <td><%= member.getMember_Name() %></td>
                        <td><%= member.getEmail()%></td>
                        <td><%= member.getMobile_No() %></td>
                    </tr>
                <%} 
                } else {%>
                    <tr>
                        <td colspan="4" class="no-content">No Content In Table</td>
                    </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</body>
</html>

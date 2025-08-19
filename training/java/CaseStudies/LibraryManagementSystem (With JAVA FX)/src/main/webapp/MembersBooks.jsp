<%@ page language="java" import="java.util.*,com.lms.model.Book" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Members Books</title>
    <style>
        .members-books-container {
            max-width: 800px;
            margin: 20px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .members-books-title {
            color: #34495e;
            text-align: center;
            margin-bottom: 30px;
            font-size: 2em;
            font-weight: 600;
        }

        .members-books-form {
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            display: block;
            margin-bottom: 8px;
            color: #34495e;
            font-weight: 500;
            font-size: 0.95em;
        }

        .form-select, .form-input {
            width: 100%;
            padding: 12px;
            border: 2px solid #7f8c8d;
            border-radius: 6px;
            font-size: 1em;
            color: #34495e;
            box-sizing: border-box;
        }

        .search-group {
            display: flex;
            gap: 10px;
            align-items: flex-end;
        }

        .search-input-container {
            flex: 1;
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

        .books-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .books-table th {
            background-color: #34495e;
            color: white;
            padding: 12px;
            text-align: left;
        }

        .books-table td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }

        .books-table tr:nth-child(even) {
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
    <div class="members-books-container">
        <h1 class="members-books-title">Members Books</h1>
        <%  
            List<String> membersList = (List<String>) request.getAttribute("membersList");
            List<Book> booksList = (List<Book>) request.getAttribute("booksList");
            String email=request.getAttribute("email").toString();
        %>
        
        <form class="members-books-form" action="MembersBooksServlet" method="post">
            <div class="form-group">
                <label class="form-label" for="members">Select Member:</label>
                <select class="form-select" id="members" name="members">
                    <option value="" disabled selected>Select a Member</option>
                    <c:forEach var="member" items="${membersList}">
                        <option value="${member}" <c:if test="${member == selectedMember}">selected</c:if>>${member}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <div class="search-group">
                    <div class="search-input-container">
                        <label class="form-label" for="email">Or Email of Member:</label>
                        <input class="form-input" id="email" name="email" type="text" value="${email}"/>
                    </div>
                    <input class="form-button" type="submit" name="action" value="Search"/>
                </div>
            </div>
        </form>

        <table class="books-table">
            <thead>
                <tr>
                    <th>Book Id</th>
                    <th>Title</th>
                    <th>Author</th>
                </tr>
            </thead>
            <tbody>
                <% if(!(booksList==null) && !(booksList.size()==0)){
                    for(Book book : booksList){%>
                    <tr>
                        <td><%= book.getBook_Id()%></td>
                        <td><%= book.getBook_Title() %></td>
                        <td><%= book.getBook_Author() %></td>
                    </tr>
                <%} 
                } else {%>
                    <tr>
                        <td colspan="3" class="no-content">No Content In Table</td>
                    </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</body>
</html>

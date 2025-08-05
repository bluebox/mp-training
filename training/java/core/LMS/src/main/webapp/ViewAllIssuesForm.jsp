<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View All Issues</title>
    <link rel="stylesheet" href="css/viewbook.css"> 
    <style>
        
        .table-container {
            width: 90%;
            margin: 30px auto;
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        .back-button {
            padding: 10px 20px;
            background-color: red;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <div class="table-container">
        <h2 style="text-align: center;">All Issues</h2>

   
        <table>
            <thead>
                <tr>
                    <th>Issue ID</th>
                    <th>Book ID</th>
                    <th>Member ID</th>
                  <!--   <th>Availability</th> -->
                    <th>Issue Date</th>
                    <th>Return Date</th>
                </tr>
            </thead>
            <tbody>
              
                <c:forEach var="issue" items="${issuesList}">
                    <tr>
                        <td>${issue.issueId}</td>
                        <td>${issue.bookId}</td>
                        <td>${issue.memberId}</td>
                      <%--   <td>${issue.availability}</td> --%>
                        <td>${issue.issueDate}</td>
                        <td>${issue.returnDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    
        <div style="text-align: center; margin-top: 20px;">
            <button class="back-button" onclick="window.location.href='IssueAndReturn.jsp'">Back</button>
        </div>
    </div>

</body>
</html>

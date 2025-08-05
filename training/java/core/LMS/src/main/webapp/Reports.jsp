<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reports</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reports.css">
    <style>
        .header {
            font-size: 18px;
            font-weight: bold;
            text-align: center;
            margin-top: 20px;
        }
        .button-panel {
            float: left;
            width: 200px;
            margin: 70px 0 0 50px;
        }
        .button-panel form {
            margin-bottom: 20px;
        }
        .report-table {
            margin-left: 270px;
            margin-top: 70px;
            display: none;
            width: 500px;
        }
        .visible {
            display: block !important;
        }
    </style>
</head>
<body>

    <div class="header">Reports</div>

    <div class="button-panel">
        <form action="ReportsController" method="post">
            <button name="action" value="overdue" class="book-button">Overdue Books</button>
        </form>
        <form action="ReportsController" method="post">
            <button name="action" value="categoryCount" class="book-button">Count of Books per Category</button>
            
        </form>
        <form action="ReportsController" method="post">
            <button name="action" value="activeMembers" class="book-button">Active Issued Members</button>
        </form>
        <form action="MainPage.jsp" method="get">
            <button class="book-buttons" style="color: red; width: 160px;">Back</button>
        </form>
    </div>

    <div class="report-table" id="overdueTable" style="${requestScope.action eq 'overdue' ? 'display:block;' : ''}">
        <table border="1" width="100%">
            <tr><th>Title</th><th>Member</th><th>Due Date</th></tr>
            <c:forEach var="book" items="${overdueBooks}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.member}</td>
                    <td>${book.dueDate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="report-table" id="categoryTable" style="${requestScope.action eq 'categoryCount' ? 'display:block;' : ''}">
        <table border="1" width="100%">
            <tr><th>Category</th><th>Count</th></tr>
            <c:forEach var="category" items="${categoryCounts}">
                <tr>
                    <td>${category.category}</td>
                    <td>${category.count}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="report-table" id="membersTable" style="${requestScope.action eq 'activeMembers' ? 'display:block;' : ''}">
        <table border="1" width="100%">
            <tr><th>Member Name</th><th>Books Issued</th></tr>
            <c:forEach var="member" items="${activeMembers}">
                <tr>
                    <td>${member.name}</td>
                    <td>${member.booksIssued}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Reports</title>
    <style>
        

        .reports-container {
            height: 100vh;
            padding: 20px;
            font-family: Arial, sans-serif;
            overflow-y: auto;
            box-sizing: border-box;
        }

        .reports-container h2 {
            color: #34495e;
            margin: 20px 0;
            padding-bottom: 10px;
            border-bottom: 2px solid #7f8c8d;
            position: sticky;
            top: 0;
            background-color: white;
            z-index: 1;
        }

        .table-wrapper {
            max-height: 300px;
            overflow-y: auto;
            margin-bottom: 30px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .reports-container table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        .reports-container thead {
            position: sticky;
            top: 0;
            z-index: 1;
        }

        .reports-container th {
            background-color: #34495e;
            color: white;
            padding: 12px;
            text-align: left;
        }

        .reports-container td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }

        .reports-container tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .reports-container .report-section {
            margin-bottom: 40px;
        }

        /* Scrollbar styling */
        .reports-container::-webkit-scrollbar,
        .table-wrapper::-webkit-scrollbar {
            width: 8px;
        }

        .reports-container::-webkit-scrollbar-track,
        .table-wrapper::-webkit-scrollbar-track {
            background: #f1f1f1;
        }

        .reports-container::-webkit-scrollbar-thumb,
        .table-wrapper::-webkit-scrollbar-thumb {
            background: #7f8c8d;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="reports-container">
        <div class="report-section">
            <h2>Overdue Books Report</h2>
            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Issue ID</th>
                            <th>Book ID</th>
                            <th>Title</th>
                            <th>Member</th>
                            <th>Due Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${overdueBooks}" var="book">
                            <tr>
                                <td>${book.issueId}</td>
                                <td>${book.bookId}</td>
                                <td>${book.title}</td>
                                <td>${book.memberName}</td>
                                <td>${book.overDueDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="report-section">
            <h2>Books by Category</h2>
            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Category</th>
                            <th>Total Books</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categoryStats}" var="category">
                            <tr>
                                <td>${category.key}</td>
                                <td>${category.value}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="report-section">
            <h2>Member Statistics</h2>
            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Member ID</th>
                            <th>Name</th>
                            <th>Books Issued</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${memberStats}" var="member">
                            <tr>
                                <td>${member.memberId}</td>
                                <td>${member.name}</td>
                                <td>${member.booksActiveString}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>

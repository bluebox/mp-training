<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.lms.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reports</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lms-style.css" />
    
</head>
<body>
<div class="container">
    <h2>Library Management System - Reports</h2>

    <form method="get" action="ReportsServlet">
        <label for="reportType">Select Report:</label>
        <select name="reportType" id="reportType" onchange="this.form.submit()">
            <option value="Overdue Books" ${param.reportType == 'Overdue Books' ? 'selected' : ''}>Overdue Books</option>
            <option value="Books by Category" ${param.reportType == 'Books by Category' ? 'selected' : ''}>Books by Category</option>
            <option value="Active Members" ${param.reportType == 'Active Members' ? 'selected' : ''}>Active Members</option>
        </select>
    </form>

    <hr/>

    <%
        String reportType = (String) request.getAttribute("reportType");
        String error = (String) request.getAttribute("error");
    %>

    <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
    <% } else if ("Overdue Books".equals(reportType)) { 
        List<Book> books = (List<Book>) request.getAttribute("overdueBooks");
    %>
        <h3>Overdue Books</h3>
        <table border="1" cellpadding="8">
            <tr><th>ID</th><th>Title</th><th>Author</th><th>Category</th></tr>
            <% for (Book b : books) { %>
                <tr>
                    <td><%= b.getBookId() %></td>
                    <td><%= b.getBookTitle() %></td>
                    <td><%= b.getBookAuthor() %></td>
                    <td><%= b.getBookCategory() %></td>
                </tr>
            <% } %>
        </table>

    <% } else if ("Books by Category".equals(reportType)) { 
        Map<String, Long> categoryMap = (Map<String, Long>) request.getAttribute("booksByCategory");
    %>
        <h3>Book Count by Category</h3>
        <table border="1" cellpadding="8">
            <tr><th>Category</th><th>Count</th></tr>
            <% for (Map.Entry<String, Long> entry : categoryMap.entrySet()) { %>
                <tr>
                    <td><%= entry.getKey() %></td>
                    <td><%= entry.getValue() %></td>
                </tr>
            <% } %>
        </table>

    <% } else if ("Active Members".equals(reportType)) { 
        List<Member> members = (List<Member>) request.getAttribute("activeMembers");
    %>
        <h3>Members with Active Issued Books</h3>
        <table border="1" cellpadding="8">
            <tr><th>ID</th><th>Name</th><th>Email</th><th>Mobile</th><th>Gender</th><th>Address</th></tr>
            <% for (Member m : members) { %>
                <tr>
                    <td><%= m.getMemberId() %></td>
                    <td><%= m.getName() %></td>
                    <td><%= m.getEmail() %></td>
                    <td><%= m.getMobile() %></td>
                    <td><%= m.getGender() %></td>
                    <td><%= m.getAddress() %></td>
                </tr>
            <% } %>
        </table>
    <% } %>
</div>
</body>
</html>

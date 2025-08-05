<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String contentPage = request.getParameter("page");
    if (contentPage == null) {
        contentPage = (String) request.getAttribute("page");
    }
%>
<html>
<head>
    <title>Library Management System</title>
    <style>
        body {
            display: flex;
            font-family: Arial, sans-serif;
        }
        .sidebar {
            width: 200px;
            background-color: #f4f4f4;
            padding: 15px;
            height: 100vh;
        }
        .sidebar a {
            display: block;
            margin: 10px 0;
            text-decoration: none;
            color: #333;
        }
        .sidebar a:hover {
            text-decoration: underline;
        }
        .content {
            flex: 1;
            padding: 20px;
        }
    </style>
</head>
<body>

    <div class="sidebar">
        <h3>Menu</h3>
        <a href="addBook">Add Book</a>
        <a href="updateBook">Update Book</a> 
        <a href="viewBooks">View All Books</a>
        <hr>
        <a href="addMember">Add Member</a>
        <a href="UpdateMemberServlet">Update Member</a>
        <a href="viewMembers">View All Members</a>
        <hr>
        <a href="home.jsp?page=IssueBook.jsp">Issue Book</a>
        <a href="home.jsp?page=ReturnBook.jsp">Return Book</a>
        <hr>
        <a href="ReportsServlet">Reports</a>
    </div>

    <div class="content">
        <% if (contentPage != null) { %>
            <jsp:include page="<%= contentPage %>" />
        <% } else { %>
            <h2>Library Management System</h2>
            <p>Please select an option from the left menu.</p>
        <% } %>
    </div>

</body>
</html>

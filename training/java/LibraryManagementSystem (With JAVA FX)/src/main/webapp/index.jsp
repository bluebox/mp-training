<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management System</title>
    <style>
        .lms-app {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .lms-app .header {
            background-color: #f8f9fa;
            padding: 20px;
            text-align: center;
            border-bottom: 1px solid #ddd;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .lms-app .logo {
            width: 80px;
            height: 80px;
            margin-right: 20px;
        }

        .lms-app .container {
            display: flex;
            min-height: calc(100vh - 122px);
        }

        .lms-app .content {
            flex: 1;
            padding: 20px;
            border-right: 1px solid #ddd;
        }

        .lms-app .navigation {
            width: 200px;
            padding: 20px;
            background-color: #f8f9fa;
        }

        .lms-app .nav-section {
            margin-bottom: 15px;
        }

        .lms-app .section-title {
            color: #333;
            font-size: 14px;
            font-weight: bold;
            padding: 5px;
            margin-bottom: 8px;
            border-bottom: 1px solid #ddd;
        }

        .lms-app .navigation input[type="submit"] {
            width: 100%;
            margin-bottom: 5px;
            padding: 15px;
            background-color: #4C7298;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .lms-app .navigation input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="lms-app">
        <div class="header">
            <img src="images/library.png" alt="Library Logo" class="logo">
            <h1>Library Management System</h1>
        </div>

        <div class="container">
            <div class="content">
                <%
                    String fileToRender = (String) request.getAttribute("fileToRender");
                    fileToRender = fileToRender == null ? "EmptyComponent.jsp" : fileToRender;
                %>
                
                	
                <jsp:include page="<%=fileToRender%>" />
            </div>

            <div class="navigation">
                <form action="Action" method="POST">
                    <div class="nav-section">
                        <div class="section-title">Book Management</div>
                        <input type="submit" name="homeButton" value="AddBook"/>
                        <input type="submit" name="homeButton" value="ViewBooks"/>
                    </div>

                    <div class="nav-section">
                        <div class="section-title">Member Management</div>
                        <input type="submit" name="homeButton" value="AddMember"/>
                        <input type="submit" name="homeButton" value="ViewMembers"/>
                    </div>

                    <div class="nav-section">
                        <div class="section-title">Issue/Return</div>
                        <input type="submit" name="homeButton" value="IssueBook"/>
                        <input type="submit" name="homeButton" value="ReturnBook"/>
                       	<input type="submit" name="homeButton" value="IssuedRecords"/>
                    </div>

                    <div class="nav-section">
                        <div class="section-title">Reports & Extra Features</div>
                        <input type="submit" name="homeButton" value="Reports"/>
 
                        <input type="submit" name="homeButton" value="MembersBooks"/>
                        <input type="submit" name="homeButton" value="BooksMembers"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

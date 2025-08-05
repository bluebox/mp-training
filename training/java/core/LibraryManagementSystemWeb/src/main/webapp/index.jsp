<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f6f8;
        margin: 0;
        padding: 0;
    }

    h1 {
        background-color: #004080;
        color: white;
        padding: 20px 0;
        margin: 0;
        text-align: center;
    }

    .container {
        max-width: 500px;
        margin: 40px auto;
        padding: 30px;
        background-color: white;
        border-radius: 12px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        text-align: center;
    }

    form {
        margin: 15px 0;
    }

    button {
        width: 80%;
        padding: 12px;
        font-size: 15px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>

<h1>LIBRARY MANAGEMENT SYSTEM</h1>

<div class="container">
    <form action="MainServlet" method="post">
        <button type="submit" name="clickedButton" value="addBook">Add Book</button>
    </form>
    
    <form action="MainServlet" method="post">
        <button type="submit" name="clickedButton" value="viewBooks">View Books</button>
    </form>
    
    <form action="MainServlet" method="post">
        <button type="submit" name="clickedButton" value="addMember">Add Member</button>
    </form>
    
    <form action="MainServlet" method="post">
        <button type="submit" name="clickedButton" value="viewMembers">View Members</button>
    </form>
    
    <form action="MainServlet" method="post">
        <button type="submit" name="clickedButton" value="issueBook">Issue Book</button>
    </form>
    
    <form action="MainServlet" method="post">
        <button type="submit" name="clickedButton" value="returnBook">Return Book</button>
    </form>
    
    <form action="MainServlet" method="post">
        <button type="submit" name="clickedButton" value="reports">Reports</button>
    </form>
</div>

</body>
</html>

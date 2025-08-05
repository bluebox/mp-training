<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 30px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 500px;
            margin: 0 auto;
            background-color: white;
            padding: 25px 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0 20px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        button {
            padding: 10px 18px;
            border: none;
            border-radius: 5px;
            font-size: 15px;
            cursor: pointer;
            margin-right: 10px;
        }
        select {
            width: 100%;
            padding: 8px;
            margin: 10px 0 20px 0;
            border-radius: 5px;
        }

        button[type="submit"] {
            background-color: #4CAF50;
            color: white;
        }

        button[type="button"] {
            background-color: #f44336;
            color: white;
        }

        button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <h2>Edit Book Details</h2>
    <form action="UpdateBookController" method="post">
        <input type="hidden" name="bookId" value="${book.bookId}" />

        Title: <input type="text" name="title" value="${book.title}" /><br/>
        Author: <input type="text" name="author" value="${book.author}" /><br/>
        Category: <%-- <input type="text" name="category" value="${book.category}" /> --%><br/>
        <select name="category" id="category">
                <option value="" disabled selected>Select category</option>
                <option value="fantasy">Fantasy</option>
                <option value="Historical Fiction">Historical Fiction</option>
                <option value="Comedy Horror">Comedy Horror</option>
            </select>
        Status: <%-- <input type="text" name="status" value="${book.status}" /> --%>    <select name="status" >
          <option value="A">A</option>
          <option value="I">I</option>
          </select><br/>
      	

        <button type="submit">Save</button>
        <a href="AddBookOptions.jsp"><button type="button">Cancel</button></a>
    </form>
</body>
</html>

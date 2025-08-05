<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Availability</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            padding: 40px;
        }

        .container {
            max-width: 400px;
            background: #fff;
            margin: auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
        }

        label {
            font-weight: bold;
        }

        select {
            width: 100%;
            padding: 8px;
            margin: 10px 0 20px 0;
            border-radius: 5px;
        }

        button {
            padding: 10px 15px;
            background-color: #4CAF50;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }

        a {
            margin-left: 10px;
            text-decoration: none;
            color: #f44336;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Availability</h2>
        <form action="SaveAvailabilityController" method="post">
            <input type="hidden" name="bookId" value="${book.bookId}" />

            <label>Availability:</label>
          <%-- <input type="text" name="availability" value="${book.availability}" /> --%><br/>
          <select name="availability" >
          <option value="A">A</option>
          <option value="I">I</option>
          </select>
            <button type="submit">Save</button>
            <a href="AddBookOptions.jsp">Cancel</a>
        </form>
    </div>
</body>
</html>

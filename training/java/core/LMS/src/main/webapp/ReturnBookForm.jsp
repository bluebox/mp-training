<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Return Book</title>
    <link rel="stylesheet" href="css/return.css">
    <style>
       
        .status {
            font-size: 14px;
            color: red;
        }
        .form-container {
            width: 50%;
            margin: 50px auto;
            text-align: center;
        }
        .form-group {
            margin-bottom: 20px;
            text-align: left;
        }
        label {
            font-size: 20px;
            color: #333;
        }
        input[type="text"] {
            padding: 8px;
            width: 150px;
            margin-top: 10px;
            font-size: 16px;
        }
        .button-group {
            margin-top: 20px;
        }
        .book-buttons {
            padding: 10px 20px;
            background-color: red;
            color: white;
            border: none;
            cursor: pointer;
        }
        .return-book {
            padding: 10px 20px;
            background-color: green;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Return Book</h2>

     
        <form action="ReturnBookController" method="POST">
          
            <div class="form-group">
                <label for="bookIdField">Enter Book ID:</label>
                <input type="text" id="bookIdField" name="bookId" placeholder="Book ID" />
            </div>

          
            <div class="status">
                <c:if test="${not empty statusMessage}">
                    <p style="color: red;">${statusMessage}</p>
                </c:if>
            </div>

         
            <div class="button-group">
                <button type="submit" name="action" value="submit" class="return-book">Submit</button>
                <button type="submit" name="action" value="back" class="book-buttons">Back</button>
            </div>
        </form>
    </div>

</body>
</html>

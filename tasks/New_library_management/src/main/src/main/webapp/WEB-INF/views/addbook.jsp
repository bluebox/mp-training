<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        padding: 40px;
    }
    form {
        background-color: #fff;
        padding: 20px 30px;
        max-width: 400px;
        margin: 0 auto;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    div {
        margin-bottom: 15px;
        text-align: left;
    }
    label {
        display: block;
        margin-bottom: 6px;
        font-weight: bold;
    }
    input[type="text"] {
        width: 100%;
        padding: 8px 10px;
        box-sizing: border-box;
        border-radius: 4px;
        border: 1px solid #ccc;
    }
    input[type="radio"] {
        margin-right: 6px;
        margin-left: 10px;
    }
    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 18px;
        font-size: 16px;
        cursor: pointer;
        border-radius: 6px;
        transition: background-color 0.3s ease;
    }
    input[type="submit"]:hover {
        background-color: #388E3C;
    }
</style>
</head>
<body>

<form action="addbook" method="post">
    <div>
        <label for="title">Book title:</label>
        <input type="text" name="title" id="title" required />
    </div>
    <div>
        <label for="author">Author:</label>
        <input type="text" name="author" id="author" required />
    </div>
    <div>
        <label for="category">Category:</label>
        <input type="text" name="category" id="category" required />
    </div>
    <div>
        <label>Status:</label>
        <input type="radio" name="status" value="Active" id="statusActive" required />
        <label for="statusActive" style="display:inline; font-weight:normal;">Active</label>
        <input type="radio" name="status" value="InActive" id="statusInactive" />
        <label for="statusInactive" style="display:inline; font-weight:normal;">Inactive</label>
    </div>
    <div>
        <label>Availability:</label>
        <input type="radio" name="availability" value="Available" id="availAvailable" required />
        <label for="availAvailable" style="display:inline; font-weight:normal;">Available</label>
        <input type="radio" name="availability" value="Issued" id="availIssued" />
        <label for="availIssued" style="display:inline; font-weight:normal;">Issued</label>
    </div>
    <input type="submit" value="Add Book" />
</form>

</body>
</html>

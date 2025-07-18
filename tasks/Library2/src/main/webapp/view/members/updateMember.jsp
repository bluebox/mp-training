<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Member</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f3f4f6; padding: 50px; }
        .container { max-width: 400px; margin: auto; padding: 24px; background: #fff; border-radius: 10px; box-shadow: 0 0 8px rgba(0, 0, 0, 0.1); }
        h2 { text-align: center; margin-bottom: 18px; }
        label, select, input, textarea { display: block; width: 100%; margin-bottom: 14px; }
        input, select, textarea { padding: 8px; border: 1px solid #ccc; border-radius: 5px; }
        button { width: 100%; padding: 10px; background: #2563eb; color: white; border: none; border-radius: 6px; cursor: pointer; }
        .success { color: green; text-align: center; margin-bottom: 14px; font-weight: bold; }
        .error { color: red; text-align: center; margin-bottom: 14px; font-weight: bold; }
    </style>
</head>
<body>
<div class="container">
    <h2>Update Member</h2>
    <% String success = (String) request.getAttribute("success"); %>
    <% String error = (String) request.getAttribute("error"); %>
    <% if (success != null) { %>
        <div class="success"><%= success %></div>
    <% } %>
    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>
    <form action="/updatemember" method="post">
        <label for="memberId">Member ID</label>
        <input type="text" name="memberId" id="memberId" placeholder="Enter Member ID" required pattern="\d+">

        <label for="name">Name</label>
        <input type="text" name="name" id="name" placeholder="Enter Name" required>

        <label for="email">Email</label>
        <input type="email" name="email" id="email" placeholder="Enter Email" required>

        <label for="mobile">Mobile</label>
        <input type="text" name="mobile" id="mobile" placeholder="Enter Mobile Number" required pattern="\d+">

        <label for="gender">Gender</label>
        <select name="gender" id="gender" required>
            <option value="" disabled selected>Select Gender</option>
            <option value="M">Male</option>
            <option value="F">Female</option>
        </select>

        <label for="address">Address</label>
        <textarea name="address" id="address" placeholder="Enter Address" required></textarea>

        <button type="submit">Update Member</button>
    </form>
</div>
</body>
</html>
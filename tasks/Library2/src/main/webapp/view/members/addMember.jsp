<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Member</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
            background-color: #f3f4f6;
        }
        form {
            max-width: 500px;
            margin: auto;
            background: #fff;
            padding: 24px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            margin-top: 12px;
            display: block;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            margin-top: 20px;
            padding: 10px;
            width: 100%;
            background-color: #2563eb;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .message {
            margin-top: 15px;
            text-align: center;
            color: green;
            font-weight: bold;
        }
    </style>
</head>
<body>

<form action="${pageContext.request.contextPath}/addmember" method="post">
    <h2>Add Member</h2>

    <label for="name">Name</label>
    <input type="text" id="name" name="name" required />

    <label for="email">Email</label>
    <input type="email" id="email" name="email" required />

    <label for="address">Address</label>
    <input type="text" id="address" name="address" required />

    <label for="mobile">Mobile</label>
    <input type="text" id="mobile" name="mobile"required />

    <label for="gender">Gender</label>
    <select id="gender" name="gender" required>
        <option value="">-- Select Gender --</option>
        <option value="M">Male</option>
        <option value="F">Female</option>
    </select>

    <button type="submit">Add Member</button>

    <div id="messageLabel" style="color:green;">
  			<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
		</div>
</form>

</body>
</html>

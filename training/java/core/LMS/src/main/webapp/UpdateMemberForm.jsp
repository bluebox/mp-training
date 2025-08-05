<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.LibraryManagement.Models.Member" %>
<%
    Member m = (Member) request.getAttribute("member");
    if (m == null) { response.sendRedirect("ViewMembers.jsp"); return; }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Member</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 40px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 500px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 25px 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"],
        input[type="email"],
        select,
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 14px;
        }

        textarea {
            resize: vertical;
        }

        .error {
            color: red;
            font-size: 13px;
            margin-top: 5px;
        }

        button {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 14px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button[type="submit"] {
            background-color: #4CAF50;
            color: white;
            margin-right: 10px;
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
<h2>Update Member</h2>
<form action="UpdateMemberController" method="post">
    <label>Member ID:</label><input type="text" name="memberId" value="<%=m.getMemberId()%>" readonly /><br>

    <label>Name:</label><input type="text" name="name" value="<%=m.getName()%>"/><br>
    <div class="error"><%= request.getAttribute("nameError") != null ? request.getAttribute("nameError") : "" %></div>

    <label>Email:</label><input type="email" name="email" value="<%=m.getEmail()%>"/><br>
    <div class="error"><%= request.getAttribute("emailError") != null ? request.getAttribute("emailError") : "" %></div>

    <label>Mobile:</label><input type="text" name="mobile" value="<%=m.getMobile()%>"/><br>
    <div class="error"><%= request.getAttribute("mobileError") != null ? request.getAttribute("mobileError") : "" %></div>

    <label>Gender:</label>
    <select name="gender">
      <option value="">Select</option>
      <option value="Male" <%= m.getGender()=='M'?"selected":"" %>>Male</option>
      <option value="Female" <%= m.getGender()=='F'?"selected":"" %>>Female</option>
    </select><br>
    <div class="error"><%= request.getAttribute("genderError") != null ? request.getAttribute("genderError") : "" %></div>

    <label>Address:</label><textarea name="address" rows="3"><%=m.getAddress()%></textarea><br>
    <div class="error"><%= request.getAttribute("addressError") != null ? request.getAttribute("addressError") : "" %></div>

    <button type="submit">Update</button>
<button type="submit" >Back</button>
</form>
</body>
</html>   
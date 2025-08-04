<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.lms.model.Member" %>
<%
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Member</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lms-style.css" />

</head>
<body>
<div class="container">
    <h2>Add Member</h2>

    <form method="post" action="addMember">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" placeholder="Enter Your Name" required />

        <label for="email">Email:</label>
        <input type="text" name="email" id="email" placeholder="example@example.com" required />

        <label for="mobile">Mobile Number:</label>
        <input type="text" name="mobile" id="mobile" placeholder="Your Number Please" required />

        <label for="gender">Gender:</label>
        <select name="gender" id="gender" required>
            <option value="" disabled selected>Select Your Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select>

        <label for="address">Address:</label>
        <textarea name="address" id="address" rows="3" placeholder="Enter Your Full Address" required></textarea>

        <input type="submit" value="Add Member" />
    </form>

    <% if (message != null) { %>
        <p class="message"><%= message %></p>
    <% } %>
</div>
</body>
</html>

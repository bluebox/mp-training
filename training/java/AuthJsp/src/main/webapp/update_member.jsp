<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.casestudy.domain.Member, com.casestudy.domain.Gender" %>
<%
    Member member = (Member) request.getAttribute("member");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Member</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        form { width: 500px; margin: auto; border: 1px solid #ccc; padding: 20px; border-radius: 8px; }
        label { display: block; margin-top: 10px; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; }
        .btns { margin-top: 15px; display: flex; justify-content: space-between; }
        .msg { text-align: center; color: green; font-weight: bold; }
        .error { text-align: center; color: red; font-weight: bold; }
    </style>
</head>
<body>

<h2 style="text-align:center;"> Update Member</h2>

<% if (message != null) { %>
    <p class="<%= message.contains("failed") || message.contains("not") ? "error" : "msg" %>"><%= message %></p>
<% } %>

<form method="post" action="UpdateMemberServlet">
    <label for="memberId">Member ID</label>
    <input type="text" name="memberId" id="memberId" value="<%= member != null ? member.getMemberId() : "" %>" <%= member != null ? "readonly" : "" %> required />

    <% if (member == null) { %>
        <div class="btns">
            <input type="submit" name="action" value="Fetch Member" />
        </div>
    <% } else { %>

        <label for="name">Name</label>
        <input type="text" name="name" id="name" maxlength="50" value="<%= member.getName() %>" required />

        <label for="email">Email</label>
        <input type="email" name="email" id="email" maxlength="50" value="<%= member.getEmail() %>" required />

        <label for="mobile">Mobile</label>
        <input type="text" name="mobile" id="mobile" maxlength="10" value="<%= member.getMobile() %>" required />

        <label for="gender">Gender</label>
        <select name="gender" id="gender" required>
            <option value="M" <%= member.getGender() == Gender.MALE ? "selected" : "" %>>M - Male</option>
            <option value="F" <%= member.getGender() == Gender.FEMALE ? "selected" : "" %>>F - Female</option>
        </select>

        <label for="address">Address</label>
        <input type="text" name="address" id="address" maxlength="200" value="<%= member.getAddress() %>" required />

        <div class="btns">
            <input type="submit" name="action" value="Update Member" />
            <a href="update_member.jsp">Reset</a>
        </div>
    <% } %>
</form>

</body>
</html>

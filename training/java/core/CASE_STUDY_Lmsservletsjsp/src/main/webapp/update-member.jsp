<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lms.model.Member" %>
<%
    Member member = (Member) request.getAttribute("member");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Member</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lms-style.css" />
	
</head>
<body>
<div class="container">
    <h2>Update Member</h2>

    <form method="post" action="UpdateMemberServlet">
        <label>Search by Mobile Number:</label>
        <input type="text" name="searchMobile"
               value="<%= request.getParameter("searchMobile") != null ? request.getParameter("searchMobile") : "" %>" required/>
        <input type="submit" name="action" value="Fetch Member" />
    </form>

    <hr/>

    <form method="post" action="UpdateMemberServlet">
        <input type="hidden" name="memberId" value="<%= member != null ? member.getMemberId() : "" %>"/>

        <label>Name:</label>
        <input type="text" name="name" value="<%= member != null ? member.getName() : "" %>" required />

        <label>Email:</label>
        <input type="text" name="email" value="<%= member != null ? member.getEmail() : "" %>" required />

        <label>Mobile:</label>
        <input type="text" name="mobile" value="<%= member != null ? member.getMobile() : "" %>" required />

        <label>Gender:</label>
        <select name="gender" required>
            <option value="Male" <%= member != null && "Male".equals(member.getGender()) ? "selected" : "" %>>Male</option>
            <option value="Female" <%= member != null && "Female".equals(member.getGender()) ? "selected" : "" %>>Female</option>
            <option value="Other" <%= member != null && "Other".equals(member.getGender()) ? "selected" : "" %>>Other</option>
        </select>

        <label>Address:</label>
        <textarea name="address" rows="3" required><%= member != null ? member.getAddress() : "" %></textarea>

        <input type="submit" name="action" value="Update Member" />
    </form>

    <% if (message != null) { %>
        <p class="error"><%= message %></p>
    <% } %>
</div>
</body>
</html>

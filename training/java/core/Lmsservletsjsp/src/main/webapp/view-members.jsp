<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lms.model.Member" %>

<html>
<head>
    <title>All Members</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lms-style.css" />

</head>
<body>
<div class="container">
    <h2>All Members</h2>

    <%
        List<Member> members = (List<Member>) request.getAttribute("members");
        if (members != null) {
            out.println("<p style='text-align:center;'>Total members: " + members.size() + "</p>");
        }
    %>

    <%
        if (members != null && !members.isEmpty()) {
    %>
    <table>
        <tr>
            <th>Member ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Mobile</th>
            <th>Address</th>
            <th>Email</th>
        </tr>
        <% for (Member m : members) { %>
        <tr>
            <td><%= m.getMemberId() %></td>
            <td><%= m.getName() %></td>
            <td><%= m.getGender() %></td>
            <td><%= m.getMobile() %></td>
            <td><%= m.getAddress() %></td>
            <td><%= m.getEmail() %></td>
        </tr>
        <% } %>
    </table>
    <% } else { %>
        <p style="text-align:center;">No members found.</p>
    <% } %>
    </div>
</body>
</html>

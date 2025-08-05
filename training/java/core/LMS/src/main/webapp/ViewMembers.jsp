<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.LibraryManagement.Models.Member" %>
<%
    List<Member> members = (List<Member>) request.getAttribute("membersList");
    String successMsg = (String) request.getAttribute("successMessage");
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>View Members</title></head>
<body>
    <h2>All Members</h2>
    <% if (successMsg != null) { %>
        <div style="color: green;"><%= successMsg %></div>
    <% } %>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr><th>ID</th><th>Name</th><th>Email</th><th>Mobile</th>
            <th>Gender</th><th>Address</th><th>Action</th>
        </tr>
        <% if (members != null && !members.isEmpty()) {
               for (Member m : members) { %>
        <tr>
            <td><%= m.getMemberId() %></td>
            <td><%= m.getName() %></td>
            <td><%= m.getEmail() %></td>
            <td><%= m.getMobile() %></td>
            <td><%= m.getGender() %></td>
            <td><%= m.getAddress() %></td>
            <td>
                <form action="LoadMemberForEditController" method="get">
                    <input type="hidden" name="memberId" value="<%= m.getMemberId() %>"/>
                    <button type="submit">Edit</button>
                </form>
            </td>
        </tr>
        <%     }
           } else { %>
        <tr><td colspan="7" align="center">No members found.</td></tr>
        <% } %>
    </table>
    <br>
    <button onclick="window.location.href='AddMemberOptions.jsp'">Back</button>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Member" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Members</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
            background-color: #f9fafb;
        }
        h2 {
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #ffffff;
            box-shadow: 0 0 8px rgba(0, 0, 0, 0.08);
        }
        th, td {
            padding: 12px 16px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #e2e8f0;
        }
    </style>
</head>
<body>

<h2>All Members</h2>

<table>
    <thead>
        <tr>
            <th>Member ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Gender</th>
            <th>Address</th>
        </tr>
    </thead>
    <tbody>
<%
    List<Member> members = (List<Member>) request.getAttribute("members");
    if (members != null) {
        for (Member member : members) {
            String gender = member.getGender() == 'M' ? "Male" : member.getGender() == 'F' ? "Female" : "Other";
%>
        <tr>
            <td><%= member.getMemberId() %></td>
            <td><%= member.getName() %></td>
            <td><%= member.getEmail() %></td>
            <td><%= member.getMobile() %></td>
            <td><%= gender %></td>
            <td><%= member.getAddress() %></td>
        </tr>
<%
        }
    }
%>
    </tbody>
</table>

</body>
</html>

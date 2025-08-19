<%@ page language="java" import="java.util.List,java.util.ArrayList,com.lms.model.Member" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Members</title>
    <style>
        .table-container {
            font-family: Arial, sans-serif;
            margin: 20px;
            color: #34495e;
            max-height: 700px;
            overflow-y: auto;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .table-container h1 {
            color: #34495e;
            margin-bottom: 30px;
        }

        .table-container table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        .table-container thead {
            position: sticky;
            top: 0;
            z-index: 1;
        }

        .table-container th,
        .table-container td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #7f8c8d;
        }

        .table-container th {
            background-color: #34495e;
            color: white;
        }

        .table-container tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .table-container button {
            background-color: #34495e;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
        }

        .table-container button:hover {
            background-color: #7f8c8d;
        }

        .no-items {
            color: #888;
            font-style: italic;
            text-align: center;
        }
    </style>
    <%
        List<Member> members = (List<Member>) request.getAttribute("membersList");
        members = members==null ? new ArrayList<Member>() : members;
    %>
</head>
<body>
    <div class="table-container">
        <h1>View Members</h1>
        <table>
            <thead>
                <tr>
                    <th>Member ID</th>
                    <th>Name</th>
                    <th>Email Id</th>
                    <th>Mobile Number</th>
                    <th>Gender</th>
                    <th>Address</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% if (members.isEmpty()) { %>
                <tr>
                    <td colspan="7" class="no-items">No Items To Display</td>
                </tr>
                <% } else {
                    for(Member member : members){ %>
                    <tr>
                        <td><%= member.getMember_Id() %></td>
                        <td><%= member.getMember_Name() %></td>
                        <td><%= member.getEmail() %></td>
                        <td><%= member.getMobile_No() %></td>
                        <td><%= member.getGender() %></td>
                        <td><%= member.getAddress() %></td>
                        <td>
                            <form action="ViewMembersServlet" style="margin:0;">
                                <input type="hidden" name="memberId" value="<%= member.getMember_Id() %>" />
                                <input type="hidden" name="name" value="<%= member.getMember_Name() %>" />
                                <input type="hidden" name="email" value="<%= member.getEmail() %>" />
                                <input type="hidden" name="mobile" value="<%= member.getMobile_No() %>" />
                                <input type="hidden" name="gender" value="<%= member.getGender() %>" />
                                <input type="hidden" name="address" value="<%= member.getAddress() %>" />
                                <button type="submit" name="action" value="Update">Update</button>
                            </form>
                        </td>
                    </tr>
                <% }} %>
            </tbody>
        </table>
    </div>
</body>
</html>

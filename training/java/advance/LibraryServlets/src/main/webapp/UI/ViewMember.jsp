<%@page import="com.library.domain.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Details</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/UI/styles.css">


</head>
<body>
<h1>Members</h1>
	<table>
		<tr>
			<th>ID</th>
			<th>name</th>
			<th>email</th>
			<th>mobile</th>
			<th>gender</th>
			<th>address</th>
		</tr>
		<%
        	List<Member> members=(List<Member>) request.getAttribute("membersList");
        	for(Member member:members)
        	{
        	
        %>
        <tr> 
        	<td> <%=member.getId() %> </td>
        	<td> <%=member.getName() %> </td>
        	<td> <%=member.getEmail()   %> </td>
        	<td> <%=member.getMobile() %> </td>
        	<td> <%=member.getGender() %> </td>
        	<td> <%=member.getAddress() %> </td>
        </tr>
        <%
        }%>



	</table>

</body>
</html>
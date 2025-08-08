<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List, com.mvcExample.spring.model.Menu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Restaurant Menu</title>
</head>
<body>
<h3>Menu</h3>
<%
		List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
		if (menuList == null || menuList.isEmpty()) {
		%>
		<p>No Menu.</p>
		<%
		} else {
		%>
		<table>
			<thead>
				<tr>
					<th>Item </th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Menu menu : menuList) {
				%>
				<tr>
					<td><%=menu.getName()%></td>
					<td><%=menu.getPrice()%></td>
				</tr><%}} %>
				</tbody>
				</table>
				<form action="home" method="get">
		<input type="submit" value="Back">
	</form>
</body>
</html>
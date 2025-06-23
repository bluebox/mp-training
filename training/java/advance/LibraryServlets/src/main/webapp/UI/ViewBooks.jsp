<%@page import="com.library.domain.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Details</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/UI/styles.css">

</head>
<body>

	<h1>Books</h1>
	<table>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>author</th>
			<th>Category</th>
			<th>Status</th>
			<th>Availability</th>
		</tr>
		<%
        	List<Book> books=(List<Book>) request.getAttribute("booksList");
        	for(Book book:books)
        	{
        	
        %>
        <tr> 
        	<td> <%=book.getBookId() %> </td>
        	<td> <%=book.getTitle() %> </td>
        	<td> <%=book.getAuthor()   %> </td>
        	<td> <%=book.getCategory() %> </td>
        	<td> <%=book.getStatus() %> </td>
        	<td> <%=book.getAvailability() %> </td>
        </tr>
        <%
        }%>



	</table>

</body>
</html>
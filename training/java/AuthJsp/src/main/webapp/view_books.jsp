<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.casestudy.dao.BooksDao" %>
<%@ page import="com.casestudy.domain.Book" %>
<%@ page import="com.casestudy.domain.Status" %>
<%@ page import="com.casestudy.domain.Availability" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{
margin : auto;
border-collapse : collapse;
}

table td, th {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>

<%
    BooksDao dao = new BooksDao();
    List<Book> books = dao.viewAllBooks();
%>
<table border="1">
<tr><th>ID</th><th>Title</th><th>Author</th><th>Category</th><th>Status</th><th>Availability</th></tr>
<% for(Book b : books) { %>
<tr>
    <td><%= b.getBookId() %></td>
    <td><%= b.getTitle() %></td>
    <td><%= b.getAuthor() %></td>
    <td><%= b.getCategory() %></td>
    <td><%= b.getStatus().name() %></td>
    <td><%= b.getAvailable().name() %></td>
</tr>
<% } %>
</table>



</body>
</html>
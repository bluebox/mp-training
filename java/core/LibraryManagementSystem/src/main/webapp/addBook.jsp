<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="Pojo.Book" %>
<%@ page import="Service.LibraryService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book</title>
</head>
<body>

	<%
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		String status = request.getParameter("status");
		String availability = request.getParameter("availability");

        try {
        	Book book=new Book();
            book.setAuthor(author);
            book.setAvailability(availability.toUpperCase().charAt(0));
            book.setCategory(category);
            book.setStatus(status.toUpperCase().charAt(0));
            book.setTitle(title);


            LibraryService lib = new LibraryService();
            lib.addBook(book);
	%>
            <h3>BOOK ADDED SUCCESSFULLY</h3>
	<%
       
        }catch (Exception e) {
            e.printStackTrace();
	%>
            <jsp:forward page="errorPage.html" />
	<%
    	}
	%>

</body>
</html>

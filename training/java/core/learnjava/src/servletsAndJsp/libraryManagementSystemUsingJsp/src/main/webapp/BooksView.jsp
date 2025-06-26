<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.library.domain.Book"%>

<%
// Get the books attribute from the request
List<Book> books = (List<Book>) request.getAttribute("books");
%>
<%
if (books != null && !books.isEmpty()) {
%>
<table border="1" style="margin: auto">
	<tr>
		<th>Book Id</th>
		<th>Title</th>
		<th>Author</th>
		<th>Category</th>
		<th>Status</th>
		<th>Availability</th>
	</tr>

	<%
	for (Book book : books) {
	%>
	<tr>
		<td><%=book.getBookId()%></td>
		<td><%=book.getTitle()%></td>
		<td><%=book.getAuthor()%></td>
		<td><%=book.getCategory()%></td>
		<td><%=book.getStatus()%></td>
		<td><%=book.getAvailable()%></td>
	</tr>
	<%
	}
	%>

</table>
<%
} else {
%>
<h3 style="text-align: center">Books not Found</h3>
<%
}
%>

<div style="text-align: center">
	<h4>Return to main menu</h4>
	<button onclick="window.location.href='MainMenu.jsp'"
		style="color: white; margin: auto; background-color: green;">
		Click Here</button>
</div>

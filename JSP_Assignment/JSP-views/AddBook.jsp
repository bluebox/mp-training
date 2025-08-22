<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book</title>
</head>
<body style="background: lavender;">
	<h1 ALIGN="CENTER">ADD BOOK</h1>
	

	
	<div align="center">
		<form action=" ${pageContext.request.contextPath}/addBookServlet" method="post" autocomplete="off">
			<pre>






Enter Book Title: <input type="text" name="title" required/>

Enter Author Name: <input type="text" name="author" required/>

Enter Category: <input type="text" name="category" required/>

<input style="background: skyblue; border: none; border-radius: 4px;"
					type="submit" value="submit" />
</pre>

		</form>
	</div>
</body>
</html>
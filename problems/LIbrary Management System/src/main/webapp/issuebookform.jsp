<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>view all issues
</title>

</head>

<body style="display:flex;flex-direction:column;align-items:center;">
<h2>Issue Book Form</h2>
<form action ="issuebook" method ="post">


Book id: <input type ="text" name ="Bookid"/><br/><br/>
Member id:<input type ="text" name ="memberid"/><br/><br/>
<input type ="sumbit" value ="create issue"><br/><br/>


</form>
<% if (request.getAttribute("message") != null ) { %>
<p style="color:green;"><%= request.getAttribute("message") %></p>
<%} %>

<% if (request.getAttribute("error") != null ) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<%} %>



	

</body>
</html>

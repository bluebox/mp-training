<%@ page import="java.sql.*,java.util.*" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Issue Book</title>
</head>
<body>
<%  List<String> membersList = (List<String>) request.getAttribute("membersList");
	List<String> booksList = (List<String>) request.getAttribute("booksList");
	String email=request.getAttribute("email").toString();
%>

<form action="issueBook" method="post">
<pre>
<label for="members">Select Members: </label><select id="members" name="members">
	<option value="" disabled selected>Select a Member</option>
    <c:forEach var="member" items="${membersList}">
        <option value="${member}" <c:if test="${member == selectedMember}">selected</c:if>>${member}</option>
    </c:forEach>
</select>

<label for="email">Or Email of Member: </label><input id="email" name="email" type="text" value="${email}"/>	<input type="submit" name="action" value="Search"/>

<label for="books">Select Book: </label><select id="books" name="books" >
	<option value="" disabled selected>Select a Book</option>
    <c:forEach var="book" items="${booksList}">
        <option value="${book}">${book}</option>
    </c:forEach>
</select>

<input type="submit" name="action" value="Issue"/>       <input type="submit"  name="action" value="Cancel"/>
</pre>
</form>

</body>
</html>
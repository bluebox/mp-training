<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books</title>
<link rel="stylesheet" href="viewbooks.css"/>
</head>
<body>
	<h1>Hi hello welcome to books section</h1>
	<table>
	<thead>
		<tr>
		<th>
		Bookid
		</th>
		<th>
		Title
		</th>
		<th>
		Author
		</th>
		<th>
		Category
		</th>
		<th>
		Status
		</th>
		<th>Availability</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
	
 <c:forEach items="${list}" var="item">                
                <tr>   
                    <td>${item.getBookid()}</td>
                    <td>${item.getTitle()}</td>
                <td>${item.getAuthor()}</td>
                <td>${item.getCategory()}</td>
                <td>${item.getStatus()}</td>
                <td>${item.getAvailability()}</td>
                </tr>
    </c:forEach>
            
	
	</table>
	
	<div>
	
	<a href="/LMS/addbook.jsp">
		<button>Add Book</button>
	</a>
	<a href="/LMS/updatebook.jsp">
		<button>update Book</button>
	</a>
	
	</div>

</body>
</html>
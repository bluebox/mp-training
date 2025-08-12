<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webLibraryManagementSystem/css/table.css">
</head>
<body>
	<div class="wrapper">
	
          <h1 style="text-align: center">Category Wise Books Count </h1>
           <c:if test="${not empty message}">
      	<p id="responseMessage" style="color: ${formReset ? 'green' : 'red'}; font-weight: bold; text-align: center;">
          	${message}
      	</p>
  	</c:if>
          <table>
              <thead>
                  <tr>
                      <th>Category</th>
                      <th>Count</th>
                  </tr>
              </thead>
               
              <tbody>
             <c:forEach var="book" items="${books}">
      			 <tr>
                      <td data-cell="Category">${book.key}</td>
                      <td data-cell="Status">${book.value}</td>
                  </tr>
  			</c:forEach>
                   
              </tbody>
          </table>
      </div>
       
</body>
</html>
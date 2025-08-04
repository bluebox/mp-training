<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/table.css">
</head>
<body>
	<div class="wrapper">
	
          <h1 style="text-align: center">Active Issued Books </h1>
           <c:if test="${not empty message}">
      	<p id="responseMessage" style="color: ${formReset ? 'green' : 'red'}; font-weight: bold; text-align: center;">
          	${message}
      	</p>
  	</c:if>
          <table>
              <thead>
                  <tr>
                      <th>Member ID</th>
                      <th>Member Name</th>
                      <th>Book ID</th>
                      <th>Book Title</th>
                      <th>Issue Date</th>
                  </tr>
              </thead>
               
              <tbody>
             <c:forEach var="book" items="${activeIssueBooks}">
      			 <tr>
                      <td data-cell="Member Id">${book.getMemberId()}</td>
                      <td data-cell="Member Name">${book.getMemberName() }</td>
                       
                      <td data-cell="Book Id">${book.getBookId()} </td>
                      <td data-cell="Book Title" >${book.getBookTitle()} </td>
                      <td data-cell="Issue Date" >   ${book.getIssueDate()}) </td>
                  </tr>
  			</c:forEach>
                   
              </tbody>
          </table>
      </div>
       
</body>
</html>
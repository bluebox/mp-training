<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Books</title>
<link rel="stylesheet" href="/webLibraryManagementSystem/css/table.css">

    <script type="text/javascript">
        function confirmDelete(bookId) {
            var result = window.confirm("Are you sure you want to delete this item?");
            if (result) {
                 window.location.href="/webLibraryManagementSystem/books/deleteBook?bookId="+bookId;
            } else {
                 window.alert("Book delete Failed!");
            }
        }
    </script>
    
</head>
<body>
        
  	 <div class="wrapper">
          <h1 style="text-align: center">View All Books</h1>
           <c:if test="${not empty message}">
      	<p id="responseMessage" style="color: ${formReset ? 'green' : 'red'}; font-weight: bold; text-align: center;">
          	${message}
      	</p>
  	</c:if>
          <table>
              <caption>Books data</caption>
              <thead>
                  <tr>
                      <th>ID</th>
                      <th>Title</th>
                      <th>Author</th>
                      <th>Category</th>
                      <th>Status</th>
                      <th>Availability</th>
                      <th>Actions</th>
                  </tr>
              </thead>
               
              <tbody>
              <c:forEach var="book" items="${books}">
      			 <tr>
                      <td data-cell="ID">${book.getBookId()}</td>
                      <td data-cell="Title">${book.getTitle()}</td>
                      <td data-cell="Author">${book.getAuthor()}</td>
                      <td data-cell="Category">${book.getCategory()}</td>
                      <td data-cell="Status">${book.getStatus()}</td>
                      <td data-cell="Availability">${book.getAvailability()}</td>
                      <td data-cell="Actions"> 
                       
                      <button id="delete" name="delete" onclick="confirmDelete(${book.getBookId()})">Delete</button>
                      </td>
                  </tr>
  			</c:forEach>
                   
              </tbody>
          </table>
      </div>
       
</body>
</html>
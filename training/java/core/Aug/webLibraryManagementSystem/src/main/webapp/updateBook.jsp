<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webLibraryManagementSystem/css/addBook.css">
</head>
<body>
	<div class="form-container">
    
        <h1>Update Book</h1>
         <c:if test="${not empty message}">
        	<p id="responseMessage" style="color: ${formReset ? 'green' : 'red'}; font-weight: bold; text-align: center;">
            	${message}
        	</p>
    	</c:if>
    	
    	 <form action="/webLibraryManagementSystem/books/updateBook" method="post" >
    	 
    	 	 <label for="book">Book </label>
    	 	 <input list="bookList" id="book" name="book" 
       placeholder="Search by title or author" value="${book}" ${book != null ? 'disabled' : ''}
       onchange="location.href='/webLibraryManagementSystem/books/updateBook?book=' + this.value" required />

		<datalist id="bookList">
		    <c:forEach var="bookObj" items="${books}">
		      <option value="${bookObj}"></option>
		  </c:forEach>
		</datalist>
            
            <c:if test= "${not empty book}">
	            <label for="bookId">Book Id </label>
	        	<input type="text" value="${bookId}" disabled  /> 
	   			<input id="bookId" type="hidden"  name = "bookId" value="${bookId }" />
	    		
	    		<label for="bookTitle">Book Title </label>
	        	<input type="text" id="bookTitle" name="bookTitle" value="${bookTitle}" ${book == null ? 'disabled' : ''} required  maxlength="50"
           oninput="allowOnlyTitle(this)"  />
	   <small id="titleHint" class="hint">Allowed: letters, numbers, spaces, : - . ' & / , ? ! +</small>
	            
	    	 	 <label for="author">Author</label>
	        	 <input type="text" id="author" name="author" value="${author== null ? '' : author}"  ${book == null ? 'disabled' : ''} required maxlength="50"
           oninput="allowOnlyAuthor(this)" />
	    		<small id="authorHint" class="hint">Allowed: letters, spaces, . ' -</small>
	    		
	    		<label for="category">Category</label>
	    		 <select id="category" name="category" ${book == null ? 'disabled' : ''} required >
	    		 <option value="" ${book == null ? 'selected' : ''} >Select Category</option>
		    		<c:forEach var="cat" items="${categories}">
		        			<option value="${cat}" ${(cat == category) ? 'selected' : ''}>${cat}</option>
		    		</c:forEach>
	    		</select>
	 
	    		 
            </c:if>
  
   			<button type="submit">Update</button>
             
    	 </form>
    	 
    	 <script>
    function allowOnlyTitle(input) {
        const pattern = /[^a-zA-Z0-9 :\-.'&/,?!+]/g;
        if (pattern.test(input.value)) {
            document.getElementById("titleHint").style.color = "red";
        } else {
            document.getElementById("titleHint").style.color = "gray";
        }
        input.value = input.value.replace(pattern, '');
    }

    function allowOnlyAuthor(input) {
        const pattern = /[^a-zA-Z .'\-]/g;
        if (pattern.test(input.value)) {
            document.getElementById("authorHint").style.color = "red";
        } else {
            document.getElementById("authorHint").style.color = "gray";
        }
        input.value = input.value.replace(pattern, '');
    }
</script>
   
    </div>
</body>
</html>
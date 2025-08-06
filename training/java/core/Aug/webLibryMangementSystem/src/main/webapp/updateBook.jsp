<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/addBook.css">
</head>
<body>
	<div class="form-container">
    
        <h1>Update Book</h1>
         <c:if test="${not empty message}">
        	<p id="responseMessage" style="color: ${formReset ? 'green' : 'red'}; font-weight: bold; text-align: center;">
            	${message}
        	</p>
    	</c:if>
    	
    	 <form action="updateBook" method="post" >
    	 
    	 	 <label for="book">Book </label>
    	 	 <select id="book" name="book" required onchange="location.href='updateBook?book=' + this.value" >
                <option value="" disabled selected>Select Book</option>
                <c:forEach var="bookTitles" items="${books}">
					<option value="${bookTitles}"
						${bookTitles == book ? 'selected' : ''}>${bookTitles}</option>
				</c:forEach>
				</select>
            
            <c:if test= "${not empty book}">
	            <label for="bookId">Book Id </label>
	        	<input type="text" value="${bookId}" disabled  /> 
	   			<input id="bookId" type="hidden"  name = "bookId" value="${bookId }" />
	    		
	    		<label for="bookTitle">Book Title </label>
	        	<input type="text" id="bookTitle" name="bookTitle" value="${bookTitle}" ${book == null ? 'disabled' : ''}required  />
	  
	            
	    	 	 <label for="author">Author</label>
	        	 <input type="text" id="author" name="author" value="${author== null ? '' : author}"  ${book == null ? 'disabled' : ''} required  />
	    		
	    		<label for="category">Category</label>
	    		 <select id="category" name="category" ${book == null ? 'disabled' : ''} required >
	    		 <option value="" ${book == null ? 'selected' : ''} >Select Category</option>
		    		<c:forEach var="cat" items="${categories}">
		        			<option value="${cat}" ${(cat == category) ? 'selected' : ''}>${cat}</option>
		    		</c:forEach>
	    		</select>
	 
	    		<label for="status">Status</label>
	    		<select id="status" name="status" ${book == null ? 'disabled' : ''} required >
	    		 <option value="" ${book == null ? 'selected' : ''} >Select Status</option>
		    		<c:forEach var="stat" items="${statuses}">
		        			<option value="${stat}" ${(stat == status) ? 'selected' : ''}>${stat}</option>
		    		</c:forEach>
	    		</select>
	    	 	  
	    		
	    		<label for="availability">Availability</label>
	    	 	 <select id="availability"  disabled >
	    		 <option value="${availability == null ? '' : availability}">${availability == null ? '' : availability} </option>
	    		</select>
	    		<input name="availability" type="hidden" value = "${availability}" />
            </c:if>
  
   			<button type="submit">Update</button>
             
    	 </form>
   
    </div>
</body>
</html>
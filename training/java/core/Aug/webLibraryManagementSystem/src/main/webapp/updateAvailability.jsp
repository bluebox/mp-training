<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webLibraryManagementSystem/css/addBook.css">
<title>Insert title here</title>
</head>
<body>
	<div class="form-container">
    
        <h1>Update Availability</h1>
         <c:if test="${not empty message}">
        	<p id="responseMessage" style="color: ${formReset ? 'green' : 'red'}; font-weight: bold; text-align: center;">
            	${message}
        	</p>
    	</c:if>
    	<form action="/webLibraryManagementSystem/books/updateAvailability" method="post" >
    		
    		<label for="book">Book </label>
    	 	 <select id="book" name="book" required onchange="location.href='/webLibraryManagementSystem/books/updateAvailability?book=' + this.value" >
                <option value="">Select Book</option>
                <c:forEach var="bookTitles" items="${books}">
        			<option value="${bookTitles}" ${bookTitles == book ? 'selected' : ''} >${bookTitles}</option>
    			</c:forEach>
            </select>
            <c:if test= "${not empty book}">
            	<label for="availability">Availability</label>
	    	 	 <select id="availability" name="availability"  required >
		    		<c:forEach var="avail" items="${availabilities}">
		        			<option value="${avail}" ${(avail == availability) ? 'selected' : ''}>${avail}</option>
		    		</c:forEach>
	    		</select>
	    	</c:if>
	    	
	    	<button type="submit">Update</button>
    	</form>
    	
    </div>
</body>
</html>
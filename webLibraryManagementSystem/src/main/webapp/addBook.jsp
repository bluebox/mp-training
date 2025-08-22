<%@ taglib uri="jakarta.tags.core" prefix="c" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
    <link rel="stylesheet" href="css/addBook.css">
</head>
<body>
    <div class="form-container">
    
        <h1>Add Book</h1>
         <c:if test="${not empty message}">
        	<p id="responseMessage" style="color: ${formReset ? 'green' : 'red'}; font-weight: bold; text-align: center;">
            	${message}
        	</p>
    	</c:if>
        <form action="addBook" method="post" >
            <label for="title">Title</label>
            <input type="text" id="title" name="title" required value="${title}" />

            <label for="author">Author</label>
            <input type="text" id="author" name="author" required value = "${author }"/>

            <label for="category">Category</label>
            <select id="category" name="category" required>
                <option value="">Select Category</option>
                <c:forEach var="cat" items="${categories}">
        			<option value="${cat}" ${cat == category ? 'selected' : ''}>${cat}</option>
    			</c:forEach>
            </select>

            <label for="status">Status</label>
            <select id="status" name="status" required>
                <option value="">Select Status</option>
                <c:forEach var="stat" items="${statuses}">
        			<option value="${stat}" ${stat == status ? 'selected' : ''}>${stat}</option>
    			</c:forEach>
            </select>

            <label for="availability">Availability</label>
            <select id="availability" name="availability" required>
                <option value="">Select Availability</option>
                <c:forEach var="avail" items="${availabilities}">
        			<option value="${avail}" ${avail == availability ? 'selected' : ''}>${avail}</option>
    			</c:forEach>
            </select>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>

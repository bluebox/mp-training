<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Return Book</title>
    <link rel="stylesheet" type="text/css" href="css/addBook.css">
     
</head>
<body>

<div class="form-container">

    <h1>Return Book</h1>

    <label class="error-label">
        <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
    </label>

	<label class="success-label">
        <%= request.getAttribute("successMessage") != null ? request.getAttribute("successMessage") : "" %>
    </label>

    <form action="ReturnBookServlet" method="post">

       <label class="input-label">Select Member</label>
        <select name="memberId" required>
            <option value="" disabled selected>Select Member</option>
            <c:forEach var="member" items="${membersList}">
            <option value="${member.getMemberId()}" ${member.getMemberId() == memberSelected ? 'selected' : ''}>  ${member.getName()}</option>
            </c:forEach>  
        </select>

        <label class="input-label">Select Book</label>
        <select name="bookId"  required>
            <option value="" disabled selected>Select Book</option>
            <c:forEach var="book" items="${booksList}" >
                <option value="${book.getBookId()}" ${book.getBookId() == bookSelected ? 'selected' : ''}>${book.getTitle()}</option>
             </c:forEach>
        </select>

        <label class="form-label">Return Date</label>
        <input type="date" name="returnDate"  required
               value="${dateSelected}"/>

        <button type="submit" class="submit-button">Return</button>
    </form>

</div>

</body>
</html>

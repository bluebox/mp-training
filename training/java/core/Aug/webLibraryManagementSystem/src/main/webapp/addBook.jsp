<%@ taglib uri="jakarta.tags.core" prefix="c" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
    <link rel="stylesheet" href="/webLibraryManagementSystem/css/addBook.css">
</head>
<body>
    <div class="form-container">
    
        <h1>Add Book</h1>
         <c:if test="${not empty message}">
        	<p id="responseMessage" style="color: ${formReset ? 'green' : 'red'}; font-weight: bold; text-align: center;">
            	${message}
        	</p>
    	</c:if>
        <form action="/webLibraryManagementSystem/books/addBook" method="post">
    <label for="title">Title</label>
    <input type="text" id="title" name="title"
           required value="${title}"
           maxlength="50"
           oninput="allowOnlyTitle(this)" />
    <small id="titleHint" class="hint">Allowed: letters, numbers, spaces, : - . ' & / , ? ! +</small>

    <label for="author">Author</label>
    <input type="text" id="author" name="author"
           required value="${author}"
           maxlength="50"
           oninput="allowOnlyAuthor(this)" />
    <small id="authorHint" class="hint">Allowed: letters, spaces, . ' -</small>

    <label for="category">Category</label>
    <select id="category" name="category" required>
        <option value="">Select Category</option>
        <c:forEach var="cat" items="${categories}">
            <option value="${cat}" ${cat == category ? 'selected' : ''}>${cat}</option>
        </c:forEach>
    </select>

    <button type="submit">Submit</button>
</form>

<style>
    .hint {
        display: block;
        font-size: 12px;
        color: gray;
        margin-bottom: 10px;
    }
</style>

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

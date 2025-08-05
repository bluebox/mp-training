<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Book Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addbook.css" />
    <style>
        label {
            min-width: 100px;
            display: inline-block;
            font-weight: bold;
        }
        .form-row {
            margin-bottom: 15px;
        }
        .book-buttons {
            margin-top: 10px;
            padding: 8px 16px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div style="width: 600px; margin: 30px auto;">
        <h2 style="font-size: 18px; font-weight: bold;">Update Book Details</h2>

        <form action="updateBook" method="post">
            <div class="form-row">
                <label for="titleField">Title:</label>
                <input type="text" id="titleField" name="title" 
                    value="${book.title}" style="width: 200px;" required />
            </div>

            <div class="form-row">
                <label for="authorField">Author:</label>
                <input type="text" id="authorField" name="author" 
                    value="${book.author}" style="width: 200px;" required />
            </div>

            <div class="form-row">
                <label for="categoryField">Category:</label>
                <input type="text" id="categoryField" name="category" 
                    value="${book.category}" style="width: 200px;" />
            </div>

            <div class="form-row">
                <label for="statusComboBox">Status:</label>
                <select id="statusComboBox" name="status" style="width: 208px;">
                    <c:forEach var="statusOption" items="${statusOptions}">
                        <option value="${statusOption}" 
                            <c:if test="${book.status == statusOption}">selected</c:if>>
                            ${statusOption}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="book-buttons">Save Changes</button>
        </form>

        <form action="viewBooks.jsp" method="get" style="margin-top: 10px;">
            <button type="submit" class="book-buttons" style="color: red;">Back</button>
        </form>
    </div>
</body>
</html>

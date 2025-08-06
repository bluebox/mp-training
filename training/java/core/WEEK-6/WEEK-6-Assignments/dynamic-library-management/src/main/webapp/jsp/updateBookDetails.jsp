<%@page import="com.dynamic_library_management.domain.Book"%>
<%@page import="com.dynamic_library_management.dao.implementation.BookDaoImplementation"%>
<%@page import="com.dynamic_library_management.domain.BookCategory"%>
<%@page import="com.dynamic_library_management.domain.BookStatus"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    request.setAttribute("categoryList", List.of(BookCategory.values()));
    request.setAttribute("statusList", List.of(BookStatus.values()));
    request.setAttribute("bookList", new BookDaoImplementation().selectAllBooks());
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Book</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: 
                linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
                url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1470&q=80');
            background-size: cover;
            background-position: center;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
        }

        .back-btn {
            position: absolute;
            top: 15px;
            left: 15px;
            background-color: rgba(255, 255, 255, 0.8);
            color: #0d47a1;
            padding: 6px 12px;
            font-size: 14px;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            text-decoration: none;
            transition: background-color 0.3s ease;
            z-index: 10;
        }

        .back-btn:hover {
            background-color: #bbdefb;
        }

        .container {
            width: 360px;
            background: rgba(255, 255, 255, 0.95);
            padding: 25px 35px;
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        }

        h1 {
            text-align: center;
            font-size: 22px;
            color: #0d47a1;
            margin-bottom: 20px;
            border-bottom: 2px solid #90caf9;
            display: inline-block;
            padding-bottom: 8px;
        }

        label {
            display: block;
            margin-top: 12px;
            font-size: 14px;
            color: #0d47a1;
            font-weight: bold;
        }

        input[type="text"], select {
            width: 100%;
            padding: 8px;
            margin-top: 4px;
            font-size: 13px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .submit-btn {
            display: block;
            width: 100%;
            margin-top: 20px;
            padding: 10px;
            background-color: #64b5f6;
            color: white;
            font-size: 14px;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #42a5f5;
        }

        .popup {
            position: absolute;
            top: 10%;
            left: 50%;
            transform: translateX(-50%);
            background-color: #f0f0f0;
            padding: 10px 20px;
            border-radius: 8px;
            font-size: 14px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            z-index: 999;
            display: none;
        }

        .popup.success {
            background-color: #c8e6c9;
            color: #2e7d32;
        }

        .popup.error {
            background-color: #ffcdd2;
            color: #c62828;
        }
    </style>
</head>
<body>

<a href="${pageContext.request.contextPath}/jsp/bookOptions.jsp" class="back-btn">← Back</a>

<!-- Message Popup -->
<c:if test="${not empty message}">
    <div id="popupMessage" class="popup ${success ? 'success' : 'error'}">
        ${message}
    </div>
</c:if>

<div class="container">
    <h1>🔄 Update Book</h1>

    <form action="${pageContext.request.contextPath}/updateBookDetailsController" method="post">
        <label>Select Book:</label>
        <select id="bookSelector" name="bookId" onchange="loadBookDetails()" required>
            <option disabled selected>Select Book to Update</option>
            <c:forEach items="${bookList}" var="book">
                <option value="${book.getBookId()}">${book.getBookId()}. ${book.getTitle()}</option>
            </c:forEach>
        </select>

        <label>Title:</label>
        <input type="text" name="title" id="titleField" required />

        <label>Author:</label>
        <input type="text" name="author" id="authorField" required />

        <label>Category:</label>
        <select name="category" id="categoryField" required>
            <option value="">Select Category</option>
            <c:forEach items="${categoryList}" var="category">
                <option value="${category.getStringValue()}">${category.toString()}</option>
            </c:forEach>
        </select>

        <label>Status:</label>
        <select name="status" id="statusField" required>
            <option value="">Select Status</option>
            <c:forEach items="${statusList}" var="status">
                <option value="${status.getStringValue()}">${status.toString()}</option>
            </c:forEach>
        </select>

        <input type="submit" class="submit-btn" value="✏️ Update Book" />
    </form>
</div>

<script>
    const bookMap = new Map();

    <c:forEach items="${bookList}" var="book">
        bookMap.set(${book.getBookId()}, {
            "title": "${book.getTitle()}",
            "author": "${book.getAuthor()}",
            "category": "${book.getCategory().getStringValue()}",
            "status": "${book.getStatus().getStringValue()}"
        });
    </c:forEach>

    function loadBookDetails() {
        const selectedId = Number(document.getElementById("bookSelector").value.split(".")[0]);
        const currBook = bookMap.get(selectedId);
        if (currBook) {
            document.getElementById("titleField").value = currBook["title"];
            document.getElementById("authorField").value = currBook["author"];
            document.getElementById("categoryField").value = currBook["category"];
            document.getElementById("statusField").value = currBook["status"];
        }
    }

    const popup = document.getElementById('popupMessage');
    if (popup) {
        popup.style.display = 'block';
        setTimeout(() => {
            popup.style.display = 'none';
        }, 3000);
    }
</script>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Return Book</title>
    <link rel="stylesheet" href="/webLibraryManagementSystem/css/addBook.css">
    <script>
        function setHiddenId(inputId, listId, hiddenId) {
            const input = document.getElementById(inputId);
            const list = document.getElementById(listId);
            const hidden = document.getElementById(hiddenId);

            const selectedOption = Array.from(list.options)
                .find(option => option.value === input.value);

            hidden.value = selectedOption ? selectedOption.dataset.id : "";
        }
        
        function allowOnlyTitle(input) {
            const pattern = /[^a-zA-Z0-9 :\-.'&/,?!+]/g;
            
            input.value = input.value.replace(pattern, '');
        }

        function allowOnlyMember(input) {
            const pattern = /[^a-zA-Z ()0-9]/g;
           
            input.value = input.value.replace(pattern, '');
        }
    </script>
</head>
<body>

<div class="form-container">
    <h1>Return Book</h1>

    <label class="error-label">
        <c:out value="${errorMessage != null ? errorMessage : ''}" />
    </label>
    <label class="success-label">
        <c:out value="${successMessage != null ? successMessage : ''}" />
    </label>

    <form action="/webLibraryManagementSystem/issue/returnBook" method="post">

<label class="input-label">Select Member</label>
<input list="members" id="memberInput" name="memberNameTyped"
       placeholder="Search member by name or mobile"
       onchange="setHiddenId('memberInput','members','memberHidden')" autocomplete="off"
       value="${memberNameTyped != null ? memberNameTyped : ''}"
       required oninput="allowOnlyMember(this)"/>
<datalist id="members">
    <c:forEach var="member" items="${membersList}">
        <option value="${member.name} (${member.mobile})" data-id="${member.memberId}"></option>
    </c:forEach>
</datalist>
<input type="hidden" id="memberHidden" name="memberId"
       value="${memberSelected != null ? memberSelected : ''}" />

<label class="input-label">Select Book</label>
<input list="books" id="bookInput" name="bookTyped"
       placeholder="Search by title or author"
       onchange="setHiddenId('bookInput','books','bookHidden')" autocomplete="off" oninput="allowOnlyTitle(this)"
       value="${bookTyped != null ? bookTyped : ''}"
       required />
<datalist id="books">
    <c:forEach var="book" items="${booksList}">
        <option value="${book.title} - ${book.author}" data-id="${book.bookId}"></option>
    </c:forEach>
</datalist>
<input type="hidden" id="bookHidden" name="bookId"
       value="${bookSelected != null ? bookSelected : ''}" />

<label class="form-label">Return Date</label>
<input type="date" name="returnDate" value="${dateSelected != null ? dateSelected : ''}" required>


        <button type="submit" class="submit-button">Return</button>
    </form>
</div>

</body>
</html>

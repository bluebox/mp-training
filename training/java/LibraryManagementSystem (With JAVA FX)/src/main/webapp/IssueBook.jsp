<%@ page import="java.sql.*,java.util.*" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue Book</title>
    <style>
        .issue-book-container {
            max-width: 600px;
            margin: 20px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .issue-book-title {
            color: #34495e;
            text-align: center;
            margin-bottom: 30px;
            font-size: 2em;
            font-weight: 600;
        }

        .issue-book-form-group {
            margin-bottom: 20px;
        }

        .issue-book-label {
            display: block;
            margin-bottom: 8px;
            color: #34495e;
            font-weight: 500;
            font-size: 0.95em;
        }

        .issue-book-input {
            width: 100%;
            padding: 12px;
            border: 2px solid #7f8c8d;
            border-radius: 6px;
            font-size: 1em;
            color: #34495e;
            box-sizing: border-box;
        }

        .issue-book-input:focus {
            border-color: #34495e;
            outline: none;
            box-shadow: 0 0 5px rgba(52, 73, 94, 0.2);
        }

        .issue-book-select {
            width: 100%;
            padding: 12px;
            border: 2px solid #7f8c8d;
            border-radius: 6px;
            font-size: 1em;
            color: #34495e;
            background-color: white;
            cursor: pointer;
            box-sizing: border-box;
        }

        .issue-book-select:focus {
            border-color: #34495e;
            outline: none;
        }

        .issue-book-button {
            width: 100%;
            padding: 14px;
            background-color: #34495e;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 1em;
            font-weight: 500;
            cursor: pointer;
            margin-top: 10px;
        }

        .issue-book-button:hover {
            background-color: #7f8c8d;
        }

        .issue-book-button.secondary {
            background-color: #95a5a6;
        }

        .search-group {
            display: flex;
            gap: 10px;
            align-items: flex-end;
        }

        .search-group .input-container {
            flex: 1;
        }

        .search-group .issue-book-button {
            margin-top: 0;
            width: auto;
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <div class="issue-book-container">
        <h1 class="issue-book-title">Issue Book</h1>
        <%  
            List<String> membersList = (List<String>) request.getAttribute("membersList");
            List<String> booksList = (List<String>) request.getAttribute("booksList");
            String email = request.getAttribute("email").toString();
        %>

        <form id="issue-book-Form" action="IssueBookServlet" method="post">
            <div class="issue-book-form-group">
                <label class="issue-book-label" for="members">Select Member</label>
                <select class="issue-book-select" id="members" name="members">
                    <option value="" disabled selected>Choose a member</option>
                    <c:forEach var="member" items="${membersList}">
                        <option value="${member}" <c:if test="${member == selectedMember}">selected</c:if>>${member}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="issue-book-form-group">
                <div class="search-group">
                    <div class="input-container">
                        <label class="issue-book-label" for="email">Or Enter Email</label>
                        <input class="issue-book-input" id="email" name="email" type="text" value="${email}"/>
                    </div>
                    <input class="issue-book-button" onclick="onSearch(event)" type="submit" name="action" value="Search"/>
                </div>
            </div>

            <div class="issue-book-form-group">
                <label class="issue-book-label" for="books">Select Book</label>
                <select class="issue-book-select" id="books" name="books">
                    <option value="" disabled selected>Choose a book</option>
                    <c:forEach var="book" items="${booksList}">
                        <option value="${book}">${book}</option>
                    </c:forEach>
                </select>
            </div>

            <input class="issue-book-button" onclick="onIssue(event)" type="submit" name="action" value="Issue"/>
            <input class="issue-book-button secondary" onclick="cancelForm(event)" type="submit" name="action" value="Cancel"/>
        </form>
        <script>
		function cancelForm(event) {
		  const form = document.getElementById('issue-book-Form');
		  form.querySelectorAll('[required]').forEach(el => el.removeAttribute('required'));
		  window.location.href = "IssueBookServlet";
		}
		function onSearch(event){
			const form = document.getElementById('issue-book-Form');
			form.querySelectorAll('[required]').forEach(el => el.removeAttribute('required'));
			document.getElementById('email').setAttribute('required', 'required');
		}
		function onIssue(event){
			const form = document.getElementById('issue-book-Form');
			form.querySelectorAll('[required]').forEach(el => el.removeAttribute('required'));
			document.getElementById('members').setAttribute('required', 'required');
			document.getElementById('books').setAttribute('required', 'required');
		}
	</script>
    </div>
</body>
</html>

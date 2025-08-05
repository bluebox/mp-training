<%@ page language="java" import="java.sql.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Return Book</title>
    <style>
        .return-book-container {
            max-width: 600px;
            margin: 20px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .return-book-title {
            color: #34495e;
            text-align: center;
            margin-bottom: 30px;
            font-size: 2em;
            font-weight: 600;
        }

        .return-book-form-group {
            margin-bottom: 20px;
        }

        .return-book-label {
            display: block;
            margin-bottom: 8px;
            color: #34495e;
            font-weight: 500;
            font-size: 0.95em;
        }

        .return-book-input {
            width: 100%;
            padding: 12px;
            border: 2px solid #7f8c8d;
            border-radius: 6px;
            font-size: 1em;
            color: #34495e;
            box-sizing: border-box;
        }

        .return-book-input:focus {
            border-color: #34495e;
            outline: none;
            box-shadow: 0 0 5px rgba(52, 73, 94, 0.2);
        }

        .return-book-select {
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

        .return-book-select:focus {
            border-color: #34495e;
            outline: none;
        }

        .return-book-submit {
            width: 100%;
            padding: 14px;
            background-color: #34495e;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 1em;
            font-weight: 500;
            cursor: pointer;
            margin-top: 20px;
        }

        .return-book-submit:hover {
            background-color: #7f8c8d;
        }

        .return-book-button {
            padding: 12px 24px;
            background-color: #34495e;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 1em;
            font-weight: 500;
            cursor: pointer;
        }

        .return-book-button:hover {
            background-color: #7f8c8d;
        }

        .return-book-button.secondary {
            background-color: #95a5a6;
            margin-top: 10px;
            width: 100%;
        }

        .search-group {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }

        .search-group .input-container {
            flex: 1;
        }

        .search-group .return-book-button {
            align-self: flex-end;
            height: 45px;
        }
    </style>
</head>
<body>
    <div class="return-book-container">
        <h1 class="return-book-title">Return Book</h1>
        <%  
            List<String> membersList = (List<String>) request.getAttribute("membersList");
            List<String> booksList = (List<String>) request.getAttribute("booksList");
            String email=request.getAttribute("email") != null ? request.getAttribute("email").toString() : "";
        %>

        <form id="return-book-Form" action="ReturnBookServlet" method="post">
            <div class="return-book-form-group">
                <label class="return-book-label" for="members">Select Member</label>
                <select class="return-book-select" id="members" name="members">
                    <option value="" disabled selected>Choose a member</option>
                    <c:forEach var="member" items="${membersList}">
                        <option value="${member}" <c:if test="${member == selectedMember}">selected</c:if>>${member}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="search-group">
            	<div class="input-container">
	            	<label class="return-book-label" for="email">Or Enter Email</label>
	                <input class="return-book-input" id="email" name="email" type="text" value="${email}"/>
	            </div>
	           <button type="submit" name="action" onclick="onSearch(event)" value="Search" class="return-book-button">Search</button>
	        </div>
	            

            <div class="return-book-form-group">
                <label class="return-book-label" for="books">Select Book</label>
                <select class="return-book-select" id="books" name="books">
                    <option value="" disabled selected>Choose a book</option>
                    <c:forEach var="book" items="${booksList}">
                        <option value="${book}">${book}</option>
                    </c:forEach>
                </select>
            </div>

            <input type="submit" name="action" onclick="onReturn(event)" value="Return" class="return-book-submit"/>
            <input type="submit" name="action" onclick="cancelForm(event)" value="Cancel" class="return-book-button secondary"/>
        </form>
        <script>
		function cancelForm(event) {
		  const form = document.getElementById('return-book-Form');
		  form.querySelectorAll('[required]').forEach(el => el.removeAttribute('required'));
		  window.location.href = "IssueBookServlet";
		}
		function onSearch(event){
			const form = document.getElementById('return-book-Form');
			form.querySelectorAll('[required]').forEach(el => el.removeAttribute('required'));
			if(!document.getElementById('members').value){
				document.getElementById('email').setAttribute('required', 'required');
			}
		}
		function onReturn(event){
			const form = document.getElementById('return-book-Form');
			form.querySelectorAll('[required]').forEach(el => el.removeAttribute('required'));
			document.getElementById('members').setAttribute('required', 'required');
			document.getElementById('books').setAttribute('required', 'required');
		}
	</script>
    </div>
</body>
</html>

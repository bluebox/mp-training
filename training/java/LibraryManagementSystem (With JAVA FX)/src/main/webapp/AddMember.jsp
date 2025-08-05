<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Member</title>
<style>
    .add-member-container {
        max-width: 600px;
        margin: 20px auto;
        padding: 30px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }
    .add-member-title {
        color: #34495e;
        text-align: center;
        margin-bottom: 30px;
        font-size: 2em;
        font-weight: 600;
    }
    .add-member-form-group {
        margin-bottom: 20px;
    }
    .add-member-label {
        display: block;
        margin-bottom: 8px;
        color: #34495e;
        font-weight: 500;
        font-size: 0.95em;
    }
    .add-member-input, .add-member-select, .add-member-textarea {
        width: 100%;
        padding: 12px;
        border: 2px solid #7f8c8d;
        border-radius: 6px;
        font-size: 1em;
        color: #34495e;
        box-sizing: border-box;
    }
    .add-member-input:focus, .add-member-select:focus, .add-member-textarea:focus {
        border-color: #34495e;
        outline: none;
        box-shadow: 0 0 5px rgba(52, 73, 94, 0.2);
    }
    .add-member-submit {
        width: 48%;
        padding: 14px;
        background-color: #34495e;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 1em;
        font-weight: 500;
        cursor: pointer;
        margin-top: 20px;
        margin-right: 2%;
    }
    .add-member-submit:hover {
        background-color: #7f8c8d;
    }
</style>
	<script type="text/javascript">
    	<% String alertMessage = (String) request.getAttribute("alertMessage"); %>
    	
    	<%if(request.getAttribute("alertMessage") != null){%>
    	 	alert('<%= alertMessage %>');
    	<%}%>
    </script>
</head>
<body>
    <div class="add-member-container">
        <h1 class="add-member-title">Add Member</h1>
        <form id="add-member-Form" action="AddMemberServlet" method="post">
            <div class="add-member-form-group">
                <label class="add-member-label" for="name">Name:</label>
                <input type="text" id="name" name="MemberName" class="add-member-input" placeholder="Enter Member Name" required/>
            </div>
            <div class="add-member-form-group">
                <label class="add-member-label" for="email">Email:</label>
                <input type="email" id="email" name="email" class="add-member-input" placeholder="Enter Email Address(abc@gmail.com)" required/>
            </div>
            <div class="add-member-form-group">
                <label class="add-member-label" for="number">Mobile Number:</label>
                <input type="text" id="number" pattern="\d{10}" name="Mobile Number" class="add-member-input" placeholder="Enter Ph-No (1234345467)" required/>
            </div>
            <div class="add-member-form-group">
                <label class="add-member-label" for="gender">Gender:</label>
                <select id="gender" name="gender" class="add-member-select" required>
                    <option value="" disabled selected>Select Gender</option>
                    <option value="M">M</option>
                    <option value="F">F</option>
                </select>
            </div>
            <div class="add-member-form-group">
                <label class="add-member-label" for="address">Address:</label>
                <textarea rows="3" id="address" name="address" class="add-member-textarea" required></textarea>
            </div>
            <input type="submit" name="action" value="Submit" class="add-member-submit"/>
            <input type="submit" onclick="cancelForm(event)" name="action" value="Cancel" class="add-member-submit" style="background-color:#7f8c8d;"/>
        </form>
    </div>
    <script>
        function cancelForm(event) {
            const form = document.getElementById('add-member-Form');
            form.querySelectorAll('[required]').forEach(el => el.removeAttribute('required'));
            window.location.href = "AddMemberServlet";
        }
    </script>
</body>
</html>

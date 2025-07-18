<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>library management system</title>
<style>
    form {
      display: grid;
      grid-template-columns: 1fr 2fr;
      gap: 12px 16px;
      max-width: 500px;
      margin: 40px auto;
      padding: 24px;
      background: #f8f9fa;
      border-radius: 10px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }
    label {
      font-weight: bold;
      margin-top: 6px;
    }
    input, select {
      padding: 8px;
      border-radius: 4px;
      border: 1px solid #ccc;
    }
    button {
      grid-column: span 2;
      padding: 10px;
      background: #2563eb;
      color: white;
      border: none;
      border-radius: 6px;
      cursor: pointer;
    }
    #messageLabel {
      grid-column: span 2;
      text-align: center;
      font-weight: bold;
      margin-top: 12px;
    }
  </style>
</head>
<body>

	<form id="bookForm" action="<%= request.getContextPath() %>/addbook" method="post">
		<label for="titleField">Title</label> <input type="text"
			id="titleField" name="title" required> <label
			for="authorField">Author</label> <input type="text" id="authorField"
			name="author" required> <label for="categoryField">Category</label>
		<input type="text" id="categoryField" name="category" required>

		<label for="statusChoiceBox">Status</label> <select
			id="statusChoiceBox" name="status" required>
			<option value="" disabled selected>Select status</option>
			<option value="A">Active</option>
			<option value="I">Inactive</option>
		</select> <label for="availabilityChoiceBox">Availability</label> <select
			id="availabilityChoiceBox" name="availability" required>
			<option value="" disabled selected>Select availability</option>
			<option value="A">Available</option>
			<option value="I">Issued</option>
		</select>
		
		<div id="messageLabel" style="color:green;">
  			<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
		</div>

		<button type="submit">Add Book</button>

		<div id="messageLabel"></div>
	</form>
</body>
</html>
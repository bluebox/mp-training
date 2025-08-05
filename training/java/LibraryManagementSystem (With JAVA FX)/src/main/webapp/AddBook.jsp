<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
    <style>
        .add-book-container {
            max-width: 600px;
            margin: 20px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .add-book-title {
            color: #34495e;
            text-align: center;
            margin-bottom: 30px;
            font-size: 2em;
            font-weight: 600;
        }

        .add-book-form-group {
            margin-bottom: 20px;
        }

        .add-book-label {
            display: block;
            margin-bottom: 8px;
            color: #34495e;
            font-weight: 500;
            font-size: 0.95em;
        }

        .add-book-input {
            width: 100%;
            padding: 12px;
            border: 2px solid #7f8c8d;
            border-radius: 6px;
            font-size: 1em;
            color: #34495e;
            box-sizing: border-box;
        }

        .add-book-input:focus {
            border-color: #34495e;
            outline: none;
            box-shadow: 0 0 5px rgba(52, 73, 94, 0.2);
        }

        .add-book-select {
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

        .add-book-select:focus {
            border-color: #34495e;
            outline: none;
        }

        .add-book-submit {
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

        .add-book-submit:hover {
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
    <div class="add-book-container">
        <h1 class="add-book-title">Add Book  </h1>
        <form action='AddBookServlet' method='POST' class="add-book-form">
            <div class="add-book-form-group">
                <label class="add-book-label" for="bookTitle">Enter book title </label>
                <input type='text' name='bookTitle' id="bookTitle" class="add-book-input" required/>
            </div>
            
            <div class="add-book-form-group">
                <label class="add-book-label" for="author">Enter book's Author</label>
                <input type='text' name='author' id="author" class="add-book-input" required/>
            </div>
            
            <div class="add-book-form-group">
                <label class="add-book-label" for="category">Select book category</label>
                <select name="category" id="category" class="add-book-select" required>
                    <option value="" disabled selected>Choose a category</option>
                    <option value="Fiction">Fiction</option>
                    <option value="Non-Fiction">Non-Fiction</option>
                    <option value="Science">Science</option>
                    <option value="Technology">Technology</option>
                    <option value="History">History</option>
                    <option value="Biography">Biography</option>
                </select>
            </div>
            
            <input type='submit' name='action' value='AddBook' class="add-book-submit"/>
        </form>
    </div>
</body>
</html>

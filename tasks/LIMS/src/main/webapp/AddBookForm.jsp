<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="library.model.enums.BookCategory" %>
<%@ page import="library.model.enums.BookStatus" %>
<%@ page import="library.model.enums.BookAvailability" %>
<%@ page import="java.util.stream.Collectors" %>




<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Book</title>
</head>
<body>
    <div style="text-align: center; padding: 20px;">
        <h1 style="font-family: 'System Bold'; font-size: 24px;">Add New Book</h1>

        <form action="addBook" method="post" style="align-items: center; gap: 10px;">
            <div>
                <label for="titleField">Title:</label>
                <input type="text" id="titleField" name="title" placeholder="Enter book title" style="width: 200px;"/> <br><br>

                <label for="authorField">Author:</label>
                <input type="text" id="authorField" name="author" placeholder="Enter author name" style="width: 200px;"/><br><br>

                <label for="categoryComboBox">Category:</label>
                <select id="categoryComboBox" name="category" style="width: 200px;">
                    <option value="">Select category</option>
                    <%
                        List<String> categories =  Arrays.stream(BookCategory.values())
					                                .map(BookCategory::getDisplayName)
					                                .collect(Collectors.toList());
                        if (categories != null) {
                            for (String category : categories) {
                                out.println("<option value="+ category +">" + category + "</option>");
                            }
                        }
                    %>
                </select><br><br>

                <label for="statusComboBox">Status:</label>
                <select id="statusComboBox" name="status" style="width: 200px;">
                    <option value="">Select status</option>
                    <%
                        List<String> statuses = Arrays.stream(BookStatus.values())
				                                .map(Enum::toString)
				                                .collect(Collectors.toList());
/*                         		List<String>) request.getAttribute("bookStatuses");
 */                        
 							if (statuses != null) {
                            for (String status : statuses) {
                                out.println("<option value=\"" + status + "\">" + status + "</option>");
                            }
                        }
                    %>
                </select><br><br>

                <label for="availabilityComboBox">Availability:</label>
                <select id="availabilityComboBox" name="availability" style="width: 200px;">
                    <option value="">Select availability</option>
                    <%
                        List<String> availabilities = Arrays.stream(BookAvailability.values())
						                                .map(Enum::toString) 
						                                .collect(Collectors.toList());
/*                         (List<String>) request.getAttribute("bookAvailabilities");
 */                    
                        if (availabilities != null) {
                            for (String availability : availabilities) {
                                out.println("<option value=\"" + availability + "\">" + availability + "</option>");
                            }
                        }
                    %>
                </select><br><br>
            </div>

            <p id="messageLabel" style="color: <%= (request.getAttribute("messageType") != null && request.getAttribute("messageType").equals("error")) ? "red" : "green" %>;">
                <% 
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                        out.println(message);
                    }
                %>
            </p><br>

            <div style="justify-content: center; gap: 10px; margin-top: 20px;">
                <button type="submit">Add Book</button>
                <button type="button" onclick="location.href='main'">Back to Main Menu</button>
            </div>
        </form>
    </div>
</body>
</html>
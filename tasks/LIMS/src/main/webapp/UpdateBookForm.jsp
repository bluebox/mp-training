<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="library.model.enums.BookCategory" %>
<%@ page import="library.model.enums.BookStatus" %>
<%@ page import="library.model.enums.BookAvailability" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Book Details</title>
</head>
<body>
    <div style="text-align: center; padding: 20px;">
        <h1 style="font-family: 'System Bold'; font-size: 24px;">Update Book Details</h1>

        <form action="updateBook" method="post" style="align-items: center; gap: 10px;">
            <div style="gap: 10px;">
                <label for="bookIdField">Book ID:</label> 
                <input type="text" id="bookIdField" name="bookId" 
                value="<%= request.getAttribute("bookId") != null ? request.getAttribute("bookId") : "" %>" 
                readonly style="width: 200px; "/> <br><br>
                
                <label for="titleField">Title:</label>
                <input type="text" id="titleField" name="title" placeholder="Enter book title" 
                value="<%= request.getAttribute("title") != null ? request.getAttribute("title") : "" %>" style="width: 200px;"/><br><br>
                
                <label for="authorField">Author:</label>
                <input type="text" id="authorField" name="author" placeholder="Enter author name" 
                value="<%= request.getAttribute("author") != null ? request.getAttribute("author") : "" %>" style="width: 200px;"/><br><br>
                
                <label for="categoryComboBox">Category:</label>
                <select id="categoryComboBox" name="category" style="width: 200px;">
                    <option value="">Select category</option>
                    <%
                        List<String> categories = (List<String>) request.getAttribute("bookCategories");
                        String selectedCategory = (String) request.getAttribute("selectedCategory");
                        if (categories != null) {
                            for (String category : categories) {
                                String selectedAttr = (category.equals(selectedCategory)) ? "selected" : "";
                                out.println("<option value=\"" + category + "\" " + selectedAttr + ">" + category + "</option>");
                            }
                        }
                    %>
                </select><br><br>
                
                <label for="statusComboBox">Status:</label>
                <select id="statusComboBox" name="status">
                    <option value="">Select status</option>
                    <%
                        List<String> statuses = (List<String>) request.getAttribute("bookStatuses");
                        String selectedStatus = (String) request.getAttribute("selectedStatus");
                        if (statuses != null) {
                            for (String status : statuses) {
                                String selectedAttr = (status.equals(selectedStatus)) ? "selected" : "";
                                out.println("<option value=\"" + status + "\" " + selectedAttr + ">" + status + "</option>");
                            }
                        }
                    %>
                </select><br><br>

                <label for="availabilityComboBox">Availability:</label>
                <select id="availabilityComboBox" name="availability">
                    <option value="">Select availability</option>
                    <%
                        List<String> availabilities = (List<String>) request.getAttribute("bookAvailabilities");
                        String selectedAvailability = (String) request.getAttribute("selectedAvailability");
                        if (availabilities != null) {
                            for (String availability : availabilities) {
                                String selectedAttr = (availability.equals(selectedAvailability)) ? "selected" : "";
                                out.println("<option value=\"" + availability + "\" " + selectedAttr + ">" + availability + "</option>");
                            }
                        }
                    %>
                </select><br><br>
            </div>

            <p id="messageLabel" class="message-label" style="color: <%= (request.getAttribute("messageType") != null && 
            request.getAttribute("messageType").equals("error")) ? "red" : "green" %>;">
                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                        out.println(message);
                    }
                %>
            </p><br>

            <div class="button-group">
                <button type="submit">Update Book</button>
                <button type="button" onclick="location.href='viewBooks'">Back to View Books</button>
            </div>     <br><br>   </form>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="library.model.Book" %>
<%@ page import="library.model.enums.BookCategory" %>
<%@ page import="library.model.enums.BookStatus" %>
<%@ page import="library.model.enums.BookAvailability" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>

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
            
            <%  Book book = (Book) request.getAttribute("selectedBooktoUpdate"); %>
            
                <label for="bookIdField">Book ID:</label> 
                <input type="text" id="bookIdField" name="bookId" 
                value="<%= String.valueOf(book.getBookId()) != null ? book.getBookId() : "" %>" 
                 readonly style="width: 200px; "/> <br><br>
                
                <label for="titleField">Title:</label>
                <input type="text" id="titleField" name="title" placeholder="Enter book title" 
                value="<%= book.getTitle() != null ? book.getTitle() : "" %>" style="width: 200px;"/><br><br>
                
                <label for="authorField">Author:</label>
                <input type="text" id="authorField" name="author" placeholder="Enter author name" 
                value="<%= book.getAuthor() != null ? book.getAuthor() : "" %>" style="width: 200px;"/><br><br>
                
                <label for="categoryComboBox">Category:</label>
                <select id="categoryComboBox" name="category" style="width: 200px;">
                    <option value="">Select category</option>
                    <%
                    List<String> categories = Arrays.stream(BookCategory.values())
						                    .map(BookCategory::getDisplayName)
						                    .collect(Collectors.toList());
                                        
                    String selectedCategory = book.getCategory().getDisplayName();
                    
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
                    List<String> statuses = Arrays.stream(BookStatus.values())
						                    .map(Enum::name) 
						                    .collect(Collectors.toList());    
                    

                        String selectedStatus = book.getStatus().name();
                        
                        if (statuses != null) {
                            for (String status : statuses) {
                                String selectedAttr = (status.equals(selectedStatus)) ? "selected" : "";
                                out.println("<option value=\"" + status + "\" " + selectedAttr + ">" + status + "</option>");
                            }
                        }
                    %>
                </select><br><br>

                <label for="availabilityComboBox">Availability:</label>
                <select id="availabilityComboBox" name="availability" >
                    <option value="" >Select availability</option>
                    <%
                    List<String> availabilities = Arrays.stream(BookAvailability.values())
								                    .map(Enum::name) 
								                    .collect(Collectors.toList());   
                    
                        String selectedAvailability = book.getAvailability().name();
                        
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
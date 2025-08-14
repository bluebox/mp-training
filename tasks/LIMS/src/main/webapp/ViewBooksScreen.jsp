<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="library.model.Book" %>
<%@ page import="library.model.enums.BookAvailability" %>
<%@ page import="library.model.enums.BookStatus" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Books</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .center-text {
            text-align: center;
        }
        .action-buttons button {
            margin-right: 5px;
            padding: 5px 10px;
        }
        .message-label {
            margin-top: 10px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div style="text-align: center; padding: 20px;">
        <h1 style="font-family: 'System Bold'; font-size: 24px;">All Books</h1>


        <form id="bookActionsForm" action="viewBooks" method="post">
            <table>
                <thead>
                    <tr>
                        <th class="center-text">Select</th>
                        <th>Book ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Category</th>
                        <th>Status</th>
                        <th>Avail.</th>
                        <th>Actions</th>
                        <th>Avail. Action</th>
                        <th>Created At</th>
                        <th>Created By</th>
                        <th>Updated At</th>
                        <th>Updated By</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Book> books = (List<Book>) request.getAttribute("books");
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                        if (books != null && !books.isEmpty()) {
                            for (Book book : books) {
                    %>
                                <tr>
                                    <td class="center-text">
                                        <input type="checkbox" name="selectedBookIds" value="<%= book.getBookId() %>"/>
                                    </td>
                                    <td><%= book.getBookId() %></td>
                                    <td><%= book.getTitle() %></td>
                                    <td><%= book.getAuthor() %></td>
                                    <td><%= book.getCategory().getDisplayName() %></td>
                                    <td><%= book.getStatus().toString() %></td>
                                    <td><%= book.getAvailability().toString() %></td>
                                    
                                   <td class="action-buttons">
                                        <button type="button" onclick="location.href='updateBook?bookId=<%= book.getBookId() %>'"
                                                style="padding: 5px 10px;">Update</button>
                                        <button type="submit" name="action" value="deleteBook:<%= book.getBookId() %>" 
                                                onclick="return confirm('Are you sure you want to delete <%= book.getTitle() %> (ID: <%= book.getBookId() %>)?')"
                                                style="padding: 5px 10px;">Delete</button>
                                    </td>
                                    
                                    <td class="action-buttons">
                                        <% 
                                            BookAvailability newAvailability = (book.getAvailability() == BookAvailability.AVAILABLE) ? BookAvailability.ISSUED : BookAvailability.AVAILABLE;
                                            String buttonText = (book.getAvailability() == BookAvailability.AVAILABLE) ? "Mark Issued" : "Mark Available";
                                            String disableAttr = (book.getStatus() == BookStatus.INACTIVE) ? "disabled" : "";
                                        %>
                                        <button type="submit" name="action" value="changeAvailability:<%= book.getBookId() %>:<%= newAvailability.getCode() %>" <%= disableAttr %>>
                                            <%= buttonText %>
                                        </button>
                                    </td>
                                    <td><%= book.getCreatedAt() != null ? book.getCreatedAt().format(dateFormatter) : "N/A" %></td>
                                    <td><%= book.getCreatedBy() != null ? book.getCreatedBy() : "N/A" %></td>
                                    <td><%= book.getUpdatedAt() != null ? book.getUpdatedAt().format(dateFormatter) : "N/A" %></td>
                                    <td><%= book.getUpdatedBy() != null ? book.getUpdatedBy() : "N/A" %></td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="13" class="center-text">No books found in the library.</td>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            
             <p class="message-label" style="color: <%= (request.getAttribute("messageType") != null && request.getAttribute("messageType").equals("error")) ? "red" : "green" %>;">
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
                    out.println(message);
                }
            %>
        </p>

            <div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
                <button type="submit" name="action" value="refreshBooks">Refresh Books</button>
                <button type="submit" name="action" value="updateAvailabilityBatch" 
                        onclick="return confirm('Are you sure you want to update availability for selected books?')">Update Availability - Selected Books</button>
                <button type="submit" name="action" value="deleteBatch" 
                        onclick="return confirm('Are you sure you want to delete selected books?')">Delete Selected Books</button>
                <button type="button" onclick="location.href='main'">Back to Main Menu</button>
            </div>
        </form>
    </div>
</body>
</html>
package library.controllers;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.Book;
import library.model.IssueRecord;
import library.model.Member;
import library.service.BookServiceImpl;
import library.service.IssueServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService;
import library.validation.BookValidator;

@WebServlet("/issuedRecords")
public class IssuedRecordsServlet extends HttpServlet {

    private IssueService issueService;
    private BookService bookService;
    private MemberService memberService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init() throws ServletException {
        super.init();
        this.issueService = new IssueServiceImpl();
        this.bookService = new BookServiceImpl();
        this.memberService = new MemberServiceImpl();
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadIssuedRecords(request);
        request.getRequestDispatcher("/IssuedRecordsScreen.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.removeAttribute("message");
        request.removeAttribute("messageType");

        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            request.setAttribute("message", "No action specified.");
            request.setAttribute("messageType", "error");
        } else if (action.equals("refreshRecords")) {
        } else {
            request.setAttribute("message", "Unknown action: " + action);
            request.setAttribute("messageType", "error");
        }

        loadIssuedRecords(request);
        request.getRequestDispatcher("/IssuedRecordsScreen.jsp").forward(request, response);
    }

    private void loadIssuedRecords(HttpServletRequest request) {
        try {
            List<IssueRecord> records = issueService.getAllIssuedRecords();
            List<Map<String, Object>> displayRecords = new ArrayList<>();

            if (records.isEmpty()) {
                request.setAttribute("message", "No Issued records found in the library.");
                request.setAttribute("messageType", "info");
            } else {
                for (IssueRecord record : records) {
                    String bookTitle = "N/A";
                    String memberName = "N/A";

                    try {
                        BookValidator.validateNumericId(record.getBookId(), "Book ID");
                        Map<String, Object> bookCriteria = new HashMap<>();
                        bookCriteria.put("bookId", record.getBookId());
                        List<Book> books = bookService.findBooks(bookCriteria);
                        Book book = books.isEmpty() ? null : books.get(0);
                        if (book != null) {
                            bookTitle = book.getTitle();
                        }
                    } catch (LibraryException | IllegalArgumentException e) {
                        bookTitle = "Error Fetching";
                    } catch (Exception e) {
                        bookTitle = "Error Fetching";
                    }

                    try {
                        Member member = memberService.getMemberById(record.getMemberId());
                        if (member != null) {
                            memberName = member.getName();
                        }
                    } catch (LibraryException | IllegalArgumentException e) {
                        memberName = "Error Fetching";
                    } catch (Exception e) {
                        memberName = "Error Fetching";
                    }

                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("issueId", record.getIssueId());
                    rowData.put("bookId", record.getBookId());
                    rowData.put("bookTitle", bookTitle);
                    rowData.put("memberId", record.getMemberId());
                    rowData.put("memberName", memberName);
                    rowData.put("status", record.getStatus().toString());
                    rowData.put("issueDate", (record.getIssueDate() != null) ? record.getIssueDate().format(FORMATTER) : "N/A");
                    rowData.put("issuedBy", (record.getIssuedBy() != null) ? record.getIssuedBy() : "N/A");
                    rowData.put("returnDate", (record.getReturnDate() != null) ? record.getReturnDate().format(FORMATTER) : "N/A");
                    rowData.put("returnedBy", (record.getReturnedBy() != null) ? record.getReturnedBy() : "N/A");
                    
                    displayRecords.add(rowData);
                }
            }
            request.setAttribute("displayRecords", displayRecords);
            if (request.getAttribute("message") == null) {
                request.setAttribute("message", "");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error  loading records: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
    }
}
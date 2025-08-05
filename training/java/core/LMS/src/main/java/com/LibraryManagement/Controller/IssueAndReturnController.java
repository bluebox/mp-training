package com.LibraryManagement.Controller;

import com.LibraryManagement.Models.Book;
import com.LibraryManagement.Models.IssueRecords;
import com.LibraryManagement.Models.Member;
import com.LibraryManagement.Service.Implementation.BookServiceImplementation;
import com.LibraryManagement.Service.Implementation.IssueRecordServiceImplementation;
import com.LibraryManagement.Service.Implementation.MemberServiceImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/IssueAndReturnController")
public class IssueAndReturnController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final BookServiceImplementation bookService = new BookServiceImplementation();
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();
    private final IssueRecordServiceImplementation isi=new IssueRecordServiceImplementation();

    public IssueAndReturnController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "issueBook":
             
                    List<Book> bookList = bookService.getAllBooks();
                    List<Member> memberList = memberService.getAllMembers();

                    request.setAttribute("bookList", bookList);
                    request.setAttribute("memberList", memberList);

                    request.getRequestDispatcher("IssueBookForm.jsp").forward(request, response);
                    break;

                case "returnBook":
                    request.getRequestDispatcher("ReturnBookForm.jsp").forward(request, response);
                    break;

                case "viewIssues":
				try {
					List<IssueRecords> vi= isi.getAllIssues() ;
//					for(IssueRecords x:vi)
//					{
//						System.out.print(x.getBookId());
//					}
					request.setAttribute("issuesList", vi);
					request.getRequestDispatcher("ViewAllIssuesForm.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    //request.getRequestDispatcher("ViewAllIssuesForm.jsp").forward(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        doGet(request, response);
    }
}

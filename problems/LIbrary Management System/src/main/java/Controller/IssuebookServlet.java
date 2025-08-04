package Controller;

import Service.ServiceLayer;
import Domain.Book;
import Domain.IssueStatus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/issuebook")
public class IssuebookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServiceLayer service = new ServiceLayer();


   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookid = request.getParameter("bookid");
        String memberid = request.getParameter("memberid");
try {
        if (bookid == null || memberid == null) {
            throw new Exception("the fields are null ");
        } else {
                int bookId = Integer.parseInt(bookid);
                int memberId = Integer.parseInt(memberid);
                LocalDate returnDate = LocalDate.now().plusDays(14);
                service.createBookIssue(bookId, memberId,IssueStatus.ISSUED,LocalDate.now(),returnDate);
                 request.setAttribute("message", "created new issue succesfully");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/issueBookresult.jsp");
        dispatcher.forward(request, response);
        }
    }catch(Exception e){
    	   request.setAttribute("error", e.getMessage());
           RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/issueBookresult.jsp");
           dispatcher.forward(request, response);
    }
    	
    }
    }


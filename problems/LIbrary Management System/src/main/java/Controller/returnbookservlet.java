package Controller;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Domain.IssueStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import Service.ServiceLayer;

/**
 * Servlet implementation class returnbookservlet
 */
@WebServlet("/returnbook")
public class returnbookservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceLayer service=new ServiceLayer();
   
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
               
                service.returnBook(bookId, memberId);
                 request.setAttribute("message", "returned issue succesfully");
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


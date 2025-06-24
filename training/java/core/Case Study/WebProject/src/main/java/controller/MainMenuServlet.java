package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/MainMenu")
public class MainMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MainMenuServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String destination = null;

        if (action == null) {
            destination = "/error.html";
        } else {
            switch (action) {
                case "addBook":
                    destination = "/AddBook.html";
                    break;
                case "addMember":
                    destination = "/AddMember.html";
                    break;
                case "searchBook":
                    destination = "/SearchBook.html";
                    break;
                case "updateBook":
                    destination = "/UpdateBook.html";
                    break;
                case "allBooks":
                    destination = "/ViewBook.html";
                    break;
                case "allMembers":
                    destination = "/ViewMembers.html";
                    break;
                case "issuedBooks":
                    destination = "/IssuedBooks.html";
                    break;
                case "issueBookTab":
                    destination = "/IssueBook.html";
                    break;
                case "returnBookTab":
                    destination = "/ReturnBook.html";
                    break;
                default:
                    destination = "/error.html";
                    break;
            }
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        response.sendRedirect("MainMenu.html");
    }
}

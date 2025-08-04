package controller;

import Service.ServiceLayer;
import Domain.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/overduebooks")
public class overduebooks extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServiceLayer service = new ServiceLayer();
        List<Book> overdueBooks = null;

        try {
            overdueBooks = service.getOverdueBooks();  // returns List<Book>
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("books", overdueBooks);
        request.getRequestDispatcher("/WEB-INF/views/overduebooks.jsp").forward(request, response);
    }
}

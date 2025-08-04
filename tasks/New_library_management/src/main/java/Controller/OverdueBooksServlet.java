package Controller;

import Service.Reports;
import domain.Book;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/overduebooks")
public class OverdueBooksServlet extends HttpServlet {

    private Reports reportService = new Reports();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> books = reportService.overduebooks();
        System.out.println("Books: " + books);

        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/overduebooks.jsp");
        dispatcher.forward(request, response);
    }
}

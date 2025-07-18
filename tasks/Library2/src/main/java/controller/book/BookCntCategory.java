package controller.book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;
import model.BookCategoryCount;
import serviceimpl.BookServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class BookCntCategory
 */
@WebServlet("/bookcntcategory")
public class BookCntCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BookServiceImpl bookService = new BookServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCntCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<BookCategoryCount> books = bookService.getBookCountPerCategory();
			System.out.println(books);
            request.setAttribute("categoryCounts", books);
        } catch (Exception e) {
            request.setAttribute("error", "Unable to fetch books: " + e.getMessage());
        }
		
		request.getRequestDispatcher("/view/books/bookCategoryCount.jsp").forward(request, response);
		
	}

}

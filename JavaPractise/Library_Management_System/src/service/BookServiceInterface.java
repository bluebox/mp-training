package service;

import domain.Book;
import exceptions.ManagementException;
import java.util.List;

public interface BookServiceInterface {
	public void addBook(Book book) throws ManagementException;

	public void updateBook(Book book) throws ManagementException;

	public void updateBookAvailability(Book book) throws ManagementException;

	public List<Book> getAllBooks() throws ManagementException;

	public boolean bookExists(Book book) throws ManagementException;
}

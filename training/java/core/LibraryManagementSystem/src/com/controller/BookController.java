package com.controller;


import com.Exception.DatabaseException;
import com.Exception.InvalidInputException;
import com.Models.Book;
import com.Service.BookService;

import java.sql.SQLException;
import java.util.Scanner;

public class BookController {
    private BookService bookService = new BookService();

    public void menu(Scanner sc) throws InvalidInputException, DatabaseException, SQLException {
        while (true) {
            System.out.println("\n--- Book Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book Details");
            System.out.println("3. Update Book Availability");
            System.out.println("4. View All Books");
            System.out.println("5. View Count of Books per Category");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    Book newBook = new Book();
                    System.out.print("Enter Title: ");
                    newBook.setTitle(sc.nextLine());
                    System.out.print("Enter Author: ");
                    newBook.setAuthor(sc.nextLine());
                    System.out.print("Enter Category: ");
                    newBook.setCategory(sc.nextLine());
                    newBook.setStatus("A");
                    newBook.setAvailability("A");
                    bookService.addBook(newBook);
                    break;
                case 2:
                    System.out.print("Enter Book ID to update: ");
                    int updateId = sc.nextInt(); sc.nextLine();
                    Book updateBook = new Book();
                    updateBook.setBookId(updateId);
                    System.out.print("Enter New Title: ");
                    updateBook.setTitle(sc.nextLine());
                    System.out.print("Enter New Author: ");
                    updateBook.setAuthor(sc.nextLine());
                    System.out.print("Enter New Category: ");
                    updateBook.setCategory(sc.nextLine());
                    System.out.print("Enter New Status: ");
                    updateBook.setStatus(String.valueOf(sc.nextLine().toUpperCase().charAt(0)));
                    bookService.updateBook(updateBook);
                    break;
                case 3:
                    System.out.print("Enter Book ID to update availability: ");
                    int bookId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Availability (A/I): ");
                    char avail = sc.nextLine().toUpperCase().charAt(0);
                    bookService.updateAvailability(bookId, avail);
                    break;
                case 4:
                    bookService.getAllBooks().forEach(System.out::println);
                    break;
                case 5:
                	bookService.countBooksPerCategory();
                	break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
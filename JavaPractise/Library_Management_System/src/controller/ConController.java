package controller;

import java.util.List;
import java.util.Scanner;
import domain.BookStatus;
import domain.AvailabilityStatus;

import domain.Book;
import service.BookService;

public class ConController {
	BookService bs;
	Scanner sc;
	public ConController(BookService bs) {
		this.bs = bs;
		sc=new Scanner(System.in);
	}

	public void addBook() {
		 System.out.print("Enter Book ID: ");
         int id = Integer.parseInt(sc.nextLine());
         System.out.print("Enter Book Title: ");
         String name = sc.nextLine();
         System.out.print("Enter Book Author: ");
         String author= sc.nextLine();
         System.out.print("Enter Book Category: ");
         String category= sc.nextLine();
         System.out.print("Enter Book Status: ");
         String stat = sc.nextLine().trim().toUpperCase(); 
         BookStatus status = BookStatus.valueOf(stat);
         System.out.print("Enter Book Availability: ");
         String avail= sc.nextLine().trim().toUpperCase();
         AvailabilityStatus availability = AvailabilityStatus.valueOf(avail);
         
        
		 bs.addBook(new Book(id,name,author,category,status,availability));
	}

	public void updateBookDetails() {
		System.out.print("Enter Book ID to update: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter  new Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter new Book Author: ");
        String author= sc.nextLine();
        System.out.print("Enter new Book Category: ");
        String category= sc.nextLine();
        System.out.print("Enter new Book Status: ");
        String stat = sc.nextLine().trim().toUpperCase(); 
        BookStatus status = BookStatus.valueOf(stat);
        bs.updateBookDetails(id,title,author,category,status);
	}

	public void updateBookAvailability() {
		// TODO Auto-generated method stub
		System.out.print("Enter Book ID to update: ");
        int id = Integer.parseInt(sc.nextLine());
		System.out.print("Enter Book Availability: ");
        String avail= sc.nextLine().trim().toUpperCase();
        AvailabilityStatus availability = AvailabilityStatus.valueOf(avail);
        bs.updateBookAvailability(id,availability);
		
	}

	public void viewAllBooks() {
		// TODO Auto-generated method stub
		List<Book> list=bs.getAllBooks();
		for(Book book:list) {
			System.out.println(book);
		}
		
	}

}

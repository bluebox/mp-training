package library.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

import library.exception.LibraryException;
import library.model.Book;
import library.model.Member;
import library.model.enums.BookAvailability;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.model.enums.Gender;
import library.service.BookServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.MemberService;


public class DataLoader {

    private static final String BOOKS_CSV_FILE_NAME = "initial_books.csv";
    private static final String MEMBERS_CSV_FILE_NAME = "initial_members.csv";


    private static BookService bookService;
    private static MemberService memberService;
    private static final String SYSTEM_USER = "SYSTEM"; 

    static {
        bookService = new BookServiceImpl();
        memberService = new MemberServiceImpl();
    }

    private DataLoader() {
    }

    public static void loadInitialBookData() {
        try {
            if (!bookService.findBooks(Collections.emptyMap()).isEmpty()) {
                System.out.println("DataLoader: Books table is not empty. Skipping initial book data load.");
                return;
            }
        } catch (LibraryException e) {
            System.err.println("DataLoader: Error checking if books table is empty: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("DataLoader: Unexpected error checking book data: " + e.getMessage());
        }

        System.out.println("DataLoader: Books table is empty. Loading initial data from " + BOOKS_CSV_FILE_NAME + "...");
        try (InputStream input = DataLoader.class.getClassLoader().getResourceAsStream(BOOKS_CSV_FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            if (input == null) {
                System.err.println("DataLoader: CSV file not found: " + BOOKS_CSV_FILE_NAME);
                return;
            }

            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    try {
                        String title = parts[0].trim();
                        String author = parts[1].trim();
                        String categoryDisplayName = parts[2].trim();
                        String statusCode = parts[3].trim();
                        String availabilityCode = parts[4].trim();

                        BookCategory category = BookCategory.fromDisplayName(categoryDisplayName);
                        BookStatus status = BookStatus.fromCode(statusCode);
                        BookAvailability availability = BookAvailability.fromCode(availabilityCode);

//                        Book book = new Book(title, author, category, status, availability);
                        Book book =new Book();
                        book.setTitle(title);
                        book.setAuthor(author);
                        book.setCategory(category);
                        book.setStatus(status);
                        book.setAvailability(availability);

                        bookService.addBook(book, SYSTEM_USER);
                    } catch (IllegalArgumentException | LibraryException e) {
                        System.err.println("Error: Problem adding book from CSV (validation/duplicate/db): " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("Unexpected error adding book from CSV: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("DataLoader: Skipping malformed book CSV line (incorrect parts count): " + line);
                }
            }
            System.out.println("DataLoader: Initial book data load complete.");
        } catch (IOException e) {
            System.err.println("DataLoader: Error reading book CSV file: " + e.getMessage());
        }
    }

    public static void loadInitialMemberData() {
        try {
            if (!memberService.getAllMembers().isEmpty()) {
                System.out.println("DataLoader: Members table is not empty. Skipping initial member data load.");
                return;
            }
        } catch (LibraryException e) {
            System.err.println("DataLoader: Error checking if members table is empty: " + e.getMessage());
        } catch (Exception e) {
             System.err.println("DataLoader: Unexpected error checking member data: " + e.getMessage());
        }

        System.out.println("DataLoader: Members table is empty. Loading initial data from " + MEMBERS_CSV_FILE_NAME + "...");
        try (InputStream input = DataLoader.class.getClassLoader().getResourceAsStream(MEMBERS_CSV_FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            if (input == null) {
                System.err.println("DataLoader: CSV file not found: " + MEMBERS_CSV_FILE_NAME);
                return;
            }

            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    try {
                        String name = parts[0].trim();
                        String email = parts[1].trim();
                        long phoneNumber = Long.parseLong(parts[2].trim());
                        char genderCode = parts[3].trim().charAt(0);
                        String address = parts[4].trim();

                        Gender gender = Gender.fromCode(genderCode);

                        Member member = new Member(0, name, email, phoneNumber, gender, address);

                        String message = memberService.addMember(member);
                        System.out.println("DataLoader: " + message);
                    } catch (NumberFormatException e) {
                        System.err.println("DataLoader: Skipping malformed member CSV line (invalid number format): " + line + " - " + e.getMessage());
                    } catch (IllegalArgumentException | LibraryException e) {
                        System.err.println("DataLoader: Error inserting member from CSV: " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("DataLoader: Unexpected error adding member from CSV: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("DataLoader: Skipping malformed member CSV line (incorrect parts count): " + line);
                }
            }
            System.out.println("DataLoader: Initial member data load complete.");
        } catch (IOException e) {
            System.err.println("DataLoader: Error reading member CSV file: " + e.getMessage());
        }
    }
}
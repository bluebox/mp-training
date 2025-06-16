package control;

import java.util.List;
import java.util.Scanner;

import pojo.Book;
import pojo.Book.Availability;
import pojo.Book.Status;
import pojo.Member;
import pojo.Member.Gender;
import service.BookService;
import service.IssueService;
import service.MemberService;
import service.exceptions.DatabaseException;
import service.exceptions.InvalidInputException;
import service.exceptions.LogicalError;

public class MainApp {

    private static Scanner scanner = new Scanner(System.in);
    
    private static BookService bookService = new BookService();
    private static MemberService memberService = new MemberService();
    private static IssueService issueService = new IssueService();

    public static void main(String[] args) {
        System.out.println("Welcome to the Library Management System (CLI)");
        displayMainMenu();
        System.out.println("Exiting Library Management System. Goodbye!");
    }

    private static void displayMainMenu() {
        int choice;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Book Management");
            System.out.println("2. Member Management");
            System.out.println("3. Issue/Return Books");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayBookManagementMenu();
                    break;
                case 2:
                    displayMemberManagementMenu();
                    break;
                case 3:
                    displayIssueReturnMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void displayBookManagementMenu() {
        int choice;
        do {
            System.out.println("\n--- Book Management ---");
            System.out.println("1. Add New Book");
            System.out.println("2. Update Book Details");
            System.out.println("3. Update Book Availability");
            System.out.println("4. View All Books");
            System.out.println("0. Back to Main Menu");
            System.out.println("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    updateBookDetails();
                    break;
                case 3:
                    updateBookAvailability();
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void addNewBook() {
    	String title, author, category;
        System.out.println("\n--- Add New Book ---");
        System.out.println("Enter Title: ");
        title = scanner.nextLine();
        System.out.println("Enter Author: ");
        author = scanner.nextLine();
        System.out.println("Enter Category: ");
        category = scanner.nextLine();

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setCategory(category);
        newBook.setStatus(Status.ACTIVE);      
        newBook.setAvailability(Availability.AVAILABLE); 
        try {
            bookService.addBook(newBook);
            System.out.println("Book added successfully!");
        } catch (InvalidInputException | DatabaseException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    private static void updateBookDetails() {
        System.out.println("\n--- Update Book Details ---");
        System.out.println("Enter Book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        try {
            Book existingBook = bookService.getBookById(bookId);
            if (existingBook == null) {
                System.out.println("Book with ID " + bookId + " not found.");
                return;
            }

            System.out.println("Current Details for Book ID " + bookId + ":");
            System.out.println(existingBook);

            System.out.println("Enter New Title (leave blank to keep current: '" + existingBook.getTitle() + "'): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                existingBook.setTitle(newTitle);
            }

            System.out.println("Enter New Author (leave blank to keep current: '" + existingBook.getAuthor() + "'): ");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.isEmpty()){
                existingBook.setAuthor(newAuthor);
            }

            System.out.println("Enter New Category (leave blank to keep current: '" + existingBook.getCategory() + "'): ");
            String newCategory = scanner.nextLine();
            if (!newCategory.isEmpty()) {
                existingBook.setCategory(newCategory);
            }

            System.out.println("Enter New Status (A for Active, I for Inactive - leave blank for current: '" + existingBook.getStatus().toString().charAt(0) + "'): ");
            String newStatusChar = scanner.nextLine().trim().toUpperCase();
            if (!newStatusChar.isEmpty()) {
                if (newStatusChar.equals("A")) {
                    existingBook.setStatus(Status.ACTIVE);
                } else if (newStatusChar.equals("I")) {
                    existingBook.setStatus(Status.INACTIVE);
                } else {
                    System.out.println("Invalid status input. Keeping current status.");
                }
            }

            bookService.updateBookDetails(existingBook);
            System.out.println("Book details updated successfully!");

        } catch (InvalidInputException | DatabaseException e) {
            System.out.println("Error updating book details: " + e.getMessage());
        }
    }

    private static void updateBookAvailability() {
        System.out.println("\n--- Update Book Availability ---");
        System.out.println("Enter Book ID to update availability: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter New Availability (A for Available, I for Issued): ");
        String availabilityChar = scanner.nextLine().trim().toUpperCase();
        Availability newAvailability = null;
        if (availabilityChar.equals("A")) {
            newAvailability = Availability.AVAILABLE;
        } else if (availabilityChar.equals("I")) {
            newAvailability = Availability.ISSUED;
        } else {
            System.out.println("Invalid availability input. Keeping current availability.");
            return;
        }

        try {
            bookService.updateBookAvailability(bookId, newAvailability);
            System.out.println("Book availability updated successfully!");
        } catch (InvalidInputException | DatabaseException e) {
            System.out.println("Error updating book availability: " + e.getMessage());
        }
    }

    private static void viewAllBooks() {
        System.out.println("\n--- All Books ---");
        try {
            List<Book> books = bookService.getAllBooks();
            if (books.isEmpty()) {
                System.out.println("No books found in the library.");
                return;
            }
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (DatabaseException e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }
    }

    private static void displayMemberManagementMenu() {
        int choice;
        do {
            System.out.println("\n--- Member Management ---");
            System.out.println("1. Register New Member");
            System.out.println("2. Update Member Details");
            System.out.println("3. View All Members");
            System.out.println("0. Back to Main Menu");
            System.out.println("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerNewMember();
                    break;
                case 2:
                    updateMemberDetails();
                    break;
                case 3:
                    viewAllMembers();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void registerNewMember() {
        System.out.println("\n--- Register New Member ---");
        System.out.println("Enter Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter Mobile : ");
        long mobile = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter Gender (M/F): ");
        String genderChar = scanner.nextLine().trim().toUpperCase();
        Gender gender = null;
        if (genderChar.equals("M")) {
            gender = Gender.MALE;
        } else if (genderChar.equals("F")) {
            gender = Gender.FEMALE;
        } else {
            System.out.println("Invalid gender input. Member not added.");
            return;
        }
        System.out.println("Enter Address: ");
        String address = scanner.nextLine();

        Member newMember = new Member();
        newMember.setName(name);
        newMember.setEmail(email);
        newMember.setMobile(mobile);
        newMember.setGender(gender);
        newMember.setAddress(address);

        try {
            memberService.addMember(newMember);
            System.out.println("Member registered successfully!");
        } catch (InvalidInputException | DatabaseException e) {
            System.out.println("Error registering member: " + e.getMessage());
        }
    }

    private static void updateMemberDetails() {
        System.out.println("\n--- Update Member Details ---");
        System.out.println("Enter Member ID to update: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        try {
            Member existingMember = memberService.getMemberById(memberId);
            if (existingMember == null) {
                System.out.println("Member with ID " + memberId + " not found.");
                return;
            }

            System.out.println("Current Details for Member ID " + memberId + ":");
            System.out.println(existingMember);

            System.out.println("Enter New Name (leave blank to keep current: '" + existingMember.getName() + "'): ");
            String newName = scanner.nextLine();
            if (!newName.trim().isEmpty()) {
                existingMember.setName(newName);
            }

            System.out.println("Enter New Email (leave blank to keep current: '" + existingMember.getEmail() + "'): ");
            String newEmail = scanner.nextLine();
            if (!newEmail.trim().isEmpty()) {
                existingMember.setEmail(newEmail);
            }

            System.out.println("Enter New Mobile (leave blank to keep current: '" + existingMember.getMobile() + "'): ");
            String newMobileStr = scanner.nextLine();
            if (!newMobileStr.trim().isEmpty()) {
                try {
                    existingMember.setMobile(Long.parseLong(newMobileStr));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid mobile number format. Keeping current mobile.");
                }
            }

            System.out.println("Enter New Gender (M/F - leave blank to keep current: '" + existingMember.getGender().toString().charAt(0) + "'): ");
            String newGenderChar = scanner.nextLine().trim().toUpperCase();
            if (!newGenderChar.isEmpty()) {
                if (newGenderChar.equals("M")) {
                    existingMember.setGender(Gender.MALE);
                } else if (newGenderChar.equals("F")) {
                    existingMember.setGender(Gender.FEMALE);
                } else {
                    System.out.println("Invalid gender input. Keeping current gender.");
                }
            }

            System.out.println("Enter New Address (leave blank to keep current: '" + existingMember.getAddress() + "'): ");
            String newAddress = scanner.nextLine();
            if (!newAddress.trim().isEmpty()) {
                existingMember.setAddress(newAddress);
            }

            memberService.updateMember(existingMember);
            System.out.println("Member details updated successfully!");

        } catch (InvalidInputException | DatabaseException e) {
            System.out.println("Error updating member details: " + e.getMessage());
        }
    }

    private static void viewAllMembers() {
        System.out.println("\n--- All Members ---");
        try {
            List<Member> members = memberService.getAllMembers();
            if (members.isEmpty()) {
                System.out.println("No members found in the library.");
                return;
            }
            for (Member member : members) {
                System.out.println(member);
            }
        } catch (DatabaseException e) {
            System.out.println("Error retrieving members: " + e.getMessage());
        }
    }

    private static void displayIssueReturnMenu() {
        int choice;
        do {
            System.out.println("\n--- Issue/Return Books ---");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("0. Back to Main Menu");
            System.out.println("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    issueBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void issueBook() {
        System.out.println("\n--- Issue Book ---");
        System.out.println("Enter Book ID to issue: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Member ID to issue to: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        try {
            issueService.issueBook(bookId, memberId);
            System.out.println("Book issued successfully!");
        } catch (InvalidInputException | LogicalError | DatabaseException e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.println("\n--- Return Book ---");
        System.out.println("Enter Issue Record ID to return: ");
        int issueId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Book ID related to this issue (for verification): ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        try {
            issueService.returnBook(issueId, bookId);
            System.out.println("Book returned successfully!");
        } catch (InvalidInputException | LogicalError | DatabaseException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}
 
package com.Main;

import com.Exception.BookAlreadyIssuedException;
import com.Exception.DatabaseException;
import com.Exception.InvalidInputException;
import com.controller.BookController;
import com.controller.MemberController;
import com.controller.IssueController;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidInputException, DatabaseException, SQLException, BookAlreadyIssuedException {
        Scanner sc = new Scanner(System.in);
        BookController bookController = new BookController();
        MemberController memberController = new MemberController();
        IssueController issueController = new IssueController();

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Book Management");
            System.out.println("2. Member Management");
            System.out.println("3. Issue/Return Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookController.menu(sc);
                    break;
                case 2:
                    memberController.menu(sc);
                    break;
                case 3:
                    issueController.menu(sc);
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
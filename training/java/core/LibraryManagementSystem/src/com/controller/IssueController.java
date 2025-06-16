package com.controller;

import com.Exception.*;
import com.Models.Issue;
import com.Service.IssueService;

import java.util.List;
import java.util.Scanner;

public class IssueController {
    private final IssueService issueService = new IssueService();

    public void menu(Scanner sc) throws DatabaseException, BookAlreadyIssuedException {
        while (true) {
            System.out.println("\n--- Issue/Return Management ---");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Overdue Books");
         //   System.out.println("4. View Count of Books per Category");
            System.out.println("4. View Members with Active Issued Books");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID to issue: ");
                    int bookId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt(); sc.nextLine();
                    issueService.issueBook(bookId, memberId);
                    break;
                case 2:
                	System.out.print("Enter Member ID to return: ");
                    int returnMemberId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = sc.nextInt(); sc.nextLine();
                    issueService.returnBook(returnBookId,returnMemberId);
                    break;
                case 3:
                    issueService.getAllOverDueBooks().forEach(System.out::println);
                    break;
//                case 4:
//                    issueService.viewBooksCountByCategory().forEach((k,v) -> System.out.println(k + ": " + v));
//                    break;
                case 4:
                    issueService.getAllIssuedRecords().forEach(System.out::println);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

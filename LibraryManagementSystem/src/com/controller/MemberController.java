package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.Exception.DatabaseException;
import com.Exception.InvalidInputException;
import com.Models.Member;
import com.Service.MemberService;

public class MemberController {
    private final MemberService memberService = new MemberService();

    public void menu(Scanner sc) throws InvalidInputException, DatabaseException, SQLException {
        while (true) {
            System.out.println("\n--- Member Management ---");
            System.out.println("1. Register Member");
            System.out.println("2. Update Member Details");
            System.out.println("3. View All Members");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    Member newMember = new Member();
                    System.out.print("Enter Name: ");
                    newMember.setName(sc.nextLine());
                    System.out.print("Enter Email: ");
                    newMember.setEmail(sc.nextLine());
                    System.out.print("Enter Mobile: ");
                    newMember.setMobile(sc.nextLine()); sc.nextLine();
                    System.out.print("Enter Gender (M/F): ");
                    newMember.setGender(sc.nextLine().toUpperCase().charAt(0));
                    System.out.print("Enter Address: ");
                    newMember.setAddress(sc.nextLine());
                    memberService.addMember(newMember);
                    break;
                case 2:
                    System.out.print("Enter Member ID to update: ");
                    int memberId = sc.nextInt(); sc.nextLine();
                    Member updateMember = new Member();
                    updateMember.setMemberId(memberId);
                    System.out.print("Enter New Name: ");
                    updateMember.setName(sc.nextLine());
                    System.out.print("Enter New Email: ");
                    updateMember.setEmail(sc.nextLine());
                    System.out.print("Enter New Mobile: ");
                    updateMember.setMobile(sc.nextLine()); sc.nextLine();
                    System.out.print("Enter New Gender (M/F): ");
                    updateMember.setGender(sc.nextLine().toUpperCase().charAt(0));
                    System.out.print("Enter New Address: ");
                    updateMember.setAddress(sc.nextLine());
                    memberService.updateMember(updateMember);
                    break;
                case 3:
                    memberService.getAllMembers().forEach(System.out::println);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
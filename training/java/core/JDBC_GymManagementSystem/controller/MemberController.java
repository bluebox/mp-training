package controller;

import service.MemberService;
import java.util.Scanner;

public class MemberController {
    private MemberService memberService = new MemberService();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n--- GYM MANAGEMENT MENU ---");
            System.out.println("1. Register Member");
            System.out.println("2. Assign Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Update Member");
            System.out.println("5. Remove Member");
            System.out.println("6. Export to CSV");
            System.out.println("7. Import from CSV");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> memberService.addMember();
                case 2 -> memberService.assignPlanToMember();
                case 3 -> memberService.viewAllMembers();
                case 4 -> memberService.updateMember();
                case 5 -> memberService.removeMember();
                case 6 -> memberService.exportToCSV();
                case 7 -> memberService.importFromCSV();
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}

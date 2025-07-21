import java.util.Scanner;

import controllers.MemberController;
import controllers.MembershipController;
import controllers.MembershipPlanController;
import dao.implimentions.MemberDaoImpl;
import dao.implimentions.MembershipPlanDaoImpl;
import dao.implimentions.PersonMembershipDaoImpl;
import services.MemberService;
import services.MembershipPlanService;
import services.PersonMembershipService;
import services.implimentations.MemberServiceImpl;
import services.implimentations.MembershipPlanServiceImpl;
import services.implimentations.PersonMembershipServiceImpl;
import utils.DBConnection;
import utils.PreparedStatementManager;

public class Main {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			PreparedStatementManager.closeAllStatements();
			DBConnection.closeConnection();
		}));

		MemberService memberService = new MemberServiceImpl(new MemberDaoImpl());
		MembershipPlanService planService = new MembershipPlanServiceImpl(new MembershipPlanDaoImpl());
		PersonMembershipService membershipService = new PersonMembershipServiceImpl(new PersonMembershipDaoImpl());

		MemberController memberController = new MemberController(memberService, sc);
		MembershipPlanController membershipPlanController = new MembershipPlanController(planService, sc);
		MembershipController membershipController = new MembershipController(membershipService, planService,
				memberService, sc);

		boolean exit = false;

		while (!exit) {
			printOptions();

			try {
				int choice = Integer.parseInt(sc.nextLine());

				switch (choice) {
				case 1:
					memberController.addMember();
					break;
				case 2:
					memberController.updateMember();
					System.out.println("Member updated successfully!");
					break;
				case 3:
					memberController.deleteMember();
					break;
				case 4:
					memberController.getMemberById();
					break;
				case 5:
					memberController.getAllMembers();
					break;
				case 6:
					membershipPlanController.getAllMembers();
					break;
				case 7:
					membershipController.assignPlan();
					break;
				case 8:
					membershipController.getAllMemberships();
					break;
				case 9:
					membershipController.getActiveMemberships();
					break;
				case 10:
					membershipController.deleteMembership();
					break;
				case 11:
					memberService.exportMembers();
					break;
				case 12:
					planService.exportPlans();
					break;
				case 13:
					membershipService.exportMembershipDetails();
					break;
				case 14:
					membershipService.exportActiveMembershipDetails();
					break;
				case 0:
					exit = true;
					System.out.println("Exited Successfully");
					break;
				default:
					System.out.println("Invalid choice. Try again.");
				}

			} catch (NumberFormatException e) {
				System.out.println("Please enter numbers Choice.");
			} catch (Exception e) {
				System.out.println("An unexpected error occurred: " + e.getMessage());
			}

		}

	}

	private static void printOptions() {

		System.out.println("\n=== Gym Membership Management System ===");
		System.out.println("1. Add New Member");
		System.out.println("2. Update Member");
		System.out.println("3. Delete Member");
		System.out.println("4. View Member by ID");
		System.out.println("5. View All Members");

		System.out.println("6. View All Plans");

		System.out.println("7. Assign Membership to Member");
		System.out.println("8. View All Memberships");
		System.out.println("9. View Active Memberships");
		System.out.println("10. Delete Membership");

		System.out.println("11. Export Members Details");
		System.out.println("12. Export Plan Details");
		System.out.println("13. Export Memberships Details");
		System.out.println("14. Export Active Memberships Details");

		System.out.println("0. Exit");
		System.out.print("Enter your choice: ");
	}
}

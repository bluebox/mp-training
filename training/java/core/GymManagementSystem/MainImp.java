package GymManagementSystem;

import GymManagementSystem.service.GymService;
import GymManagementSystem.service.InputValidation;

public class MainImp {
	private GymService service;
	private InputValidation validation;

	public MainImp() {
	   service = new GymService();
	   validation = new InputValidation();
	}

	public void start() {
		while (true) {
			displayMenu();
			int choice = validation.getIntInput("Enter your choice: ");
			switch (choice) {
			case 1 -> service.addMember();
			case 2 -> service.viewMembers();
			case 3 -> service.addPlan();
			case 4 -> service.viewPlans();
			case 5 -> service.mapMemberToPlan();
			case 6 -> service.viewActiveMembers();
			case 7 -> service.updateMember();
			case 8 -> service.deleteMember();
			case 9 -> service.updatePlan();
			case 10 -> service.deletePlan();
			case 11 -> service.getReport();
			case 12 -> {
				System.out.println("Exiting...");
				return;
			}
			default -> System.out.println("Invalid option, try again.");
			}
		}
	}

	private void displayMenu() {
		System.out.println("\n===== Gym Membership Management System =====");
		System.out.println("1. Add Member");
		System.out.println("2. View All Members");
		System.out.println("3. Add Membership Plan");
		System.out.println("4. View All Plans");
		System.out.println("5. Map Member to Plan");
		System.out.println("6. View Active Members");
		System.out.println("7. Update Member");
		System.out.println("8. Delete Member");
		System.out.println("9. Update Plan");
		System.out.println("10. Delete Plan");
		System.out.println("11. View Gym Details");
		System.out.println("12. Exit");
	}
	
}

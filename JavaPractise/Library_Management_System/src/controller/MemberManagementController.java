package controller;
import java.util.List;
import java.util.Scanner;
import domain.Gender;
import domain.Member;
import service.MemberManagementService;
public class MemberManagementController {
	 private MemberManagementService membermanagementService;
	 public MemberManagementController()
	 {
		 this.membermanagementService=new MemberManagementService();
	 }
	    Scanner sc=new Scanner(System.in);
		public void start() throws Exception {
	        while (true) {
	            System.out.println("\n===== Member Management Menu =====");
	            System.out.println("1. Register New Member");
	            System.out.println("2. Update Member Details");
	            System.out.println("3. View All Members");
	            System.out.println("4. Exit");
	            System.out.print("Choose an option: ");
	            int choice = sc.nextInt();
	            sc.nextLine();
	            switch (choice) {
	                case 1:
	                	try {
	                    System.out.println("Enter Name: ");
	                    String name = sc.nextLine();
	                    System.out.println("Enter Email: ");
	                    String email = sc.nextLine();
	                    System.out.println("Enter Mobile: ");
	                    String mobile = sc.nextLine();
	                    System.out.println("Enter Gender (M/F/O): ");
	                    String genderInput = sc.nextLine().toUpperCase();
	                    Gender gender = Gender.valueOf(genderInput);
	                    System.out.println("Enter Address: ");
	                    String address = sc.nextLine();
	                    Member newMember = new Member(name, email, mobile, gender, address);
	                    membermanagementService.registerMember(newMember);
	                	}
	                	catch (IllegalArgumentException e) {
	                	    System.out.println(e.getMessage());
	                	}
	                	   break;
	                case 2:
	                    System.out.println("Enter Member ID to update: ");
	                    int memberId = sc.nextInt();
	                    sc.nextLine();
	                    System.out.println("Enter Updated Email: ");
	                    String updatedEmail = sc.nextLine();
	                    System.out.println("Enter Updated Mobile: ");
	                    String updatedMobile = sc.nextLine();
	                    System.out.println("Enter Updated Address: ");
	                    String updatedAddress = sc.nextLine();
	                    Member updatedMember = new Member();
	                    updatedMember.setEmail(updatedEmail);
	                    updatedMember.setMobile(updatedMobile);
	                    updatedMember.setAddress(updatedAddress);
	                    membermanagementService.updateMember(memberId, updatedMember);
	                    break;
	                case 3:
	                    List<Member> members = membermanagementService.getAllMembers();
	                    if (members == null || members.isEmpty()) {
	                        System.out.println("No members found.");
	                    } else {
	                        System.out.println("\n--- All Registered Members ---");
	                        for (Member member : members) {
	                            System.out.println("Name: " + member.getName() +
	                                    ", Email: " + member.getEmail() +
	                                    ", Mobile: " + member.getMobile() +
	                                    ", Gender: " + member.getGender() +
	                                    ", Address: " + member.getAddress());
	                        }
	                    }
	                    break;
	                case 4:
	                    System.out.println("Exiting... Thank you!");
	                    return;

	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	        }
	    }
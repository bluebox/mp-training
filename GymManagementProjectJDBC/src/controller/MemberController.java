package controller;

import java.util.List;
import java.util.Scanner;

import models.Member;
import serviceImpl.MemberServiceImpl;
import services.MemberService;
import utils.Validations;

public class MemberController {

	private final MemberService memberService = new MemberServiceImpl();
	private final Scanner sc;

	public MemberController(Scanner sc) {
		this.sc = sc;
	}

	public void addMember() {

		System.out.print("Enter Member Name : ");
		String name = sc.nextLine();
		if (!Validations.isValidName(name)) {
			System.out.println("Invalid Name! Try again.");
			return;
		}

		System.out.print("Enter Age: ");
		String ageStr = sc.nextLine();
		if (!Validations.isValidAge(ageStr)) {
			System.out.println("Invalid Age! Try again.");
			return;
		}
		int age = Integer.parseInt(ageStr);

		System.out.print("Enter Contact Number: ");
		String contact = sc.nextLine();
		if (!Validations.isValidContact(contact)) {
			System.out.println("Invalid Contact Number! Try again.");
			return;
		}

		memberService.addMember(new Member(name, age, contact));
	}

	public void updateMember() {

		System.out.print("Enter Member ID to update: ");
		int updateId = Integer.parseInt(sc.nextLine());

		Member member = memberService.getMemberById(updateId);
		if (member == null) {
			System.out.println("Member with ID " + updateId + " not found.");
			return;
		}

		System.out.println("\n--- Update Member Details ---");
		System.out.println("Leave field empty to keep current value.");

		System.out.print("Enter Name [" + member.getName() + "]: ");
		String updateName = sc.nextLine();

		if (!updateName.isEmpty()) {
			member.setName(updateName);
		}

		System.out.print("Enter Age [" + member.getAge() + "]: ");
		String updateAgeStr = sc.nextLine();

		if (!updateAgeStr.isEmpty()) {
			if (Validations.isValidAge(updateAgeStr)) {
				member.setAge(Integer.parseInt(updateAgeStr));
			} else {
				System.out.println("Invalid Age! Keeping previous value.");
			}
		}

		System.out.print("Enter Contact [" + member.getContactDetails() + "]: ");
		String updateContact = sc.nextLine();

		if (!updateContact.isEmpty()) {
			if (Validations.isValidContact(updateContact)) {
				member.setContactDetails(updateContact);
			} else {
				System.out.println("Invalid Contact! Keeping previous value.");
			}
		}

		memberService.updateMember(member);

	}

	public void deleteMember() {

		System.out.print("Enter Member ID to update: ");
		int deletedId = Integer.parseInt(sc.nextLine());

		Member deletedmember = memberService.getMemberById(deletedId);
		if (deletedmember == null) {
			System.out.println("Member with ID " + deletedId + " not found.");
			return;
		}

		System.out.println("Member Deleted successfully!");
		memberService.deleteMember(deletedId);

	}

	public void getMemberById() {

		System.out.print("Enter Member ID to View details: ");
		int serchedId = Integer.parseInt(sc.nextLine());

		Member memberdetails = memberService.getMemberById(serchedId);
		if (memberdetails == null) {
			System.out.println("Member with ID " + serchedId + " not found.");
			return;
		}

		System.out.println(memberdetails);

	}

	public void getAllMembers() {

		List<Member> members = memberService.getAllMembers();
		if (members.size() == 0) {
			System.out.println("No members here!");
		}
		members.forEach(System.out::println);
	}

}

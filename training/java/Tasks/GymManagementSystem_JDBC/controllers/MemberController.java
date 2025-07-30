package GymManagementSystem.controllers;

import java.util.List;

import GymManagementSystem.models.Member;
import GymManagementSystem.service.MemberService;
import GymManagementSystem.service.Impl.MemberServiceImpl;
import GymManagementSystem.utils.InputValidation;

public class MemberController {
	private MemberService memberService = new MemberServiceImpl();
	private InputValidation val = new InputValidation();
	
	public void addMember() {
		String name = val.getStringInput("Enter member name: ");
		int age = val.getPositiveIntInput("Enter age: ");
		Member member = new Member(name, age); 
		memberService.addMember(member);
	}

	public void updateMember() {
		int memberId = val.getIntInput("Enter member ID to update: ");
		if (!val.isValidMemberId(memberId)) {
			System.out.println("Member ID not found. Please enter a valid ID.");
			return;
		}
		String name = val.getStringInput("Enter new name: ");
		int age = val.getIntInput("Enter new age: ");
		Member member = new Member(name, age, memberId);
		memberService.updateMember(member);
	}

	public void deleteMember() {
		int memberId = val.getIntInput("Enter member ID to delete: ");
		if (!val.isValidMemberId(memberId)) {
			System.out.println("Member ID not found. Please enter a valid ID.");
			return;
		}
		memberService.deleteMember(memberId);
	}
	
	public void viewMembers() {
		List<Member> members = memberService.viewMembers();
		if (members.isEmpty()) {
			System.out.println("No members found.");
		} else {
			members.forEach(System.out::println);
		}
	}
}

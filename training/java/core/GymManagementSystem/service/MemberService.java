package GymManagementSystem.service;

import java.util.List;

import GymManagementSystem.DAO.MemberDAO;
import GymManagementSystem.models.Member;

public class MemberService {
	private MemberDAO memberDAO;
	private InputValidation val;

	public MemberService() {
		memberDAO = new MemberDAO();
		val = new InputValidation();
	}
	
	public void addMember() {
		String name = val.getStringInput("Enter member name: ");
		int age = val.getPositiveIntInput("Enter age: ");
		Member member = new Member(name, age); 
		memberDAO.addMember(member);
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
		memberDAO.updateMember(member);
	}

	public void deleteMember() {
		int memberId = val.getIntInput("Enter member ID to delete: ");
		if (!val.isValidMemberId(memberId)) {
			System.out.println("Member ID not found. Please enter a valid ID.");
			return;
		}
		memberDAO.deleteMember(memberId);
	}
	
	public void viewMembers() {
		List<Member> members = memberDAO.getAllMembers();
		if (members.isEmpty()) {
			System.out.println("No members found.");
		} else {
			members.forEach(System.out::println);
		}
	}
}

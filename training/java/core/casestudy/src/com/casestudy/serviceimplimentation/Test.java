package com.casestudy.serviceimplimentation;

import com.casestudy.domain.IssueRecord;

public class Test {

	public static void main(String[] args) {
		Service s = new Service();
		//s.addBook(new Book("Undying Affinity","Sara Naveed","Fiction",Status.ACTIVE,Availability.AVAILABLE));
		//s.updateBookService(new Book(1,"Undying Affinity","Sara Naveed","Fiction",Status.INACTIVE,Availability.AVAILABLE));
		//s.updateBookAvailabilityService(6);
//		System.out.println("ALL books");
//		s.viewAllBooksService().stream()
//		.forEach(System.out::println);
		//s.addMemberService(new Member("surya","surya@gmail.com",9876543210L,Gender.MALE,"Stanza living kavuri hills"));
		//s.updateMemberService(new Member(1,"surya","surya@gmail.com",9896543210L,Gender.MALE,"Stanza living kavuri hills"));
//		List<Member> allMembers = s.getAllMembersService();
//		allMembers.stream()
//		.forEach(System.out::println);
		//s.issueBookService(new IssueRecord(1,1));
		s.issueBookService(new IssueRecord(2,2));
		//s.returnBookService(new IssueRecord(2,1));
		//s.getBooksCountPerCategory().forEach((a,v)->System.out.println(a+""+v));
		
		
	}

}

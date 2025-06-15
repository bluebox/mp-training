package com.LibraryManagement.controller;

import com.LibraryManagement.services.impl.IssueRecordServiceImpl;

public class Library {
	public static void main(String[] args) throws Exception {
//		  BookServiceImpl bs=new BookServiceImpl();
//		Book book = new Book("HHVM","Rakeshram","Action",'A','A');
//		bs.addBookService(book);
//		ArrayList<Book> arr=bs.viewAllBooksService();
//		bs.updateBookService(2,'I');
//		MemberServiceImpl ms=new MemberServiceImpl();
//		ms.updateMemberService(1,"Ramu","Ramtudu123",43729620l);
//		ArrayList<Member> arr=ms.viewAllMembersLogService();
//		System.out.println(arr.toString());
		IssueRecordServiceImpl ir=new IssueRecordServiceImpl();
		ir.issueBookToMember(1,1);
	}
}

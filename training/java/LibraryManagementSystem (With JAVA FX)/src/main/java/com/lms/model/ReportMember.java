package com.lms.model;

import java.util.List;
import java.util.stream.Collectors;

public class ReportMember {
	private int MemberId;
	private String Name;
	private List<Book> booksActive;
	private String booksActiveString;
	
	public ReportMember(int memberId, String name, List<Book> booksActive) {
		super();
		MemberId = memberId;
		Name = name;
		this.booksActive = booksActive;
		List<String> bookNames=booksActive.stream().map(b->b.getBook_Title()).collect(Collectors.toList());
		setBooksActiveString(bookNames.stream().collect(Collectors.joining(", ")));
	}

	public int getMemberId() {
		return MemberId;
	}

	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<Book> getBooksActive() {
		return booksActive;
	}

	public void setBooksActive(List<Book> booksActive) {
		this.booksActive = booksActive;
	}

	public String getBooksActiveString() {
		return booksActiveString;
	}

	public void setBooksActiveString(String booksActiveString) {
		this.booksActiveString = booksActiveString;
	}
	
	
}

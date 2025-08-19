package com.example.web.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportMember {
	private int MemberId;
	private String Name;
	private List<Book> booksActive;
	private String booksActiveString;
	
	public ReportMember(int memberId, String name, List<Book> booksActive) {
		MemberId = memberId;
		Name = name;
		this.booksActive = booksActive;
		List<String> bookNames=booksActive.stream().map(b->b.getBook_Title()).collect(Collectors.toList());
		setBooksActiveString(bookNames.stream().collect(Collectors.joining(", ")));
	}
}

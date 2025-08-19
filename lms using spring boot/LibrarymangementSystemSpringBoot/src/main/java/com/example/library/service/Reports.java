package com.example.library.service;

import java.util.List;
import java.util.Map;

import com.example.library.constants.BookCategory;
import com.example.library.domain.IssueRecord;
import com.example.library.domain.Member;

public interface Reports {

	List<IssueRecord> getOverdueBooks(int days);

	Map<BookCategory, Long> getBookCountPerCategory();

	List<Member> getMembersWithActiveIssuedBooks();

}

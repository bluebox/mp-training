package com.example.Backend.service;

import java.util.List;
import java.util.Map;

import com.example.Backend.constants.BookCategory;
import com.example.Backend.domain.IssueRecord;
import com.example.Backend.domain.Member;

public interface Reports {

	List<IssueRecord> getOverdueBooks(int days);

	Map<BookCategory, Long> getBookCountPerCategory();

	List<Member> getMembersWithActiveIssuedBooks();

}

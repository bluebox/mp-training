package com.lms.service;


import com.lms.model.Book;
import com.lms.model.Member;
import java.util.List;
import java.util.Map;

public interface ReportsService {
    List<Book> getOverdueBooks();
    Map<String, Long> getBookCountByCategory();
    List<Member> getMembersWithActiveIssues();
}

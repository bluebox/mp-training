package com.lms.springbootlms.dao;

import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.Member;

import java.util.List;
import java.util.Map;

public interface ReportDao {
    List<Book> getOverdueBooks();
    Map<String, Long> getBookCountByCategory();
    List<Member> getMembersWithActiveIssues();
}

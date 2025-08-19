package com.lms.dao;



import com.lms.model.Book;
import com.lms.model.Member;
import java.util.List;
import java.util.Map;

public interface ReportsDao {
    List<Book> findOverdueBooks();
    Map<String, Long> findBookCountByCategory();
    List<Member> findMembersWithActiveIssues();
}

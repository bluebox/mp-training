
package com.lms.service;

import com.lms.model.Book;
import com.lms.model.Member;

import java.util.List;
import java.util.Map;

public interface ReportServiceInterface {
    List<Book> fetchOverdueBooks();
    Map<String, Long> fetchBookCountByCategory();
    List<Member> fetchMembersWithActiveIssues();
}

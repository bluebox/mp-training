package com.lms.service;

import com.lms.model.IssueBook;
import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.model.BookCategory;

import java.util.List;

public interface IssueBookService {
    boolean issueBook(IssueBook issue) throws Exception;
    boolean returnBook(int issueId) throws Exception;
    List<IssueBook> getActiveIssuesByMember(int memberId) throws Exception;
    List<Book> getAvailableBooksByCategory(BookCategory category) throws Exception;
    Member getMemberByMobile(String mobile) throws Exception;
}

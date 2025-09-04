package com.vardhan.main.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.vardhan.main.model.Book;
import com.vardhan.main.model.IssueBook;
import com.vardhan.main.model.Member;

public interface ReportDao {
    
    List<IssueBook> getOverdueBooks() throws DataAccessException;
    Map<String, Long> getBookCountByCategory() throws DataAccessException;
    List<Book> getMostIssuedBooks(int limit) throws DataAccessException;
    List<Book> getAvailableBooksByCategory(String category) throws DataAccessException;
    
    List<Member> getMembersWithActiveIssues() throws DataAccessException;
    List<Member> getMembersWithOverdueBooks() throws DataAccessException;
    Map<Integer, Long> getMemberIssueCount() throws DataAccessException;
    
    List<IssueBook> getIssuesBetweenDates(LocalDate startDate, LocalDate endDate) throws DataAccessException;
    List<IssueBook> getReturnsBetweenDates(LocalDate startDate, LocalDate endDate) throws DataAccessException;
    long getTotalActiveIssues() throws DataAccessException;
    long getTotalOverdueIssues() throws DataAccessException;
    
    Map<String, Object> getDashboardStats() throws DataAccessException;
}

package com.vardhan.main.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.vardhan.main.model.Book;
import com.vardhan.main.model.IssueBook;
import com.vardhan.main.model.Member;

public interface ReportService {
    
    List<IssueBook> getOverdueBooks() throws DataAccessException;
    Map<String, Long> getBookCountByCategory() throws DataAccessException;
    List<Book> getMostIssuedBooks(int limit) throws DataAccessException;
    List<Book> getAvailableBooksByCategory(String category) throws DataAccessException;
    
    List<Member> getMembersWithActiveIssues() throws DataAccessException;
    List<Member> getMembersWithOverdueBooks() throws DataAccessException;
    Map<Integer, Long> getMemberIssueCount() throws DataAccessException;
    
    List<IssueBook> getIssuesInDateRange(LocalDate startDate, LocalDate endDate) throws DataAccessException;
    List<IssueBook> getReturnsInDateRange(LocalDate startDate, LocalDate endDate) throws DataAccessException;
    
    Map<String, Object> getDashboardStatistics() throws DataAccessException;
    Map<String, Object> getLibraryUtilizationReport() throws DataAccessException;
    
    byte[] generateOverdueBooksReport() throws DataAccessException;
    byte[] generateMemberActivityReport(LocalDate startDate, LocalDate endDate) throws DataAccessException;
}

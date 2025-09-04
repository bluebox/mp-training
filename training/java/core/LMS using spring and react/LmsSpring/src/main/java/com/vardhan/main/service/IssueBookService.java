package com.vardhan.main.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.vardhan.main.model.IssueBook;

public interface IssueBookService {
    
    IssueBook issueBook(IssueBook issueBook) throws DataAccessException;
    boolean returnBook(Integer issueId, LocalDate actualReturnDate) throws DataAccessException;
    
    Optional<IssueBook> findIssueById(Integer issueId) throws DataAccessException;
    List<IssueBook> getAllIssueRecords() throws DataAccessException;
    List<IssueBook> getActiveIssuesByMember(Integer memberId) throws DataAccessException;
    List<IssueBook> getOverdueBooks() throws DataAccessException;
    List<IssueBook> getIssueHistory(String bookId) throws DataAccessException;
    
    boolean isBookCurrentlyIssued(String bookId) throws DataAccessException;
    boolean canMemberIssueBook(Integer memberId, String bookId) throws DataAccessException;
    long getActiveIssueCountByMember(Integer memberId) throws DataAccessException;
    
    boolean validateIssueRequest(Integer memberId, String bookId) throws DataAccessException;
    LocalDate calculateDueDate(LocalDate issueDate) throws DataAccessException;
    
    long getTotalActiveIssues() throws DataAccessException;
    long getTotalOverdueIssues() throws DataAccessException;
}

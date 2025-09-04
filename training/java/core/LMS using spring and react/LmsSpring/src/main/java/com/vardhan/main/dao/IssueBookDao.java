package com.vardhan.main.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.vardhan.main.model.IssueBook;

public interface IssueBookDao {
    
    IssueBook save(IssueBook issueBook) throws DataAccessException;
    
    Optional<IssueBook> findById(Integer issueId) throws DataAccessException;
    List<IssueBook> findAll() throws DataAccessException;
    List<IssueBook> findByMemberId(Integer memberId) throws DataAccessException;
    List<IssueBook> findByBookId(String bookId) throws DataAccessException;
    List<IssueBook> findActiveIssuesByMember(Integer memberId) throws DataAccessException;
    List<IssueBook> findOverdueBooks() throws DataAccessException;
    List<IssueBook> findReturnedBooks() throws DataAccessException;
    List<IssueBook> findActiveIssues() throws DataAccessException;
    
    boolean returnBook(Integer issueId, LocalDate actualReturnDate) throws DataAccessException;
    IssueBook update(IssueBook issueBook) throws DataAccessException;
    
    boolean deleteById(Integer issueId) throws DataAccessException;
    
    boolean isBookCurrentlyIssued(String bookId) throws DataAccessException;
    boolean hasMemberIssuedBook(Integer memberId, String bookId) throws DataAccessException;
    long countActiveIssuesByMember(Integer memberId) throws DataAccessException;
    long countOverdueBooks() throws DataAccessException;
}

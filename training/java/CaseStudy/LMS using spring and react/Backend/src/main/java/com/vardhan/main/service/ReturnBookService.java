package com.vardhan.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

public interface ReturnBookService {
    
    Optional<String> getMemberNameByMobile(String mobile) throws DataAccessException;
    Optional<Integer> getMemberIdByMobile(String mobile) throws DataAccessException;
    
    List<String> getIssuedBooksByMobile(String mobile) throws DataAccessException;
    List<String> getActiveIssuesWithDueDates(String mobile) throws DataAccessException;
    boolean returnBookByMobileAndTitle(String mobile, String bookTitle, String status) throws DataAccessException;
    
    boolean isMemberExists(String mobile) throws DataAccessException;
    boolean isBookIssuedToMember(String mobile, String bookTitle) throws DataAccessException;
    
    int getActiveIssueCountByMobile(String mobile) throws DataAccessException;
    List<String> getOverdueBooksByMobile(String mobile) throws DataAccessException;
}

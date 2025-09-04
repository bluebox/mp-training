package com.vardhan.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.vardhan.main.daoimpl.ReturnBookDaoImpl;
import com.vardhan.main.service.ReturnBookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Validated
@Slf4j
public class ReturnBookServiceImpl implements ReturnBookService {

    @Autowired
    private ReturnBookDaoImpl returnBookDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<String> getMemberNameByMobile( String mobile) throws DataAccessException {
        log.debug("Fetching member name by mobile: {}", mobile);
        validateMobileNumber(mobile);
        return returnBookDao.fetchMemberName(mobile);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Integer> getMemberIdByMobile( String mobile) throws DataAccessException {
        log.debug("Fetching member ID by mobile: {}", mobile);
        validateMobileNumber(mobile);
        return returnBookDao.getMemberIdByMobile(mobile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getIssuedBooksByMobile( String mobile) throws DataAccessException {
        log.debug("Fetching issued books by mobile: {}", mobile);
        validateMobileNumber(mobile);
        return returnBookDao.fetchIssuedBooks(mobile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getActiveIssuesWithDueDates( String mobile) throws DataAccessException {
        log.debug("Fetching active issues with due dates for mobile: {}", mobile);
        validateMobileNumber(mobile);
        return returnBookDao.getActiveIssuesByMobile(mobile);
    }

    @Override
    public boolean returnBookByMobileAndTitle( String mobile,  String bookTitle, 
                                               String status) throws DataAccessException {
        log.info("Processing book return - Mobile: {}, Book: {}, Status: {}", mobile, bookTitle, status);
        
        validateMobileNumber(mobile);
        if (bookTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        
        if (!isMemberExists(mobile)) {
            throw new IllegalArgumentException("Member not found with mobile: " + mobile);
        }
        
        if (!isBookIssuedToMember(mobile, bookTitle)) {
            throw new IllegalArgumentException("Book is not issued to this member");
        }
        
        boolean returned = returnBookDao.updateBookReturnStatus(mobile, bookTitle, status);
        if (returned) {
            log.info("Successfully returned book: {} for member: {}", bookTitle, mobile);
        } else {
            log.warn("Failed to return book: {} for member: {}", bookTitle, mobile);
        }
        
        return returned;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isMemberExists( String mobile) throws DataAccessException {
        return getMemberNameByMobile(mobile).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isBookIssuedToMember( String mobile, String bookTitle) throws DataAccessException {
        return returnBookDao.isBookIssuedToMember(mobile, bookTitle);
    }

    @Override
    @Transactional(readOnly = true)
    public int getActiveIssueCountByMobile( String mobile) throws DataAccessException {
        List<String> activeIssues = getIssuedBooksByMobile(mobile);
        return activeIssues.size();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getOverdueBooksByMobile( String mobile) throws DataAccessException {
        log.warn("Overdue books check not fully implemented - returning all issued books");
        return getIssuedBooksByMobile(mobile);
    }

    private void validateMobileNumber(String mobile) {
        if (mobile == null || !mobile.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Invalid mobile number format: " + mobile);
        }
    }
}

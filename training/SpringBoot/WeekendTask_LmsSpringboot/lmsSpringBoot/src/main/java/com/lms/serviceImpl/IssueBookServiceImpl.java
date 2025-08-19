package com.lms.serviceImpl;

import com.lms.dao.IssueBookDao;
import com.lms.exceptions.DAOException;
import com.lms.model.IssueBook;
import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.model.BookCategory;
import com.lms.service.IssueBookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueBookServiceImpl implements IssueBookService {

    private final IssueBookDao issueBookDao;
    private static final Logger log = LoggerFactory.getLogger(IssueBookServiceImpl.class);

    @Override
    public boolean issueBook(IssueBook issue) {
    			if (issue == null) {
			log.warn("IssueBook object is null");
			return false;
		}
        try {
            log.info("Issuing book {} to member {}", issue.getBookId(), issue.getMemberId());
            return issueBookDao.issueBook(issue);
        } catch (DAOException e) {
            log.error("Error issuing book: {}", e.getMessage(), e);
            return false;
        } catch (Exception e) {
            log.error("Unexpected error issuing book: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean returnBook(int issueId) {
    			if (issueId <= 0) {
			log.warn("Invalid issue ID: {}", issueId);
			return false;
		}
    	
        try {
            log.info("Returning book for issue ID {}", issueId);
            return issueBookDao.returnBook(issueId);
        } catch (DAOException e) {
            log.error("Error returning book: {}", e.getMessage(), e);
            return false;
        } catch (Exception e) {
            log.error("Unexpected error returning book: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<IssueBook> getActiveIssuesByMember(int memberId) {
    			if (memberId <= 0) {
			log.warn("Invalid member ID: {}", memberId);
			return Collections.emptyList();
		}
        try {
            log.info("Fetching active issues for member {}", memberId);
            List<IssueBook> issues = issueBookDao.getActiveIssuesByMember(memberId);
            return issues != null ? issues : Collections.emptyList();
        } catch (DAOException e) {
            log.error("Error fetching active issues: {}", e.getMessage(), e);
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Unexpected error fetching active issues: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> getAvailableBooksByCategory(BookCategory category) {
    			if (category == null) {
			log.warn("Category is null");
			return Collections.emptyList();
		}
        try {
            log.info("Fetching available books for category {}", category);
            List<Book> books = issueBookDao.getAvailableBooksByCategory(category);
            return books != null ? books : Collections.emptyList();
        } catch (DAOException e) {
            log.error("Error fetching available books: {}", e.getMessage(), e);
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Unexpected error fetching available books: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public Member getMemberByMobile(String mobile) {
    	
    			if (mobile == null || mobile.isEmpty()) {
			log.warn("Mobile number is null or empty");
			return null;
		}
        try {
            log.info("Fetching member with mobile: {}", mobile);
            Member member = issueBookDao.getMemberByMobile(mobile);
            if (member == null) {
                log.warn("No member found with mobile: {}", mobile);
            }
            return member; 
        } catch (DAOException e) {
            log.error("Error fetching member by mobile: {}", e.getMessage(), e);
            return null;
        } catch (Exception e) {
            log.error("Unexpected error fetching member by mobile: {}", e.getMessage(), e);
            return null;
        }
    }
}

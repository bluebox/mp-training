package com.vardhan.main.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vardhan.main.dao.ReportDao;
import com.vardhan.main.model.Book;
import com.vardhan.main.model.IssueBook;
import com.vardhan.main.model.Member;
import com.vardhan.main.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public List<IssueBook> getOverdueBooks() throws DataAccessException {
        log.debug("Fetching overdue books report");
        return reportDao.getOverdueBooks();
    }

    @Override
    public Map<String, Long> getBookCountByCategory() throws DataAccessException {
        log.debug("Fetching book count by category report");
        return reportDao.getBookCountByCategory();
    }

    @Override
    public List<Book> getMostIssuedBooks(int limit) throws DataAccessException {
        log.debug("Fetching most issued books report with limit: {}", limit);
        return reportDao.getMostIssuedBooks(limit);
    }

    @Override
    public List<Book> getAvailableBooksByCategory(String category) throws DataAccessException {
        log.debug("Fetching available books by category: {}", category);
        return reportDao.getAvailableBooksByCategory(category);
    }

    @Override
    public List<Member> getMembersWithActiveIssues() throws DataAccessException {
        log.debug("Fetching members with active issues report");
        return reportDao.getMembersWithActiveIssues();
    }

    @Override
    public List<Member> getMembersWithOverdueBooks() throws DataAccessException {
        log.debug("Fetching members with overdue books report");
        return reportDao.getMembersWithOverdueBooks();
    }

    @Override
    public Map<Integer, Long> getMemberIssueCount() throws DataAccessException {
        log.debug("Fetching member issue count report");
        return reportDao.getMemberIssueCount();
    }

    @Override
    public List<IssueBook> getIssuesInDateRange(LocalDate startDate, LocalDate endDate) throws DataAccessException {
        log.debug("Fetching issues between {} and {}", startDate, endDate);
        return reportDao.getIssuesBetweenDates(startDate, endDate);
    }

    @Override
    public List<IssueBook> getReturnsInDateRange(LocalDate startDate, LocalDate endDate) throws DataAccessException {
        log.debug("Fetching returns between {} and {}", startDate, endDate);
        return reportDao.getReturnsBetweenDates(startDate, endDate);
    }

    @Override
    public Map<String, Object> getDashboardStatistics() throws DataAccessException {
        log.debug("Fetching dashboard statistics");
        return reportDao.getDashboardStats();
    }

    @Override
    public Map<String, Object> getLibraryUtilizationReport() throws DataAccessException {
        log.debug("Generating library utilization report");
        Map<String, Object> stats = reportDao.getDashboardStats();
        
        Long totalBooks = (Long) stats.get("totalBooks");
        Long availableBooks = (Long) stats.get("availableBooks");
        
        if (totalBooks > 0) {
            double utilizationRate = ((double) (totalBooks - availableBooks) / totalBooks) * 100;
            stats.put("utilizationRate", Math.round(utilizationRate * 100.0) / 100.0);
        } else {
            stats.put("utilizationRate", 0.0);
        }
        
        return stats;
    }

    @Override
    public byte[] generateOverdueBooksReport() throws DataAccessException {
        log.warn("Report generation not yet implemented");
        throw new UnsupportedOperationException("Report generation not yet implemented");
    }

    @Override
    public byte[] generateMemberActivityReport(LocalDate startDate, LocalDate endDate) throws DataAccessException {
        log.warn("Report generation not yet implemented");
        throw new UnsupportedOperationException("Report generation not yet implemented");
    }
}

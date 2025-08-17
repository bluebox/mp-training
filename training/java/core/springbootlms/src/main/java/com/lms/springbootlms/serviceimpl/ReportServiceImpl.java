package com.lms.springbootlms.serviceimpl;

import com.lms.springbootlms.dao.ReportDao;
import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.Member;
import com.lms.springbootlms.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportDao reportDao;

    @Autowired
    public ReportServiceImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public List<Book> fetchOverdueBooks() throws ServiceException {
        try {
            return reportDao.getOverdueBooks();
        } catch (DataAccessException e) {
            throw new ServiceException("Error fetching overdue books", e);
        }
    }

    @Override
    public Map<String, Long> fetchBookCountByCategory() throws ServiceException {
        try {
            return reportDao.getBookCountByCategory();
        } catch (DataAccessException e) {
            throw new ServiceException("Error fetching book count by category", e);
        }
    }

    @Override
    public List<Member> fetchMembersWithActiveIssues() throws ServiceException {
        try {
            return reportDao.getMembersWithActiveIssues();
        } catch (DataAccessException e) {
            throw new ServiceException("Error fetching members with active issues", e);
        }
    }
}

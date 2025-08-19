package com.lms.serviceImpl;

import com.lms.dao.ReportsDao;
import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.service.ReportsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportsServiceImpl implements ReportsService {

    private final ReportsDao reportsDao;

    public ReportsServiceImpl(ReportsDao reportsDao) {
        this.reportsDao = reportsDao;
    }

    @Override
    public List<Book> getOverdueBooks() {
        return reportsDao.findOverdueBooks();
    }

    @Override
    public Map<String, Long> getBookCountByCategory() {
        return reportsDao.findBookCountByCategory();
    }

    @Override
    public List<Member> getMembersWithActiveIssues() {
        return reportsDao.findMembersWithActiveIssues();
    }
}

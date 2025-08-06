package com.lms.serviceImpl;


import com.lms.dao.ReportDaoInterface;
import com.lms.daoImpl.ReportDao;
import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.service.ReportServiceInterface;

import java.util.List;
import java.util.Map;

public class ReportServiceImpl implements ReportServiceInterface {

    private final ReportDaoInterface reportDao;

    public ReportServiceImpl() {
        this.reportDao = ReportDao.getInstance();
    }

    @Override
    public List<Book> fetchOverdueBooks() {
        return reportDao.getOverdueBooks();
    }

    @Override
    public Map<String, Long> fetchBookCountByCategory() {
        return reportDao.getBookCountByCategory();
    }

    @Override
    public List<Member> fetchMembersWithActiveIssues() {
        return reportDao.getMembersWithActiveIssues();
    }
}

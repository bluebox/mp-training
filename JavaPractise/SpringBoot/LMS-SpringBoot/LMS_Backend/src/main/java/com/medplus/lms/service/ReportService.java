package com.medplus.lms.service;

import org.springframework.stereotype.Service;

import com.medplus.lms.dao.ReportDao;
import com.medplus.lms.dao.ReportDaoInterface;
import com.medplus.lms.domain.IssueRecordDto;
import com.medplus.lms.domain.MemberIssuedBookDto;
import com.medplus.lms.exceptions.ManagementException;

import java.util.List;
import java.util.Map;

@Service
public class ReportService implements ReportServiceInterface{

    private final ReportDaoInterface reportDao;

    public ReportService(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public List<IssueRecordDto> getOverdueBooks() {
        return reportDao.getOverdueBooks();
    }

    public Map<String, Long> getBooksCountPerCategory() throws ManagementException {
        return reportDao.getBooksCountPerCategory();
    }

    public List<MemberIssuedBookDto> getMembersWithActiveIssuedBooks() {
        return reportDao.getMembersWithActiveIssuedBooks();
    }
}

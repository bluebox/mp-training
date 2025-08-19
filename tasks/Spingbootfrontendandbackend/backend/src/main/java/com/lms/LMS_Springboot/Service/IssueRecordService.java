package com.lms.LMS_Springboot.Service;

import com.lms.LMS_Springboot.DAO.Issue_RecordDAO;
import com.lms.LMS_Springboot.Model.Issue_records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class IssueRecordService {

    @Autowired
    private Issue_RecordDAO issueRecordDAO;

    public boolean issueBook(int bookId, int memberId) {
        return issueRecordDAO.issueBook(bookId, memberId);
    }

    public boolean returnBook(int bookId, int memberId) {
        return issueRecordDAO.returnBook(bookId, memberId);
    }

    public List<Issue_records> getAllIssueRecords() throws SQLException {
        return issueRecordDAO.printAllIssueRecords();
    }

    public boolean isBookIssued(int bookId, int memberId) {
        return issueRecordDAO.isBookIssued(bookId, memberId);
    }
}

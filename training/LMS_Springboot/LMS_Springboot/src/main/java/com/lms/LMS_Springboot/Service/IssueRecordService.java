package com.lms.LMS_Springboot.Service;

import com.lms.LMS_Springboot.DAO.IssueRecordDAO;
import com.lms.LMS_Springboot.Model.IssueRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordDAO issueRecordDAO;

    public List<IssueRecord> getAllRecords() {
        return issueRecordDAO.getAllRecords();
    }

    public IssueRecord getRecordById(int id) {
        return issueRecordDAO.getRecordById(id);
    }

    public int issueBook(IssueRecord record) {
        return issueRecordDAO.issueBook(record);
    }

    public int returnBook(int id, LocalDate returnDate) {
        return issueRecordDAO.returnBook(id, returnDate);
    }
}

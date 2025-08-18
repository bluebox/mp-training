package com.lms.LMS_Springboot.Service;

import com.lms.LMS_Springboot.DAO.BookDAO;
import com.lms.LMS_Springboot.DAO.IssueRecordDAO;
import com.lms.LMS_Springboot.DAO.MemberDAO;
import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.IssueRecord;
import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Model.checking_enum.Status_issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private IssueRecordDAO issueRecordDAO;

    // 1. List of overdue books (e.g., >14 days not returned)
    public List<IssueRecord> getOverdueBooks(int days) {
        return issueRecordDAO.getAllRecords().stream()
                .filter(r -> r.getStatus() == Status_issue.ISSUED)
                .filter(r -> r.getIssueDate().isBefore(LocalDate.now().minusDays(days)))
                .collect(Collectors.toList());
    }

    // 2. Count of books per category
    public Map<String, Long> getBookCountPerCategory() {
        return bookDAO.getAllBooks().stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
    }

    // 3. Members with active issued books
    public List<Member> getMembersWithActiveIssuedBooks() {
        List<IssueRecord> activeIssues = issueRecordDAO.getAllRecords().stream()
                .filter(r -> r.getStatus() == Status_issue.ISSUED)
                .collect(Collectors.toList());

        List<Integer> memberIds = activeIssues.stream()
                .map(IssueRecord::getMemberId)
                .distinct()
                .collect(Collectors.toList());

        return memberDAO.getAllMembers().stream()
                .filter(m -> memberIds.contains(m.getMemberId()))
                .collect(Collectors.toList());
    }
}

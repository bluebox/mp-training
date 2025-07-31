package service;
import dao.ReportDao;
import dao.ReportDaoInterface;
import domain.IssueRecord;
import domain.Member;
import exceptions.ManagementException;

import java.util.List;
import java.util.Map;

public class ReportService implements ReportServiceInterface {

    private ReportDaoInterface dao = new ReportDao();

    public List<IssueRecord> getOverdueBooks() {
        return dao.getOverdueBooks();
    }

    public Map<String, Long> getBooksCountPerCategory() throws ManagementException {
        return dao.getBooksCountPerCategory();
    }

    public List<Member> getMembersWithActiveIssuedBooks() {
        return dao.getMembersWithActiveIssuedBooks();
    }
}


package service;
import dao.ReportDAO;
import domain.IssueRecord;
import domain.Member;
import javafx.util.Pair;

import java.util.List;

public class ReportService {

    private ReportDAO dao = new ReportDAO();

    public List<IssueRecord> getOverdueBooks() {
        return dao.getOverdueBooks();
    }

    public List<Pair<String, Long>> getBooksCountPerCategory() {
        return dao.getBooksCountPerCategory();
    }

    public List<Member> getMembersWithActiveIssuedBooks() {
        return dao.getMembersWithActiveIssuedBooks();
    }
}


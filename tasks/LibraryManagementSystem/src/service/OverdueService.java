package service;

import dao.OverdueDAO;
import model.IssueRecord;

import java.util.List;

public class OverdueService {
    private final OverdueDAO overdueDAO = new OverdueDAO();

    public List<IssueRecord> getOverdueRecords() throws Exception {
        return overdueDAO.getOverdueRecords();
    }
}


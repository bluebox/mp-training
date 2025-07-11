package com.loanmanagement.DAO;

import java.sql.SQLException;
import java.util.List;

import com.loanmanagement.model.LoanRequestDTO;
import com.loanmanagement.model.Payments;

public interface DataDAO {
public void issueLoan(LoanRequestDTO request) throws SQLException;
public List<LoanRequestDTO> getAllIssues() throws SQLException;
public List<LoanRequestDTO> getIssueById(int memberId) throws SQLException;
public List<LoanRequestDTO> getIssueByLoan(int id)throws SQLException;
public LoanRequestDTO getIssueByBoth(int loanId,int memberId )throws SQLException;
public void addLateFee(int personId, int loanId, double fee) throws SQLException;
public void updateEmiSummary(int personId, int loanId, double amountPaid, double outstandingBalance, String status,double principle,int tenure) throws SQLException ;
public void updateAfterPrinciple(double principle,double newEmi,int personId,int loanId) throws SQLException;
public void payAmount(Payments payment) throws SQLException;
public List<Payments>viewPayments(int memberId)throws SQLException;

}

package com.loanmanagement.service;

import java.util.List;

import com.loanmanagement.model.LoanRequestDTO;
import com.loanmanagement.model.Payments;

public interface DataService {
  public void issueLoan(LoanRequestDTO requestDTO) throws Exception;
  public String payAmount(Payments payment) throws Exception; 
  public List<LoanRequestDTO> getAllIssues() throws Exception;
  public List<LoanRequestDTO> getIssueById(int memberId) throws Exception;
  public List<LoanRequestDTO> getIssueByLoan(int id)throws Exception;
  public LoanRequestDTO getIssueByBoth(int loanId,int memberId )throws Exception;
  public String payForeClosure(int loanId,int memberId)throws Exception;
  public List<Payments>viewPayments(int memberId)throws Exception;
  
  //public void payEMI(Payments payment)throws Exception;
}

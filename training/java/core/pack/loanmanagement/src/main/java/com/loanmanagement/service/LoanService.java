package com.loanmanagement.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.loanmanagement.model.Loan;
import com.loanmanagement.model.LoanRequestDTO;
import com.loanmanagement.model.Payments;


public interface LoanService {

 public void addLoan(Loan loan)throws Exception;
 public Loan getLoanById(int id)throws Exception;
 public List<Loan>getAllLoans()throws Exception;
 public List<Loan>getLoanByType( String type) throws Exception;

 //public void issueLoan(LoanRequestDTO dto) throws Exception;
}

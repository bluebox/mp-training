package com.loanmanagement.DAO;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.loanmanagement.model.Loan;
import com.loanmanagement.model.LoanRequestDTO;


public interface LoanDAO {
	
	 public void addLoan(Loan loan)throws SQLException;
	 public Loan getLoanById(int id)throws SQLException;
	 public List<Loan>getAllLoans()throws SQLException;
	 public List<Loan>getLoanByType( String type) throws SQLException;
	 //public void issueLoan(LoanRequestDTO dto) throws SQLException;
}

package com.loanmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.loanmanagement.DAO.LoanDAO;
import com.loanmanagement.Exceptions.NoLoanException;
import com.loanmanagement.model.Loan;
import com.loanmanagement.model.LoanRequestDTO;
import com.loanmanagement.model.Payments;
import com.loanmanagement.util.UtilityMethods;

@Service
public class LoanServiceImplementation implements LoanService{

	private final LoanDAO loanDAO;
	
	
	@Autowired
	public LoanServiceImplementation(LoanDAO loanDAO){
		this.loanDAO=loanDAO;
	}
	
	@Override
	public void addLoan(Loan loan)throws Exception {
		if(loan==null)
			throw new NoLoanException("invalid loan");
		loanDAO.addLoan(loan);
		
	}
	@Override
	public Loan getLoanById(int id)throws Exception{
		Loan loan=loanDAO.getLoanById(id);
		if(loan==null)
			throw new NoLoanException("invalid loan");
		return loan;
	}
	@Override
	public List<Loan>getAllLoans()throws Exception{
		return loanDAO.getAllLoans();
		
	}
	@Override 
	public List<Loan>getLoanByType( String type) throws Exception{
		return loanDAO.getLoanByType(type);
	}
	

	

}

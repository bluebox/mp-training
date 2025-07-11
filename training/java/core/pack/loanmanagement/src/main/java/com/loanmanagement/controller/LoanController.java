package com.loanmanagement.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanmanagement.Exceptions.InvalidIdException;
import com.loanmanagement.Exceptions.NoLoanException;
import com.loanmanagement.model.Loan;
import com.loanmanagement.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

	@Autowired
	private LoanService loanServices;
	
	@PostMapping("/create-loan")
	public ResponseEntity<String> addLoan(@RequestBody Loan loan) throws Exception {
		if(loan==null)
			throw new NoLoanException("invalid loan");
		
		if (loan.getLoanType().trim().length()==0 ||loan.getLoanType().trim() == null ||(!loan.getLoanType().equals("car")&&!loan.getLoanType().equals("home")&&!loan.getLoanType().equals("personal"))) {
            return ResponseEntity.badRequest().body("Invalid loan type");
        }
		double principle=loan.getPrinciple();
		double intrest=loan.getRateOfIntrest();
		int tenure=loan.getTenureInDays();
		if(principle==0 || intrest==0 || tenure==0)
			return ResponseEntity.badRequest().body("invalid entries ");
		
		
		
		
		loanServices.addLoan(loan);
		return ResponseEntity.ok("New Loan added");
	}
	@GetMapping("/{id}")
	public Loan getLoanById(@PathVariable int id)throws Exception{
		if(id<=0)
			throw new InvalidIdException("Loan id cant be negative or zero");
		return loanServices.getLoanById(id);
	}
	@GetMapping("/type/{type}")
		public List<Loan>getLoanByType(@PathVariable String type) throws Exception{
			if(!type.toLowerCase().equals("car") && !type.toLowerCase().equals("home") && !type.toLowerCase().equals("personal"))
		   throw new NoLoanException("invalid loan");
			
			return loanServices.getLoanByType(type);
	}
	
	
	@GetMapping
	public List<Loan>getAllLoans()throws Exception{
		return loanServices.getAllLoans();
	}


	
}

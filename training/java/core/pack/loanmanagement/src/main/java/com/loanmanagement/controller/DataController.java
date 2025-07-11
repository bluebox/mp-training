package com.loanmanagement.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

import com.loanmanagement.Exceptions.GeneralException;
import com.loanmanagement.Exceptions.InvalidIdException;
import com.loanmanagement.Exceptions.InvalidPaymentException;
import com.loanmanagement.Exceptions.NoLoanException;
import com.loanmanagement.config.CreditScoreConfig;
import com.loanmanagement.model.LoanRequestDTO;
import com.loanmanagement.model.Payments;
import com.loanmanagement.service.DataService;

@RestController
@RequestMapping("/data")
public class DataController {

	@Autowired
	private DataService dataServices;
	
	@Autowired
	private CreditScoreConfig creditScoreConfig;

	@PostMapping("/issue-loan")
	public String issueLoan(@RequestBody LoanRequestDTO requestDTO) throws Exception {
		if (requestDTO == null)
			throw new NoLoanException("invalid loan request");
		if (requestDTO.getLoanId() <= 0 || requestDTO.getPersonId() <= 0)
			throw new InvalidIdException(" you have entered invalid id");
		if (requestDTO.getStartDate() == null)
			throw new GeneralException("Check the date you have entered");

		String startDateStr = requestDTO.getStartDate().toString();

		if (startDateStr == null || startDateStr.trim().isEmpty())
			throw new GeneralException("Start date is required and cannot be empty");

		LocalDate startDate;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			startDate = LocalDate.parse(startDateStr, formatter);
		} catch (DateTimeParseException e) {
			throw new GeneralException("Invalid date format. Use yyyy-MM-dd");
		}

		if (startDate.isBefore(LocalDate.now())) {
			throw new GeneralException("invalid date");
		}
		try {
		LoanRequestDTO ldto=dataServices.getIssueByBoth(requestDTO.getLoanId(),requestDTO.getPersonId());
		throw new GeneralException("this loan is already issued");
		}
		catch(NoLoanException e) {
			dataServices.issueLoan(requestDTO);
			return "Loan issued Successfully";
		}
		
        
		
	}

	@GetMapping("/member/{id}")
	public List<LoanRequestDTO> getIssueById(@PathVariable int id) throws Exception {
		if (id <= 0)
			throw new InvalidIdException("Member id cant be negative or zero");
		return dataServices.getIssueById(id);
	}

	@GetMapping("/loan/{id}")
	public List<LoanRequestDTO> getIssueByLoan(@PathVariable int id) throws Exception {
		if (id <= 0)
			throw new InvalidIdException("Loan id cant be negative or zero");

		return dataServices.getIssueByLoan(id);
	}

	@GetMapping("/loan/{id}/{memberId}")
	public LoanRequestDTO getIssueByBoth(@PathVariable("id") int loanId, @PathVariable("memberId") int memberId)
			throws Exception {
		if (loanId <= 0 || memberId <= 0)
			throw new InvalidIdException(" id cant be negative or zero");

		return dataServices.getIssueByBoth(loanId, memberId);
	}

	@GetMapping
	public List<LoanRequestDTO> getAllIssues() throws Exception {
		return dataServices.getAllIssues();
	}

	@PostMapping("/payments")
	public ResponseEntity<String> payAmount(@RequestBody Payments payment) throws Exception {
		if (payment == null)
			throw new InvalidPaymentException("invalid payment");

		if (!payment.getType().equals("emi") && !payment.getType().equals("principle")
				&& !payment.getType().equals("foreclosure")) {
			return ResponseEntity.badRequest().body("invalid payment type");
		}
		
		if (payment.getAmountPaid() < 0)
			return ResponseEntity.badRequest().body("invalid amount for the payment");
		
		

		return ResponseEntity.ok(dataServices.payAmount(payment));

	}

	@GetMapping("/view-payments/{memberId}")
	public List<Payments> viewPayments(@PathVariable int memberId) throws Exception {
		if (memberId <= 0)
			throw new InvalidIdException("Loan id cant be negative or zero");

		return dataServices.viewPayments(memberId);
	}

	@GetMapping("/foreclosure/{loanId}/{memberId}")
	public String foreClosure(@PathVariable("loanId") int loanId, @PathVariable("memberId") int memberId)
			throws Exception {
		if (loanId <= 0 || memberId <= 0)
			throw new InvalidIdException("Loan id cant be negative or zero");
		return dataServices.payForeClosure(loanId, memberId);
	}

}

package com.loanmanagement.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanmanagement.DAO.DataDAO;
import com.loanmanagement.Exceptions.GeneralException;
import com.loanmanagement.Exceptions.InvalidPaymentException;
import com.loanmanagement.Exceptions.NoLoanException;
import com.loanmanagement.config.CreditScoreConfig;
import com.loanmanagement.model.Loan;
import com.loanmanagement.model.LoanRequestDTO;
import com.loanmanagement.model.Member;
import com.loanmanagement.model.Payments;
import com.loanmanagement.util.UtilityMethods;

@Service
public class DataServiceImplementation implements DataService {

	private final DataDAO dataDAO;
	private final LoanService loanService;
	private final MemberService memberService;
    private final CreditScoreConfig creditScoreConfig;
	@Autowired
	DataServiceImplementation(DataDAO dataDAO, LoanService loanService, MemberService memberService,CreditScoreConfig creditScoreConfig) {
		this.dataDAO = dataDAO;
		this.loanService = loanService;
		this.memberService = memberService;
		this.creditScoreConfig=creditScoreConfig;
	}

	@Override
	public void issueLoan(LoanRequestDTO request) throws Exception {

		if (request == null)
			throw new NoLoanException("invalid request");

		Loan loan = loanService.getLoanById(request.getLoanId());
		Member member = memberService.getMemberById(request.getPersonId());
		int creditScore=member.getCreditScore();
		

		if (loan == null)
			throw new NoLoanException("invalid loan");

		request.setLoanType(loan.getLoanType());
		request.setPrinciple(loan.getPrinciple());
		request.setRate(loan.getRateOfIntrest());
		request.setTenureInDays(loan.getTenureInDays());

		double emi = UtilityMethods.calculateEMI(request.getPrinciple(), request.getRate(), request.getTenureInDays());
		double total = UtilityMethods.totalPayement(request.getPrinciple(), request.getRate(),
				request.getTenureInDays());
		Date dueDate = UtilityMethods.calculateDueDate(request.getStartDate(), request.getTenureInDays());

		request.setEmi(emi);
		request.setAmountPaid(0.0);
		request.setOutstandingBalance(total);
		request.setLateFee(0.0);
		request.setStatus("Activated");
		request.setDueDate(dueDate);
		request.setPrinciplePortion(request.getPrinciple() / request.getTenureInDays());
		checkLoanEligibility(creditScore,request.getPrinciple());
		dataDAO.issueLoan(request);

	}

	@Override
	public String payAmount(Payments payment) throws Exception {
          
		LoanRequestDTO req = dataDAO.getIssueByBoth(payment.getLoanId(), payment.getMemberId());
		if(req.getStatus().equals("closed"))
			throw new InvalidPaymentException("this loan is paid,invalid payment");
		
		int personId = payment.getMemberId();
		Member member=memberService.getMemberById(personId);
		
		int loanId = payment.getLoanId();
		double payingEmi = payment.getAmountPaid();
		
		int tenure=req.getTenureInDays();
		double principle = req.getPrinciple();
		double principlePortion = principle / tenure;
		double actualEmi = req.getEmi();

		double paidAmount = req.getAmountPaid();
		double outStandingBalance = req.getOutstandingBalance();
		double lateFee = req.getLateFee();
		String status;

		if (payment.getType().toLowerCase().equals("emi")) {
                
		
			if (actualEmi == payingEmi) {
				paidAmount += actualEmi;
				outStandingBalance -= actualEmi;
				principle -= principlePortion * payingEmi / actualEmi;
				status = outStandingBalance > 0 ? "Emi paid" : "closed";
				outStandingBalance=(outStandingBalance<0)? 0:outStandingBalance;
				principle=(principle<0)?0:principle;
                tenure=(tenure==0)? 0:tenure-1;
				makePayment(payment);
				
				
		dataDAO.updateEmiSummary(personId, loanId, paidAmount, outStandingBalance, status, principle,tenure);
		
		return "emi paid";
			} else if (actualEmi < payingEmi) {
				
				double extraAmount = payingEmi - actualEmi;

				payment.setAmountPaid(actualEmi);

				paidAmount += actualEmi;
				outStandingBalance -= actualEmi;
				principle -= principlePortion * payingEmi / actualEmi;
				status = outStandingBalance > 0 ? "Emi paid" : "closed";
				outStandingBalance=(outStandingBalance<0)? 0:outStandingBalance;
				principle=(principle<0)?0:principle;
				tenure=(tenure==0)? 0:tenure-1;
				makePayment(payment);
				
				dataDAO.updateEmiSummary(personId, loanId, paidAmount, outStandingBalance, status, principle,tenure);

				if (extraAmount > 0) {
					payment.setAmountPaid(extraAmount);
					payment.setType("principle");
					req = dataDAO.getIssueByBoth(payment.getLoanId(), payment.getMemberId());
					payPrinciple(payment, req);
				}
				return "principle+emi paid";

			} else {
				
			    
				paidAmount += payingEmi;
				outStandingBalance -= payingEmi;
				
				status= (payingEmi==0)? "Skipped emi":"partial emi";
		
				payment.setType(status);
				makePayment(payment);

//                 principle -= principlePortion* payingEmi / actualEmi;
				double amountShortBy = actualEmi - payingEmi;
				lateFee+= amountShortBy + actualEmi * 0.02;
				outStandingBalance+=lateFee;
				tenure=(tenure==0)? 0:tenure-1;
				dataDAO.updateEmiSummary(personId, loanId, paidAmount, outStandingBalance, status, principle,tenure);
				dataDAO.addLateFee(personId, loanId, lateFee);
                return status;
			}

		} else if (payment.getType().toLowerCase().equals("principle")) {
			payPrinciple(payment, req);
			return "payment done";

		} else if (payment.getType().toLowerCase().equals("foreclosure")) {

			double balance = req.getPrinciple() * 0.2 + req.getOutstandingBalance() + req.getLateFee();
			double amount = payment.getAmountPaid();
			if (balance == amount) {
				makePayment(payment);
				principle = 0;
				paidAmount += amount;
				lateFee = 0;
				status = "loan completed";
				outStandingBalance = 0;
				tenure=0;
				dataDAO.updateEmiSummary(personId, loanId, paidAmount, outStandingBalance, status, principle,tenure);
				dataDAO.addLateFee(personId, loanId, lateFee);
				return " foreclosure payment done ";

			}
			return "insufficent funds to foreclosure";
		}
		return "unable to make payment";
	}
	
	public void checkLoanEligibility(int score,double amount) {
		if(amount<=0)
			throw new InvalidPaymentException("invalid amount");
		
		if(score>=700 && score<800) {
			if(amount>=creditScoreConfig.getHighLoan())
				throw new InvalidPaymentException("Your credit score not eligible for this loan");
		}
		
		if(score>650 && score<=700)
			if(amount>=creditScoreConfig.getMediumLoan())
				throw new InvalidPaymentException("Your credit score not eligible for this loan");
		
		if(score<650)
			throw new InvalidPaymentException("Your credit score not eligible for this loan");
		
	}
	

	@Override
	public List<LoanRequestDTO> getIssueById(int memberId) throws Exception {

		return dataDAO.getIssueById(memberId);

	}

	@Override
	public LoanRequestDTO getIssueByBoth(int loanId, int memberId) throws Exception {
		return dataDAO.getIssueByBoth(loanId, memberId);
	}

	@Override
	public List<LoanRequestDTO> getIssueByLoan(int id) throws Exception {
		return dataDAO.getIssueByLoan(id);
	}

	@Override
	public List<LoanRequestDTO> getAllIssues() throws Exception {
		return dataDAO.getAllIssues();
	}

	public String payPrinciple(Payments payment, LoanRequestDTO req) throws Exception {
		double payingAmount = payment.getAmountPaid();

		double AmountToBePaid = req.getOutstandingBalance();
		double principle = req.getPrinciple();
		int tenure=req.getTenureInDays();
		//int remainingTenure = req.getTenureInDays() - GetDays.betweenDays(req.getStartDate());
		Double amountPaid = req.getAmountPaid();
		Double extraAmount = payingAmount - principle;

		amountPaid += payingAmount;
		principle -= payingAmount;
		AmountToBePaid -= payingAmount;
		
		
		double newEmi = UtilityMethods.calculateEMI(principle, req.getRate(), tenure);
		makePayment(payment);
		String status=(AmountToBePaid>0)?"emi+principle":"closed";
		AmountToBePaid=(AmountToBePaid<0)? 0:AmountToBePaid;
		principle=(principle<0)?0:principle;
		newEmi=(newEmi<0)?0:newEmi;
		dataDAO.updateEmiSummary(req.getPersonId(), req.getLoanId(), amountPaid, AmountToBePaid,status,
				principle,tenure);
		dataDAO.updateAfterPrinciple(principle, newEmi, req.getPersonId(), req.getLoanId());

		return "payment done";

	}

	@Override
	public String payForeClosure(int loanId, int memberId) throws Exception {
		LoanRequestDTO req = dataDAO.getIssueByBoth(loanId, memberId);
		double balance = req.getPrinciple() * 0.2 + req.getOutstandingBalance() + req.getLateFee();
		return "pay the " + balance + " Rs for forclosuring";

	}

	@Override
	public List<Payments> viewPayments(int memberId) throws Exception {
		return dataDAO.viewPayments(memberId);
	}

	public void makePayment(Payments payment) throws Exception {
		Member member = memberService.getMemberById(payment.getMemberId());
		LoanRequestDTO req = dataDAO.getIssueByBoth(payment.getLoanId(),payment.getMemberId());
		payment.setMemberName(member.getName());
		
		if(req.getOutstandingBalance()<=0) {
			payment.setType(payment.getType()+" closing account");
			dataDAO.payAmount(payment);
			throw new GeneralException(" loan  is completed");
		}
		dataDAO.payAmount(payment);

	}
}

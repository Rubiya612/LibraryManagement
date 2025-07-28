package controller;

import dao.LoanDAO;
import model.Loan;
import java.util.List;

public class LoanController {
	
	private LoanDAO loanDAO;
	
	public LoanController() {
		this.loanDAO = new LoanDAO();
	}
	
	//Add loan
	public boolean addLoan(Loan loan) {
		return loanDAO.addLoan(loan);
	}
	
	//Get all loans
	public List<Loan> getAllLoans() {
		return loanDAO.getAllLoans();
	}
	
	//Return a book
	public boolean returnBook(int loanId, String returnDate) {
		return loanDAO.returnBook(loanId, returnDate);
	}
	
	//Get overdue loans
	public List<Loan> getOverdueLoans() {
		return loanDAO.getOverdueLoans();
	}

}

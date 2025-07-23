package model;

public class Loan {
	private int loanId;
	private int copyId;
	private int memberId;
	private String loanDate;
	private String dueDate;
	private String returnDate;

	public Loan(int loanId, int copyId, int memberId, String loanDate, String dueDate, String returnDate) {
		this.loanId = loanId;
		this.copyId = copyId;
		this.memberId = memberId;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}
	
	public Loan() {
	}
	
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public int getCopyId() {
		return copyId;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
}

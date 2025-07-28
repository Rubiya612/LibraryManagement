package dao;

import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Loan;

public class LoanDAO {

	//Add new loan
	public boolean addLoan(Loan loan) {
		String sql = "INSERT INTO LMS_LOANS (COPY_ID, MEMBER_ID, LOAN_DATE, DUE_DATE, RETURN_DATE) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, loan.getCopyId());
			stmt.setInt(2, loan.getMemberId());
			stmt.setString(3, loan.getLoanDate());
			stmt.setString(4, loan.getDueDate());
			stmt.setString(5, loan.getReturnDate());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Get all loans
	public List<Loan> getAllLoans() {
		List<Loan> loans = new ArrayList<>();
		String sql = "SELECT * FROM LMS_LOANS";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Loan loan = new Loan();
				loan.setLoanId(rs.getInt("LOAN_ID"));
				loan.setCopyId(rs.getInt("COPY_ID"));
				loan.setMemberId(rs.getInt("MEMBER_ID"));
				loan.setLoanDate(rs.getString("LOAN_DATE"));
				loan.setDueDate(rs.getString("DUE_DATE"));
				loan.setReturnDate(rs.getString("RETURN_DATE"));
				loans.add(loan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loans;
	}
	
	//Return a book
	public boolean returnBook(int loanId, String returnDate) {
		String sql = "UPDATE LMS_LOANS SET RETURN_DATE = ? WHERE LOAN_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, returnDate);
			stmt.setInt(2, loanId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Get overdue loans
	public List<Loan> getOverdueLoans() {
		List<Loan> overdueLoans = new ArrayList<>();
		String sql = "SELECT * FROM LMS_LOANS WHERE DUE_DATE < SYSDATE AND RETURN_DATE IS NULL";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Loan loan = new Loan();
				loan.setLoanId(rs.getInt("LOAN_ID"));
				loan.setCopyId(rs.getInt("COPY_ID"));
				loan.setMemberId(rs.getInt("MEMBER_ID"));
				loan.setLoanDate(rs.getString("LOAN_DATE"));
				loan.setDueDate(rs.getString("DUE_DATE"));
				loan.setReturnDate(rs.getString("RETURN_DATE"));
				overdueLoans.add(loan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return overdueLoans;
	}

}


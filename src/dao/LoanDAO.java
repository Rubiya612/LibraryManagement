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
	public void addLoan(Loan loan) {
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
	
	//Get loan by ID
	public List<Loan> getLoanById(int loanId) {
		List<Loan> loans = new ArrayList<>();
		String sql = "SELECT * FROM LMS_LOANS WHERE LOAN_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, loanId);
			ResultSet rs = stmt.executeQuery();
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
	
	//Update loan
	public void updateLoan(Loan loan) {
		String sql = "UPDATE LMS_LOANS SET COPY_ID = ?, MEMBER_ID = ?, LOAN_DATE = ?, DUE_DATE = ?, RETURN_DATE = ? WHERE LOAN_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, loan.getCopyId());
			stmt.setInt(2, loan.getMemberId());
			stmt.setString(3, loan.getLoanDate());
			stmt.setString(4, loan.getDueDate());
			stmt.setString(5, loan.getReturnDate());
			stmt.setInt(6, loan.getLoanId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Delete loan
	public void deleteLoan(int loanId) {
		String sql = "DELETE FROM LMS_LOANS WHERE LOAN_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, loanId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


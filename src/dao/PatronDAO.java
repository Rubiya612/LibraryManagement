package dao;

import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Patron;

public class PatronDAO {
	
	//Add a new member
	public void addPatron(Patron patron) {
		String sql = "Insert into LMS_MEMBERS (FULL_NAME, MEMBER_TYPE, JOIN_DATE, EMAIL) values (?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, patron.getFullName());
			stmt.setString(2, patron.getMemberType());
			stmt.setString(3, patron.getJoinDate());
			stmt.setString(4, patron.getEmail());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Get all members
	public List<Patron> getAllPatrons() {
		List<Patron> patrons = new ArrayList<>();
		String sql = "SELECT * FROM LMS_MEMBERS";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Patron patron = new Patron();
				patron.setMemberId(rs.getInt("MEMBER_ID"));
				patron.setFullName(rs.getString("FULL_NAME"));
				patron.setMemberType(rs.getString("MEMBER_TYPE"));
				patron.setJoinDate(rs.getString("JOIN_DATE"));
				patron.setEmail(rs.getString("EMAIL"));
				patrons.add(patron);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patrons;
	}
	//Rubiya
	public static int getTotalPatrons() {
	    String sql = "SELECT COUNT(*) FROM LMS_MEMBERS";
	    try (Connection conn = DBConnection.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        if (rs.next()) return rs.getInt(1);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}


	//Get a member by ID
	public Patron getPatronById(int memberId) {
		String sql = "SELECT * FROM LMS_MEMBERS WHERE MEMBER_ID = ?";
		Patron patron= null;
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, memberId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				patron = new Patron();
				patron.setMemberId(rs.getInt("MEMBER_ID"));
				patron.setFullName(rs.getString("FULL_NAME"));
				patron.setMemberType(rs.getString("MEMBER_TYPE"));
				patron.setJoinDate(rs.getString("JOIN_DATE"));
				patron.setEmail(rs.getString("EMAIL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patron;
	}
	
	//Update a member
	public void updatePatron(Patron patron) {
		String sql = "UPDATE LMS_MEMBERS SET FULL_NAME = ?, MEMBER_TYPE = ?, JOIN_DATE = ?, EMAIL = ? WHERE MEMBER_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, patron.getFullName());
			stmt.setString(2, patron.getMemberType());
			stmt.setString(3, patron.getJoinDate());
			stmt.setString(4, patron.getEmail());
			stmt.setInt(5, patron.getMemberId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Delete a member
	public void deletePatron(int memberId) {
		String sql = "DELETE FROM LMS_MEMBERS WHERE MEMBER_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, memberId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Search members by name or memberId
	public List<Patron> searchPatrons(String keyword) {
		List<Patron> patrons = new ArrayList<>();
		String sql = "SELECT * FROM LMS_MEMBERS WHERE FULL_NAME LIKE ? OR MEMBER_ID = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, "%" + keyword + "%");
			stmt.setInt(2, Integer.parseInt(keyword));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Patron patron = new Patron();
				patron.setMemberId(rs.getInt("MEMBER_ID"));
				patron.setFullName(rs.getString("FULL_NAME"));
				patron.setMemberType(rs.getString("MEMBER_TYPE"));
				patron.setJoinDate(rs.getString("JOIN_DATE"));
				patron.setEmail(rs.getString("EMAIL"));
				patrons.add(patron);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patrons;
	}
}

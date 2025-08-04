package controller;

import dao.PatronDAO;
import model.Patron;
import java.util.List;

public class PatronController {
	
	private PatronDAO patronDAO;

	public PatronController() {
		this.patronDAO = new PatronDAO();
	}

	// Add new member
	public void addPatron(Patron patron) {
		patronDAO.addPatron(patron);
	}

	// Get all members
	public List<Patron> getAllPatrons() {
		return patronDAO.getAllPatrons();
	}

	
	
	
	
	// Update member
	public void updatePatron(Patron patron) {
		patronDAO.updatePatron(patron);
	}
	
	//Delete member
	public void deletePatron(int memberId) {
		patronDAO.deletePatron(memberId);
	}
	
	//Get member by name or memberId
	public List<Patron> searchPatrons(String keyword) {
		return patronDAO.searchPatrons(keyword);
	}
	
}

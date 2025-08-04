package controller;



import dao.BookDAO;
import dao.LoanDAO;
import dao.PatronDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.SceneManager;

public class HomeController {

    @FXML
    private Label totalBooksLabel;

    @FXML
    private Label totalPatronsLabel;

    @FXML
    private Label currentLoansLabel;

    @FXML
    private Label overdueLoansLabel;

    /**
     * This method is called automatically after the FXML file is loaded.
     * Use it to initialize your dashboard data.
     */

    @FXML
    public void initialize() {
        // Fetch counts from the DAOs
        int totalBooks = BookDAO.getTotalBooks();
        int totalPatrons = PatronDAO.getTotalPatrons();
        int currentLoans = LoanDAO.getCurrentLoanCount();
        int overdueLoans = LoanDAO.getOverdueLoanCount();

        // Update labels
        totalBooksLabel.setText("Total Books: " + totalBooks);
        totalPatronsLabel.setText("Total Patrons: " + totalPatrons);
        currentLoansLabel.setText("Current Loans: " + currentLoans);
        overdueLoansLabel.setText("Overdue Loans: " + overdueLoans);
    }

    // Navigation button handlers

    @FXML
    private void goHome() {
        // Already on Home, do nothing or refresh if you want
    }

    @FXML
    private void goToBooks() {
        SceneManager.switchScene("Books.fxml");
    }

    @FXML
    private void goToPatrons() {
        SceneManager.switchScene("PatronView.fxml");
    }

    @FXML
    private void goToLoans() {
        SceneManager.switchScene("LoanForm.fxml");
    }
    
    @FXML
    private void goToLoans2() {
        SceneManager.switchScene("LoanList.fxml");
    }

    @FXML
    private void goToReports() {
        SceneManager.switchScene("OverdueReport.fxml");
    }

    @FXML
    private void goToSettings() {
        SceneManager.switchScene("Settings.fxml");
    }
    
    
}

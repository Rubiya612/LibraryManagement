package controller;



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
        // TODO: Replace these hardcoded values with real data from your DAO classes
        totalBooksLabel.setText("1200");
        totalPatronsLabel.setText("350");
        currentLoansLabel.setText("220");
        overdueLoansLabel.setText("15");
    }

    // Navigation button handlers

    @FXML
    private void goHome() {
        // Already on Home, do nothing or refresh if you want
    }

    @FXML
    private void goToBooks() {
        SceneManager.switchScene("BookList.fxml");
    }

    @FXML
    private void goToPatrons() {
        SceneManager.switchScene("PatronList.fxml");
    }

    @FXML
    private void goToLoans() {
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

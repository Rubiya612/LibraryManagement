package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Loan;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dao.LoanDAO;

public class LoanFormController {

    @FXML private TextField copyIdField;
    @FXML private TextField memberIdField;
    @FXML private TextField loanDatePicker;     // it's a TextField
    @FXML private TextField dueDatePickerr;     // it's a TextField

    @FXML private Label statusLabel;
    Loan loan;

    private LoanController loanController;

    public void initialize() {
        loanController = new LoanController();

        // Set today's date and +14 days by default as text
        loanDatePicker.setText(LocalDate.now().toString());
        dueDatePickerr.setText(LocalDate.now().plusDays(14).toString());
    }

  

    @FXML
    private void handleSubmitLoan() {
        Loan loan = new Loan();

        loan.setCopyId(Integer.parseInt(copyIdField.getText()));
        loan.setMemberId(Integer.parseInt(memberIdField.getText()));
        loan.setLoanDate(loanDatePicker.getText()); // format: "YYYY-MM-DD"
        loan.setDueDate(dueDatePickerr.getText());
        loan.setReturnDate(null); // Not returned yet

        LoanDAO dao = new LoanDAO();
        boolean success = dao.addLoan(loan);

        if (success) {
            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setText("Loan submitted successfully!");
            clearForm();
        } else {
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setText("Failed to submit loan.");
        }
    }

    @FXML
    private void handleHome() {
        try {
            Parent home = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
            // Use any existing node (like statusLabel) to get the window
            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(new Scene(home));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        copyIdField.clear();
        memberIdField.clear();
        loanDatePicker.setText(LocalDate.now().toString());
        dueDatePickerr.setText(LocalDate.now().plusDays(14).toString());
    }
}


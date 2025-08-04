package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class ReturnFormController {

    @FXML private TextField loanIdField;
    @FXML private DatePicker returnDatePicker;
    @FXML private Label statusLabel;

    private LoanController loanController;

    public void initialize() {
        loanController = new LoanController();
        returnDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleReturn() {
        try {
            int loanId = Integer.parseInt(loanIdField.getText().trim());
            String returnDate = returnDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);

            boolean success = loanController.returnBook(loanId, returnDate);
            if (success) {
                statusLabel.setStyle("-fx-text-fill: green;");
                statusLabel.setText("Book returned successfully!");
                clearForm();
            } else {
                statusLabel.setText("Failed to return book.");
            }
        } catch (Exception e) {
            statusLabel.setText("Invalid input: " + e.getMessage());
        }
    }

    private void clearForm() {
        loanIdField.clear();
        returnDatePicker.setValue(LocalDate.now());
    }
}

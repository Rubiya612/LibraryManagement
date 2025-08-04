package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Loan;

import java.io.IOException;
import java.lang.classfile.components.ClassPrinter.Node;
import java.util.List;

public class LoanListController {

    @FXML private TableView<Loan> loanTable;
    @FXML private TableColumn<Loan, Integer> idCol;
    @FXML private TableColumn<Loan, Integer> copyIdCol;
    @FXML private TableColumn<Loan, Integer> memberIdCol;
    @FXML private TableColumn<Loan, String> loanDateCol;
    @FXML private TableColumn<Loan, String> dueDateCol;
    @FXML private TableColumn<Loan, String> returnDateCol;
    @FXML private TextField searchField;

    private LoanController loanController;

    public void initialize() {
        loanController = new LoanController();

        // Set up column bindings
        idCol.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        copyIdCol.setCellValueFactory(new PropertyValueFactory<>("copyId"));
        memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        loanDateCol.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        // Load data
        loadLoanData();
    }

    private void loadLoanData() {
        List<Loan> loans = loanController.getAllLoans();
        ObservableList<Loan> loanList = FXCollections.observableArrayList(loans);
        loanTable.setItems(loanList);
    }
    
    @FXML
    public void goToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) loanTable.getScene().getWindow();  // safer than searchField
            stage.setScene(new Scene(root));
            stage.setTitle("Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


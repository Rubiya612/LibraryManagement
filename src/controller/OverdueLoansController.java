package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Loan;

import java.util.List;

public class OverdueLoansController {

    @FXML private TableView<Loan> overdueTable;
    @FXML private TableColumn<Loan, Integer> loanIdCol;
    @FXML private TableColumn<Loan, Integer> copyIdCol;
    @FXML private TableColumn<Loan, Integer> memberIdCol;
    @FXML private TableColumn<Loan, String> loanDateCol;
    @FXML private TableColumn<Loan, String> dueDateCol;

    private LoanController loanController;

    public void initialize() {
        loanController = new LoanController();

        loanIdCol.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        copyIdCol.setCellValueFactory(new PropertyValueFactory<>("copyId"));
        memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        loanDateCol.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        loadOverdueData();
    }

    private void loadOverdueData() {
        List<Loan> overdueLoans = loanController.getOverdueLoans();
        ObservableList<Loan> data = FXCollections.observableArrayList(overdueLoans);
        overdueTable.setItems(data);
    }
}

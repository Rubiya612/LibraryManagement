package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Patron;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class PatronViewController {

    @FXML private TextField searchField;
    @FXML private TableView<Patron> patronTable;
    @FXML private TableColumn<Patron, Integer> idCol;
    @FXML private TableColumn<Patron, String> nameCol;
    @FXML private TableColumn<Patron, String> typeCol;
    @FXML private TableColumn<Patron, String> joinCol;
    @FXML private TableColumn<Patron, String> emailCol;

    private final ObservableList<Patron> patronList = FXCollections.observableArrayList();
    private final PatronController patronController = new PatronController();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("memberType"));
        joinCol.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        
    
       

        loadPatrons();
    }

    private void loadPatrons() {
        List<Patron> patrons = patronController.getAllPatrons();
        patronList.setAll(patrons);
        patronTable.setItems(patronList);
    }

    @FXML
    private void handleAddPatron() {
        showPatronForm(null, "Add New Patron");
    }

    @FXML
    private void handleEditPatron() {
        Patron selected = patronTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showPatronForm(selected, "Edit Patron");
        } else {
            showAlert("No Patron Selected", "Please select a patron to edit.");
        }
    }

    private void showPatronForm(Patron patron, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PatronForm.fxml"));
            Parent root = loader.load();

            PatronFormController controller = loader.getController();
            Stage stage = new Stage();
            controller.setDialogStage(stage);

            if (patron != null) controller.setPatron(patron);

            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            if (controller.isSaved()) loadPatrons();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeletePatron() {
        Patron selected = patronTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Deletion");
            confirm.setHeaderText("Are you sure you want to delete this patron?");
            confirm.setContentText(selected.getFullName());

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                patronController.deletePatron(selected.getMemberId());
                loadPatrons();
            }
        } else {
            showAlert("No Patron Selected", "Please select a patron to delete.");
        }
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().trim();
        if (!keyword.isEmpty()) {
            patronList.setAll(patronController.searchPatrons(keyword));
        } else {
            loadPatrons();
        }
    }

    @FXML
    private void handleClearSearch() {
        searchField.clear();
        loadPatrons();
    }

    @FXML
    private void handleHome() {
        try {
            Parent home = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
            Stage stage = (Stage) patronTable.getScene().getWindow();
            stage.setScene(new Scene(home));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

package controller;

import dao.SettingDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Setting;

//import java.awt.Label;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public class SettingsController {

    @FXML
    private TextField fineRateField;

    @FXML
    private TextField libraryHoursField;
    
    @FXML private Label statusLabel;

    private final SettingDAO dao = new SettingDAO();
    
    
    

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

    @FXML
    public void initialize() {
        List<Setting> settings = dao.getAllSettings();

        for (Setting setting : settings) {
            switch (setting.getKey()) {
                case "library_hours":
                    libraryHoursField.setText(setting.getValue());
                    break;
                case "fine_per_day":
                    fineRateField.setText(setting.getValue());
                    break;
            }
        }
    }

    @FXML
    public void handleSave() {
        String fineRate = fineRateField.getText();
        String hours = libraryHoursField.getText();

        if (!fineRate.matches("\\d+(\\.\\d+)?")) {
            showAlert("Invalid input", "Fine rate must be a valid number.");
            return;
        }

        dao.updateSetting("library_hours", hours);
        dao.updateSetting("fine_per_day", fineRate);

        showAlert("Success", "Settings updated successfully.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patron;
import dao.PatronDAO;

public class PatronFormController {

    @FXML private TextField nameField, typeField, dateField, emailField;
    private Stage dialogStage;
    private Patron patron;
    private boolean saved = false;

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
        nameField.setText(patron.getFullName());
        typeField.setText(patron.getMemberType());
        dateField.setText(patron.getJoinDate());
        emailField.setText(patron.getEmail());
    }

    public boolean isSaved() {
        return saved;
    }

    @FXML
    private void handleSave() {
        if (patron == null) patron = new Patron();

        patron.setFullName(nameField.getText());
        patron.setMemberType(typeField.getText());
        patron.setJoinDate(dateField.getText());
        patron.setEmail(emailField.getText());

        PatronDAO dao = new PatronDAO();
        if (patron.getMemberId() == 0) {
            dao.addPatron(patron);
        } else {
            dao.updatePatron(patron);
        }

        saved = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}

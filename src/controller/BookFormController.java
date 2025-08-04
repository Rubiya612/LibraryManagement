package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import dao.BookDAO;
 // Adjust if your package is different

public class BookFormController {

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField isbnField;
    @FXML private TextField pubYearField;
    // Add other fields as needed

    private Stage dialogStage;
    private Book book;       // The book being edited or new one to add
    private boolean saved = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    // Call this to load a book into the form for editing
    public void setBook(Book book) {
        this.book = book;
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        isbnField.setText(book.getIsbn());
        pubYearField.setText(book.getPubYear());
        // Set other fields similarly
    }

    @FXML
    private void handleSave() {
        // Validate input (you can add your own validation)
        if (isInputValid()) {
            if (book == null) {
                book = new Book();
            }
            book.setTitle(titleField.getText());
            book.setAuthor(authorField.getText());
            book.setIsbn(isbnField.getText());
            book.setPubYear(pubYearField.getText());
            // Set other fields similarly

            // Save book via DAO or controller
            BookController bookController = new BookController();
            if (book.getBookId() == 0) {
                bookController.addBook(book);
            } else {
                bookController.updateBook(book);
            }

            saved = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isSaved() {
        return saved;
    }

    private boolean isInputValid() {
        // Add your input validation here
        return true;
    }
}

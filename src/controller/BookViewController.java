
package controller;

import dao.BookDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BookViewController {

    @FXML private TextField searchField;
    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, Integer> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> isbnCol;
    @FXML private TableColumn<Book, String> pubYearCol;

    private BookController bookController;
    private ObservableList<Book> bookList;

    public BookViewController() {
        bookController = new BookController();
        bookList = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        pubYearCol.setCellValueFactory(new PropertyValueFactory<>("pubYear"));
        loadBooks();
    }

    private void loadBooks() {
        List<Book> books = bookController.getAllBooks();
        bookList.setAll(books);
        bookTable.setItems(bookList);
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            loadBooks();
        } else {
            List<Book> results = bookController.searchBooks(keyword);
            bookTable.setItems(FXCollections.observableArrayList(results));
        }
    }

    @FXML
    private void handleClearSearch() {
        searchField.clear();
        loadBooks();
    }

    @FXML
    private void handleAddBook() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BookForm.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add New Book");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));

            // Pass the stage to the form controller
            BookFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if (controller.isSaved()) {
                // Reload your books table after adding
                loadBooks(); // or however you load your TableView data
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleEditBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BookForm.fxml"));
                Parent root = loader.load();

                // Get the controller and pass the selected book
                BookFormController controller = loader.getController();
                controller.setDialogStage(new Stage()); // Create a new stage for dialog
                controller.setBook(selected);           // Pass the book to edit

                Stage dialogStage = controller.getDialogStage();
                dialogStage.setTitle("Edit Book");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.setScene(new Scene(root));
                dialogStage.showAndWait();

                if (controller.isSaved()) {
                    loadBooks();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("No book selected", "Please select a book to edit.");
        }
    }


    @FXML
    private void handleDeleteBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Delete Confirmation");
            confirm.setHeaderText("Are you sure?");
            confirm.setContentText("Delete book: " + selected.getTitle() + "?");

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                bookController.deleteBook(selected.getBookId());
                loadBooks();
            }
        } else {
            showAlert("No book selected", "Please select a book to delete.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    private void handleHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) searchField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

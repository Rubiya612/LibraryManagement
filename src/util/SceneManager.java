package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchScene(String fxmlFile) {
        try {
            // Correct path — just fxmlFile, not appended to Home.fxml
            Parent root = FXMLLoader.load(SceneManager.class.getResource("/view/" + fxmlFile));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

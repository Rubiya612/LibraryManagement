import javafx.application.Application;
import javafx.stage.Stage;
import util.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Set the primary stage for scene management
            SceneManager.setStage(stage);

            // Load the Home scene at startup
            SceneManager.switchScene("Home.fxml");

            stage.setTitle("Library Management System");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX Application
    }
}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private Image icon;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file and set the scene
        FXMLLoader loader = new FXMLLoader(App.class.getResource("GeneratorUI.fxml"));
        Scene scene = new Scene(loader.load(), 630, 450);
        primaryStage.setTitle("Password Generator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        // Load the icon for the application
        if (loadIcon()) {
            primaryStage.getIcons().add(icon);
        }
        primaryStage.show();
    }

    /**
     * Loads the icon for the application.
     * @return True if the icon was loaded successfully, false otherwise
     */
    private Boolean loadIcon() {
        try {
            icon = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/password_generator_icon.png")));
            return true;
        } catch (NullPointerException e) {
            System.err.println("Error: Icon resource not found. \nError Message: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error: Unable to load icon. \nError Message: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

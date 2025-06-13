package de.hitec.nhplus;

import de.hitec.nhplus.datastorage.ConnectionBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class of the application. It extends JavaFX {@link Application} and serves as the entry point
 * to launch the GUI. The main window shown at start is the login window.
 */
public class Main extends Application {

    private Stage primaryStage;

    /**
     * Starts the JavaFX application and opens the login window.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginWindow();
    }

    /**
     * Loads and shows the login window. Also handles cleanup on application close.
     */
    private void showLoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/hitec/nhplus/LoginView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            this.primaryStage.setTitle("Login");
            this.primaryStage.setScene(scene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();

            this.primaryStage.setOnCloseRequest(event -> {
                ConnectionBuilder.closeConnection();
                Platform.exit();
                System.exit(0);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
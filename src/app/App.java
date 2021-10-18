package app;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getResource("ui/assets/index.fxml")));
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("ui/assets/logo.png")
        )));
        primaryStage.setTitle("MULE-CodeLab");
        primaryStage.setScene(new Scene(root, 740, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

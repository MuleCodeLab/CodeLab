package app;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ui/index.fxml")));
        primaryStage.setTitle("MULE-CodeLab");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("data/NUIMLogoIcon.png"))));
        primaryStage.setScene(new Scene(root, 740, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

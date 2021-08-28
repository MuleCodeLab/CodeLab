package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/index.fxml"));
        primaryStage.setTitle("MULE-CodeLab");
        primaryStage.setScene(new Scene(root, 740, 600));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("data/NUIMLogoIcon.png")));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

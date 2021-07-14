package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxml = "TabbedScene5.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        primaryStage.setTitle("MULE-CodeLab");
        primaryStage.setScene(new Scene(root, 740, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

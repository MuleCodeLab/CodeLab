package app;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.syed.core.Util;

import java.nio.file.Paths;
import java.util.Objects;

public class App extends Application {

    // !! [ Application path ]
    // !! files (excluding image and .fxml files) must be prefixed with this path
    // !! creating .jar or .exe file will not work without this prefix operation
    public static final String PATH = Paths.get(".").toAbsolutePath().normalize().toString();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ui/index.fxml")));
        primaryStage.setTitle("MULE-CodeLab");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("ui/NUIMLogoIcon.png"))));
        primaryStage.setScene(new Scene(root, 740, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

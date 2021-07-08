package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import syed.core.*;
import syed.JavaLab.*;

public class Main extends Application {

    void test(String path) {
        Code labcode = new JavaCode(path);
        CodeCompiler compiler = new JavaCompiler((JavaCode) labcode);
        CodeRunner runner = new JavaRunner((JavaCompiler) compiler);
        CodeEvaluator evaluator = new JavaEvaluator((JavaRunner) runner);

        evaluator.setCompileGrade(10);
        evaluator.setRegexGrade(30, 2);
        evaluator.setTestGrade(60, 4);

        evaluator.specifyRegex("DNA",
                new Regex(".*public\\s\\+static\\s\\+void\\s\\+main\\s*(.*).*",
                "a main method")
        );
        evaluator.specifyRegex("Sequence",
                new Regex(".*public\\s\\+boolean\\s\\+compareSequence\\s*(.*).*",
                "a compare sequence method")
        );

        evaluator.setTestData("johndoe\njohndoe\njohnnoe\n", "No Mutation detected!\n");
        evaluator.setTestData("syed\nabcd\nefgh\n", "Mutation detected!\n");
        evaluator.setTestData("jajja\nhajja\niaiia\n", "Mutation detected!\n");
        evaluator.setTestData("test\ntae0\n1est\n", "No Mutation detected!\n");

        compiler.writeScript();
        runner.writeScript();
        evaluator.writeScript();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TabbedScene2.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        test("./tests");
    }


    public static void main(String[] args) {
        launch(args);
    }
}

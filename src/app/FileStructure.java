package app;

import syed.code.core.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStructure {

    private String path;
    private JSONDataStorage jsonData;
    private HTMLDataStorage htmlData;
    private ScriptsDataStorage scriptsData;

    public FileStructure(String path) throws IOException {
        Util.DEBUG("PATH: " + path);
        Path p = Path.of(path);
        if (Files.notExists(p)) {
            Files.createDirectory(p);
            Util.DEBUG("CourseLevel path created.");
        }
        this.path = p.toString();
        Util.DEBUG("path: " + this.path);
    }

    public String getPath() {
        return path;
    }

    public String getCourseLevelPath() throws IOException {
        Path path = Path.of(getPath() +"/"+ jsonData.courseData.getTitle());
        if (Files.notExists(path)) {
            Files.createDirectory(path);
            Util.DEBUG("CourseLevel path created.");
        }
        return path.toString();
    }

    public String getLabLevelPath() throws IOException {
        Path path = Path.of(getCourseLevelPath()+"/"+jsonData.labData.getLabLabel()+jsonData.labData.getLabNumber());
        if (Files.notExists(path)) {
            Files.createDirectory(path);
            Util.DEBUG("LabLevel path created.");
        }
        return path.toString();
    }

    public String getQuestionLevelPath() throws IOException {
        Path path = Path.of(getLabLevelPath()+"/"+
                                jsonData.questionData.getTitle() +
                                jsonData.questionData.getQuestionNumber());
        if (Files.notExists(path)) {
            Files.createDirectory(path);
            Util.DEBUG("QuestionLevel path created.");
        }
        return path.toString();
    }

//    public static String setPath(String path) throws IOException {
//        Path p = Path.of(path);
//        if (Files.notExists(p)) {
//            Files.createDirectory(p);
//            Util.DEBUG("File created.");
//        }
//        Util.DEBUG("File accessed.");
//        this.path
//        return p.toString();
//    }

    public void setJSONData(JSONDataStorage jsonData) {
        this.jsonData = jsonData;
    }

    public void setHTMLData(HTMLDataStorage htmlData) {
        this.htmlData = htmlData;
    }

    public void setScriptsData(ScriptsDataStorage scriptsData) {
        this.scriptsData = scriptsData;
    }

}

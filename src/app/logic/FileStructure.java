package app.logic;

import app.logic.storage.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStructure {

    private String path;
    private JSONDataStorage jsonData;
    private HTMLDataStorage htmlData;
    private ScriptsDataStorage scriptsData;

    public FileStructure(String path) throws IOException {
        Path p = Path.of(path);
        if (Files.notExists(p)) {
            Files.createDirectory(p);
        }
        this.path = p.toString();
    }

    public String getPath() {
        return path;
    }

    public String getCourseLevelPath() throws IOException {
        Path path = Path.of(getPath() +"/"+ jsonData.courseData.getTitle());
        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
        return path.toString();
    }

    public String getLabLevelPath() throws IOException {
        Path path = Path.of(getCourseLevelPath()+"/"+jsonData.labData.getLabLabel()+jsonData.labData.getLabNumber());
        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
        return path.toString();
    }

    public String getQuestionLevelPath() throws IOException {
        Path path = Path.of(getLabLevelPath()+"/"+
                                jsonData.questionData.getTitle() +
                                jsonData.questionData.getQuestionNumber());
        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
        return path.toString();
    }

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

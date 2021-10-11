package app.storage;

import com.syed.core.Util;

public class JSONDataStorage {

    public CourseLevelJSONDataStorage courseData = new CourseLevelJSONDataStorage();
    public LabLevelJSONDataStorage labData = new LabLevelJSONDataStorage();
    public QuestionLevelJSONDataStorage questionData = new QuestionLevelJSONDataStorage(labData);


    public boolean isReady() {
        return courseData.isReady() && labData.isReady() && questionData.isReady();
    }

    public void print() {
        Util.ECHO("------------------\n");
        courseData.print();
        Util.ECHO("------------------\n");
        labData.print();
        Util.ECHO("------------------\n");
        questionData.print();
        Util.ECHO("------------------\n");
    }
}


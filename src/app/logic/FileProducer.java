package app.logic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import app.storage.*;
import app.ui.LabSessionTableData;
import app.ui.Widget;

import com.michael.htmltools.*;
import com.michael.jsontools.*;
import com.syed.core.*;
import com.syed.javalab.*;
import com.syed.pythonlab.*;
import com.syed.clab.*;

import javax.swing.JFileChooser; // to get documents path


public class FileProducer {

    private final JSONDataStorage jsonStorage;
    private final HTMLDataStorage htmlStorage;
    private final ScriptsDataStorage scriptStorage;
    private final String location;

    public FileProducer(JSONDataStorage jds, HTMLDataStorage htmlds, ScriptsDataStorage sds) throws IOException {
        this("Courses", jds, htmlds, sds);
    }

    private FileProducer(String folderName, JSONDataStorage jds, HTMLDataStorage htmlds, ScriptsDataStorage sds) throws IOException {
        this.jsonStorage = jds;
        this.htmlStorage = htmlds;
        this.scriptStorage = sds;

        String docPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        if (Util.isLinuxOS()) {
            this.location = Util.path(docPath+"/Documents/"+folderName);
        } else if (Util.isWindowsOS()) {
            this.location = Util.path(docPath+"\\"+folderName);
        } else {
            this.location = "";
            System.exit(0);
        }
        Widget.OK("Files Path", this.location);
    }

    public boolean json() {

        if (jsonStorage.isReady()) {
            String coursePath = getCourseLevelPath();
            String labPath = getLabLevelPath();
            String questionPath = getQuestionLevelPath();

            MuleCourseLevelJSON courseJson;
            MuleLabLevelJSON labJson;
            MuleQuestionLevelJSON questionJson;

            String courseCode = jsonStorage.courseData.getTitle();
            courseJson = new MuleCourseLevelJSON(courseCode);
            Util.writeToFile(coursePath+"/metadata.json", courseJson.toString());

            int labNumber = jsonStorage.labData.getLabNumber();
            String labLabel = jsonStorage.labData.getLabLabel();
            LocalDateTime accessStart = jsonStorage.labData.getAccessStart();
            LocalDateTime accessEnd = jsonStorage.labData.getAccessEnd();
            LocalDateTime caEvalStart = jsonStorage.labData.getCAEvalStart();
            LocalDateTime caEvalEnd = jsonStorage.labData.getCAEvalEnd();
            labJson = new MuleLabLevelJSON(labLabel, labNumber, accessStart, accessEnd, caEvalStart, caEvalEnd);
            Util.writeToFile(labPath+"/metadata.json", labJson.toString());

            String questionTitle = jsonStorage.questionData.getTitle();
            String courseCodeQ = jsonStorage.questionData.getCourse();
            int labNumberQ = jsonStorage.questionData.getLabNumber();
            int questionNumber = jsonStorage.questionData.getQuestionNumber();
            String[] files = jsonStorage.questionData.getFiles();


            if(jsonStorage.questionData.isHiddenQuestion()) {
                List<LabSessionTableData> sessions = jsonStorage.questionData.getSessions();
                long sessionLength = jsonStorage.questionData.getLength();
                LocalDateTime pgStart = jsonStorage.questionData.getPGStart();
                LocalDateTime pgEnd = jsonStorage.questionData.getPGEnd();
                MuleHiddenQuestionJSON[] hiddenQuestionJson = new MuleHiddenQuestionJSON[sessions.size()];
                String[] paths = getSessionLevelPath();

                for(int i = 0; i < sessions.size(); i++) {
                    LabSessionTableData session = sessions.get(i);
                    String group = session.getGroup();
                    LocalDateTime startTime = session.getSessionStartTime();
                    hiddenQuestionJson[i] = new MuleHiddenQuestionJSON(
                            questionTitle,
                            courseCodeQ,
                            labNumberQ,
                            questionNumber,
                            files,
                            startTime,
                            sessionLength,
                            pgStart,
                            pgEnd,
                            group
                    );
                    Util.writeToFile(paths[i]+"/metadata.json", hiddenQuestionJson[i].toString());
                }
            } else {
                questionJson = new MuleQuestionLevelJSON(questionTitle, courseCodeQ, labNumberQ, questionNumber, files);
                Util.writeToFile(questionPath+"/metadata.json", questionJson.toString());
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean html() throws IOException {

        if (htmlStorage.isReady()) {
            String path = getQuestionLevelPath();
            String css = htmlStorage.getCss();
            String body = htmlStorage.getDescription();
            String[] notes = htmlStorage.getNotes();
            String[] imageUrls = htmlStorage.getImagesUrls();
            String[] sampleCodes = htmlStorage.getCodeSamples();
            String output = htmlStorage.getSingleExpectedOutput();
            String[] sampleInputs = htmlStorage.getSampleInputs();
            String[] sampleOutputs = htmlStorage.getSampleOutputs();
            String[][] sampleIO = new String[sampleInputs.length][2];

            for(int i = 0; i < sampleIO.length; i++) {
                sampleIO[i][0] = sampleInputs[i];
                sampleIO[i][1] = sampleOutputs[i];
            }

            MuleHTML html = new MuleHTML(
                    css,
                    jsonStorage.questionData.getTitle(),
                    body,
                    notes,
                    imageUrls,
                    sampleCodes,
                    output,
                    sampleIO
            );

            if(jsonStorage.questionData.isHiddenQuestion()) {
                String[] paths = getSessionLevelPath();
                for (String p : paths) {
                    Util.writeToFile(p + "/description.html", html.toString());
                }
            }
            else {
                Util.writeToFile(path+"/description.html", html.toString());
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean scripts() throws IOException {

        if (scriptStorage.isReady()) {
            Map<String, String> code = scriptStorage.getCode();
            Map<String, List<Regex>> regex = scriptStorage.getRegex();
            String[] sessionPaths = getSessionLevelPath();
            String path = getQuestionLevelPath();

            for (String filename : scriptStorage.getCodeFiles()) {
                if (!code.containsKey(filename)) {
                    scriptStorage.code.put(filename, "");
                }
            }

            if (jsonStorage.questionData.isHiddenQuestion()) {
                for (String sPath : sessionPaths) {
                    for (Map.Entry<String, String> fileAndCode : code.entrySet()) {
                        Util.writeToFile(sPath + "/" + fileAndCode.getKey(), fileAndCode.getValue());
                    }
                }
            } else {
                for (Map.Entry<String, String> fileAndCode : code.entrySet()) {
                    Util.writeToFile(path + "/" + fileAndCode.getKey(), fileAndCode.getValue());
                }
            }

            Code labcode = null;
            CodeCompiler compiler = null;
            CodeRunner runner = null;
            CodeEvaluator evaluator = null;

            switch (scriptStorage.labLanguage) {
                case "JAVA" -> {
                    labcode = new JavaCode(scriptStorage.getCode(), Util.fileTitle(scriptStorage.getMainFile()));
                    compiler = new JavaCompiler((JavaCode) labcode);
                    runner = new JavaRunner((JavaCompiler) compiler);
                    evaluator = new JavaEvaluator((JavaRunner) runner);
                }
                case "C" -> {
                    labcode = new CCode(scriptStorage.getCode(), Util.fileTitle(scriptStorage.getMainFile()));
                    compiler = new CCompiler((CCode) labcode);
                    runner = new CRunner((CCompiler) compiler);
                    evaluator = new CEvaluator((CRunner) runner);
                }
                case "PYTHON" -> {
                    labcode = new PythonCode(scriptStorage.getCode(), Util.fileTitle(scriptStorage.getMainFile()));
                    runner = new PythonRunner((PythonCode) labcode);
                    evaluator = new PythonEvaluator((PythonRunner) runner);
                }
                default -> { }
            }

            if (labcode == null || compiler == null) {
                Widget.ERROR("Unexpected Error!", "Restarting the program might fix the problem.");
                return false;
            }

            evaluator.setCompileGrade(scriptStorage.getCompileGrade());
            evaluator.setRegexGrade(scriptStorage.getRegexGrade(), regex.size());
            evaluator.setTestGrade(scriptStorage.getTCGrade(), scriptStorage.getTestCaseIOs().size());

            for (Map.Entry<String, List<Regex>> p : regex.entrySet()) {
                for (Regex r : p.getValue()) {
                    evaluator.specifyRegex(Util.fileTitle(p.getKey()), r);
                }
            }

            for (TestIO tc : scriptStorage.getTestCaseIOs()) {
                evaluator.setTestData(tc.getInput(), tc.getOutput());
            }

            if(jsonStorage.questionData.isHiddenQuestion()) {
                for (String sPath : sessionPaths) {
                    if (!scriptStorage.getLabLanguage().equals("PYTHON")) {
                        compiler.writeScript(sPath + "/vpl_compile.sh");
                    }
                    runner.writeScript(sPath+"/vpl_run.sh");
                    evaluator.writeScript(sPath+"/vpl_evaluate.sh");
                }
            } else {
                if (!scriptStorage.getLabLanguage().equals("PYTHON")) {
                    compiler.writeScript(path + "/vpl_compile.sh");
                }
                runner.writeScript(path + "/vpl_run.sh");
                evaluator.writeScript(path + "/vpl_evaluate.sh");
            }
            return true;
        } else {
            return false;
        }
    }

    public String getLocation() {
        return location;
    }

    public String getCourseLevelPath() {
        return Util.path(getLocation() +"/"+ jsonStorage.courseData.getTitle());
    }

    public String getLabLevelPath() {
        return Util.path(
                getCourseLevelPath()+"/"+
                jsonStorage.labData.getLabLabel()+
                jsonStorage.labData.getLabNumber()
        );
    }

    public String getQuestionLevelPath() {
        return Util.path(getLabLevelPath()+"/"+ jsonStorage.questionData.getTitle());
    }

    public String[] getSessionLevelPath() {
        String qPath = getQuestionLevelPath();
        List<LabSessionTableData> sessions = jsonStorage.questionData.getSessions();
        String[] copy = new String[sessions.size()];
        for (int i = 0; i < sessions.size(); i++) {
            copy[i] = Util.path(qPath+"-"+sessions.get(i).getGroup());
        }
        return copy;
    }
}

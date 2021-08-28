package app.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.logic.storage.*;
import michael.code.htmltools.*;
import michael.code.jsontools.*;
import syed.code.core.*;
import syed.code.javalab.*;
import syed.code.pythonlab.*;
import syed.code.clab.*;


public class FileProducer {

    private FileStructure fs;
    private JSONDataStorage jsonStorage;
    private HTMLDataStorage htmlStorage;
    private ScriptsDataStorage scriptStorage;

    private static final String LOCATION = "../MuleCodeLab/";

    public FileProducer(JSONDataStorage jds, HTMLDataStorage htmlds, ScriptsDataStorage sds) {
        this.jsonStorage = jds;
        this.htmlStorage = htmlds;
        this.scriptStorage = sds;
        try {
            this.fs = new FileStructure(LOCATION);
            this.fs.setJSONData(jsonStorage);
            this.fs.setHTMLData(htmlStorage);
            this.fs.setScriptsData(scriptStorage);
        } catch (IOException e) {
            Util.ECHO(e.getMessage());
        }
    }

    public boolean json() throws IOException {
        if (jsonStorage.isReady()) {
            String coursePath = fs.getCourseLevelPath();
            String labPath = fs.getLabLevelPath();
            String questionPath = fs.getQuestionLevelPath();

            MuleCourseLevelJSON courseJson;
            MuleLabLevelJSON labJson;
            MuleQuestionLevelJSON questionJson;
            MuleHiddenQuestionJSON[] hiddenQuestionJson;

            String courseCode = jsonStorage.courseData.getTitle();
            courseJson = new MuleCourseLevelJSON(courseCode);
            Util.writeToFile(coursePath+"/metadata.json", courseJson.toString()); //Error: ..\MuleCodeLab\CSXXX\ (Access denied)

            int labNumber = jsonStorage.labData.getLabNumber();
            String labLabel = jsonStorage.labData.getLabLabel();
            LocalDateTime accessStart = jsonStorage.labData.getAccessStart();
            LocalDateTime accessEnd = jsonStorage.labData.getAccessEnd();
            LocalDateTime caEvalStart = jsonStorage.labData.getCAEvalStart();
            LocalDateTime caEvalEnd = jsonStorage.labData.getCAEvalEnd();
            labJson = new MuleLabLevelJSON(labLabel, labNumber, accessStart, accessEnd, caEvalStart, caEvalEnd);
            Util.writeToFile(labPath+"/metadata.json", labJson.toString()); //Error: ..\MuleCodeLab\CSXXX\LabX (Access denied)

            String questionTitle = jsonStorage.questionData.getTitle();
            String courseCodeQ = jsonStorage.questionData.getCourse();
            int labNumberQ = jsonStorage.questionData.getLabNumber();
            int questionNumber = jsonStorage.questionData.getQuestionNumber();
            String[] files = jsonStorage.questionData.getFiles();


            if(jsonStorage.questionData.isHiddenQuestion()) {
                //If question is a hidden question
                List<LabSessionTableData> sessions = jsonStorage.questionData.getSessions();
                long sessionLength = jsonStorage.questionData.getLength();
                LocalDateTime pgStart = jsonStorage.questionData.getPGStart();
                LocalDateTime pgEnd = jsonStorage.questionData.getPGEnd();
                hiddenQuestionJson = new MuleHiddenQuestionJSON[sessions.size()];

                for(int i = 0; i < sessions.size(); i++) {

                    LabSessionTableData session = sessions.get(i);
                    String group = session.getGroup();
                    LocalDateTime startTime = session.getSessionStartTime();

                    hiddenQuestionJson[i] = new MuleHiddenQuestionJSON(questionTitle, courseCodeQ, labNumberQ, questionNumber, files, startTime, sessionLength, pgStart, pgEnd, group);

                    questionPath = fs.getQuestionLevelPath()+"-"+group;
                    Path questionPathObj = Paths.get(questionPath);
                    Files.createDirectories(questionPathObj);
                    Util.writeToFile(questionPath+"/metadata.json", hiddenQuestionJson[i].toString());
                }

            }
            else {
                //Else if question is not a hidden question
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
            String path = fs.getQuestionLevelPath();
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

            MuleHTML html = new MuleHTML(css, jsonStorage.questionData.getTitle(), body, notes, imageUrls, sampleCodes, output, sampleIO);

            if(jsonStorage.questionData.isHiddenQuestion()) {
                List<LabSessionTableData> sessions = jsonStorage.questionData.getSessions();
                for (LabSessionTableData session : sessions) {
                    String group = session.getGroup();
                    String hqPath = path + "-" + group;
                    Util.writeToFile(hqPath + "/description.html", html.toString());
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
            List<LabSessionTableData> sessions = jsonStorage.questionData.getSessions();
            Map<String, List<Regex>> regex = scriptStorage.getRegex();
            List<String> sessionPaths = new ArrayList<>();
            String path = fs.getQuestionLevelPath();

            for (LabSessionTableData session : sessions) {
                sessionPaths.add(path+"-"+session.getGroup());
            }


            for (String filename : scriptStorage.getCodeFiles()) {
                if (!code.containsKey(filename)) {
                    scriptStorage.code.put(filename, ""); // no code in the given file basically
                }
            }

            if(jsonStorage.questionData.isHiddenQuestion()) {
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
                case "JAVA": {
                    labcode = new JavaCode(scriptStorage.getCode(), Util.fileTitle(scriptStorage.getMainFile()));
                    compiler = new JavaCompiler((JavaCode) labcode);
                    runner = new JavaRunner((JavaCompiler) compiler);
                    evaluator = new JavaEvaluator((JavaRunner) runner);
                } break;
                case "C": {
                    labcode = new CCode(scriptStorage.getCode(), Util.fileTitle(scriptStorage.getMainFile()));
                    compiler = new CCompiler((CCode) labcode);
                    runner = new CRunner((CCompiler) compiler);
                    evaluator = new CEvaluator((CRunner) runner);
                } break;
                case "PYTHON": {
                    labcode = new PythonCode(scriptStorage.getCode(), Util.fileTitle(scriptStorage.getMainFile()));
                    runner = new PythonRunner((PythonCode) labcode);
                    evaluator = new PythonEvaluator((PythonRunner) runner);
                } break;
                default: break;
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

}

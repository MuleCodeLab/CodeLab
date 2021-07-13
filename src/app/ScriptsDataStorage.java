package app;

import syed.code.core.Regex;
import syed.code.core.Util;

import java.util.*;

public class ScriptsDataStorage {

    String labLanguage;
    String mainFile;
    String codeBuffer;
    String compileGrade, regexGrade, tcGrade;
    ArrayList<String> files;
    HashMap<String, String> code;
    HashMap<String, List<Regex>> regexes;
    HashMap<String, String> testCaseIOs;

    ScriptsDataStorage(QuestionLevelJSONData questionData) {
        files = new ArrayList<>();
        code = new HashMap<>();
        regexes = new HashMap<>();
        testCaseIOs = new HashMap<>();
        Collections.addAll(files, questionData.getFiles());
        getPredefinedRegex();
    }



    public String getLabLanguage() {
        return labLanguage;
    }

    public String getMainFile() {
        return mainFile;
    }

    public String[] getCodeFiles() {
        String[] copy = new String[files.size()];
        int i = 0;
        for (String f : files) { copy[i++] = f; }
        return copy;
    }

    public Map<String, String> getCode() {
        return code;
    }

    public Map<String, List<Regex>> getRegex() {
        return regexes;
    }

    public Map<String, String> getTestCaseIOs() {
        return testCaseIOs;
    }

    public int getCompileGrade() {
        return Integer.parseInt(compileGrade.trim());
    }

    public int getRegexGrade() {
        return Integer.parseInt(regexGrade.trim());
    }

    public int getTCGrade() {
        return Integer.parseInt(tcGrade.trim());
    }


    public List<Regex> getPredefinedRegex() {
        String[] comments = Util.readlines("./src/app/comments.txt").split("\n");
        String[] regex = Util.readlines("./src/app/regex.txt").split("\n");

        // [ASSUMPTION] regex and comments are the same length
        // conversion from java to bash format
        for (int i = 0; i < regex.length; i++) {
            regex[i] = regex[i].replace("\\", "\\\\");
        }

        int size = Math.min(comments.length, regex.length);
        List<Regex> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Regex(regex[i], comments[i]));
            Util.DEBUG(regex[i] + " | "+ comments[i]);
        }
        return  list;
    }

}

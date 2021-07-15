package app;

import syed.code.core.Regex;
import syed.code.core.TestIO;
import syed.code.core.Util;

import java.util.*;

public class ScriptsDataStorage {

    String labLanguage;
    String mainFile;
    String compileGrade, regexGrade, tcGrade;
    ArrayList<String> files;
    HashMap<String, String> code;
    HashMap<String, List<Regex>> regexes;
    ArrayList<TestIO> testCaseIOs;

    ScriptsDataStorage(QuestionLevelJSONData questionData) {
        files = new ArrayList<>();
        code = new HashMap<>();
        regexes = new HashMap<>();
        testCaseIOs = new ArrayList<>();
        Collections.addAll(files, questionData.getFiles());
        getPredefinedRegex();
    }



    public String getLabLanguage() {
        return labLanguage;
    }

    public String getMainFile() {
        if (files.size() == 1) {
            mainFile = files.get(0);
        }
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

    public ArrayList<TestIO> getTestCaseIOs() {
        return testCaseIOs;
    }

    public int getCompileGrade() {
        int v = 0;
        try { v = Integer.parseInt(compileGrade.trim()); }
        catch (Exception e) {}
        return v;
    }

    public int getRegexGrade() {
        int v = 0;
        try { v = Integer.parseInt(regexGrade.trim()); }
        catch (Exception e) {}
        return v;
    }

    public int getTCGrade() {
        int v = 0;
        try { v = Integer.parseInt(tcGrade.trim()); }
        catch (Exception e) {}
        return v;
    }


    public int getTotalGrade() {
        return getCompileGrade() + getRegexGrade() + getTCGrade();
    }

    public List<Regex> getPredefinedRegex() {
        String[] comments = Util.readlines("./src/app/data/comments.txt").split("\n");
        String[] regex = Util.readlines("./src/app/data/regex.txt").split("\n");

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



    public void print() {
        Util.ECHO("Lab Language: "+getLabLanguage());
        Util.ECHO("MainFile: " + getMainFile());
        Util.ECHO("Compile grade: "+ getCompileGrade());
        Util.ECHO("Regex grade: "+ getRegexGrade());
        Util.ECHO("TestCase grade: "+ getTCGrade());
        Util.ECHO("Code Files: "+Arrays.toString(getCodeFiles()));
        Util.ECHO("[Code]:");
        for (Map.Entry<String, String> p : getCode().entrySet()) {
            Util.ECHO(p.getKey() + ": "+p.getValue());
        }
        Util.ECHO("[Regex]:");
        for (Map.Entry<String, List<Regex>> p : getRegex().entrySet()) {
            Util.ECHO("\t["+p.getKey() + "]:");
            for (Regex r : p.getValue()) {
                Util.ECHO("\t\t"+r.getComment());
                Util.ECHO("\t\t"+r.use());
            }
        }
        Util.ECHO("TestCase IO:");
        for (TestIO tio : getTestCaseIOs()) {
            Util.ECHO("\nInput:\n"+tio.getInput() + "\nOutput:\n"+tio.getOutput());
        }
        Util.ECHO("\n------------------------------\n");
    }

}

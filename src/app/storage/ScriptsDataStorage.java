package app.storage;

import java.util.*;

import com.syed.core.Regex;
import com.syed.core.TestIO;
import com.syed.core.Util;


public class ScriptsDataStorage {

    public String labLanguage;
    public String mainFile;
    public String compileGrade, regexGrade, tcGrade;
    public ArrayList<String> files;
    public HashMap<String, String> code;
    public HashMap<String, List<Regex>> regexes;
    public ArrayList<TestIO> testCaseIOs;

    public ScriptsDataStorage(QuestionLevelJSONDataStorage questionData) {
        files = new ArrayList<>();
        code = new HashMap<>();
        regexes = new HashMap<>();
        testCaseIOs = new ArrayList<>();
        Collections.addAll(files, questionData.getFiles());
    }


    public boolean isReady() {
        return  getLabLanguage() != null && getMainFile() != null &&
                getCompileGrade() + getRegexGrade() + getTCGrade() > 0 &&
                files.size() > 0 && regexes != null && testCaseIOs.size() > 0;
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
        // cleaning before returning
        for (Map.Entry<String, String> fc : code.entrySet()) {
            boolean exist = false;
            for (String f : getCodeFiles()) {
                if (f.equals(fc.getKey())) {
                    exist = true;
                }
            }
            if (!exist) {
                code.remove(fc.getKey());
            }
        }
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

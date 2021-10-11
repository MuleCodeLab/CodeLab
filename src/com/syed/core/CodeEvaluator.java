package com.syed.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class CodeEvaluator {

    private final Code code;
    private final CodeRunner runner;
    private final HashMap<String, ArrayList<Regex>> regex;
    private final ArrayList<TestIO> testIO;
    private Grade cmplGrade, regGrade, tcGrade;

    public CodeEvaluator(CodeRunner runner) {
        this.runner = runner;
        this.code = this.runner.getCompiler().getCode();
        this.regex = new HashMap<>();
        this.testIO = new ArrayList<>();
        for (String name : this.getFileTitles()) {
            regex.put(name, new ArrayList<>());
        }
    }

    public void setTestData(String input, String output) {
        if (input == null || output == null) {
            Util.ERROR("Test input and/or output cannot be null");
            return;
        }
        this.testIO.add(new TestIO(input, output));
    }

    public void setTestData(File input, File output) {
        this.setTestData(
            Util.readlines(input.getAbsolutePath()), 
            Util.readlines(output.getAbsolutePath())
        );
    }

    public void specifyRegex(Regex... regexes) {
        if (this.getCode().getCount() > 0) {
            this.specifyRegex(this.code.getMainFile(), regexes);
        }
    }

    public void specifyRegex(String filetitle, Regex... regexes) {
        if (filetitle.endsWith(this.code.getLanguage().getExtension())) {
            filetitle = Util.fileTitle(filetitle);
        }
        if (!this.regex.containsKey(filetitle)) {
            Util.ERROR(filetitle + " is not in the code files" );
            return;
        }
        for (Regex r : regexes) { 
            this.regex.get(filetitle).add(r); 
        }
    }

    public ArrayList<TestIO> getTests() {
        return new ArrayList<>(this.testIO);
    }

    public String[] getFileTitles() {
        return this.code.getFileTitles();
    }

    public void setCompileGrade(int grade) {
        this.cmplGrade = new Grade(grade, 1);
    }

    public void setRegexGrade(int total, int count) {
        this.regGrade = new Grade(total, count);
    }

    public void setTestGrade(int total, int count) {
        this.tcGrade = new Grade(total, count);
    }

    public Code getCode() {
        return this.code;
    }

    public ArrayList<TestIO> getTestIOs() {
        return this.testIO;
    }

    public HashMap<String, ArrayList<Regex>> getRegex() {
        return this.regex;
    }

    public Grade getCmplGrade() {
        return this.cmplGrade;
    }

    public Grade getRegGrade() {
        return this.regGrade;
    }

    public Grade getTcGrade() {
        return this.tcGrade;
    }

    public String mainfile() {
        return this.code.getMainFile();
    }

    public String runfile() {
        return this.runner.getRunFile();
    }

    public void writeScript() {
        this.writeScript(this.code.getBasePath()+"vpl_evaluate.sh");
    }

    public void writeScript(String to) {
        Util.writeToFile(to, this.scriptify());
    }

    // implement for every evaluator
    protected abstract String scriptify();
}
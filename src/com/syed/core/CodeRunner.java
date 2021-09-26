package com.syed.core;

public abstract class CodeRunner {

    private final CodeCompiler compiler;
    private String runfile;

    public CodeRunner(CodeCompiler compiler) {
        this.compiler = compiler;
        this.runfile = this.compiler.getOutFile();
    }

    public void setRunFile(String runfile) {
        this.runfile = runfile;
    }

    public String getRunFile() {
        return this.runfile;
    }

    public CodeCompiler getCompiler() {
        return this.compiler;
    }

    public Language getLanguage() {
        return this.compiler.getLanguage();
    }

    public void writeScript() {
        this.writeScript(this.compiler.getCode().getBasePath()+"vpl_run.sh");
    }
    
    public void writeScript(String to) {
        Util.writeToFile(to, this.scriptify());
    }    

    // implement for every runner
    protected abstract String scriptify();
}

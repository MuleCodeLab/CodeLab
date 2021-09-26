package com.syed.core;

public abstract class CodeCompiler {

    private final Code codefiles;
    private String mainfile;
    private String outfile;

    public CodeCompiler(Code codefiles, String outfile) {
        this.codefiles = codefiles;
        this.outfile = outfile;
        this.setMainFile(this.codefiles.getMainFile());
    }

    public void writeScript() {
        this.writeScript(this.codefiles.getBasePath()+"vpl_compile.sh");
    }
    
    public void writeScript(String to) {
        Util.writeToFile(to, this.scriptify());
    }    

    public Code getCode() {
        return this.codefiles;
    }

    public Language getLanguage() {
        return this.codefiles.getLanguage();
    }
    
    public String getOutFile() {
        return this.outfile;
    }

    public void setOutFile(String out) {
        this.outfile = out;
    }

    public String getMainFile() {
        return this.mainfile;
    }

    public void setMainFile(String mf) {
        this.mainfile = mf;
    }

    // implement for every compiler you create
    protected abstract String scriptify();

}

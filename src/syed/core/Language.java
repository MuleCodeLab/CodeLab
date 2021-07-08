package syed.core;

public abstract class Language {

    private String compiler;
    private String runner;
    private String extension;
    private String mainRegex;

    public Language(String cmplr, String rnr, String ext, String mr) {
        this.compiler = cmplr;
        this.runner = rnr;
        this.extension = ext;
        this.mainRegex = mr;
    }

    public String getCompiler()  { return this.compiler;  }
    public String getRunner()    { return this.runner;    }
    public String getExtension() { return this.extension; }
    public String getMainRegex() { return this.mainRegex; }

}

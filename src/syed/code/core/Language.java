package syed.code.core;

public abstract class Language {

    private final String compiler;
    private final String runner;
    private final String extension;
    private final String mainRegex;

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

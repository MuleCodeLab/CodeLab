package com.syed.core;

public abstract class Language {

    private final String compiler;
    private final String runner;
    private final String extension;
    private final String mainRegex;

    public Language(String compiler, String runner, String ext, String mainRegex) {
        this.compiler = compiler;
        this.runner = runner;
        this.extension = ext;
        this.mainRegex = mainRegex;
    }

    public String getCompiler()  { return this.compiler;  }
    public String getRunner()    { return this.runner;    }
    public String getExtension() { return this.extension; }
    public String getMainRegex() { return this.mainRegex; }

}

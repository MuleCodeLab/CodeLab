package syed.code.javalab;

import syed.code.core.Language;

public class JavaLanguage extends Language {
    public JavaLanguage() { 
        super(
            "javac", 
            "java", 
            ".java", 
            ".*public\\s+static\\s+void\\s+main\\s*" +
            "\\(\\s*String(\\s*\\[\\]\\s+\\w+|\\s+\\w+\\[\\]).*\\{.*"
        ); 
    } 
}


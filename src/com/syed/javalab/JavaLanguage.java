package com.syed.javalab;

import com.syed.core.Language;

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


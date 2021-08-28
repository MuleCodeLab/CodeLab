package syed.code.javalab;

import java.util.Map;

import syed.code.core.Code;


public class JavaCode extends Code {
    public JavaCode(Map<String, String> code, String mainfile) {
        super(new JavaLanguage(), code, mainfile);
    }
    public JavaCode(String dirpath) {
        super(new JavaLanguage(), dirpath);
    }
    public JavaCode(String dirpath, String mainfile) {
        super(new JavaLanguage(), dirpath, mainfile);
    }
}

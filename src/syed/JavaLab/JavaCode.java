package syed.JavaLab;

import syed.core.Code;

public class JavaCode extends Code {
    public JavaCode(String dirpath) {
        super(new JavaLanguage(), dirpath);
    }
    public JavaCode(String dirpath, String mainfile) {
        super(new JavaLanguage(), dirpath, mainfile);
    }
}

package syed.code.clab;

import syed.code.core.Code;

public class CCode extends Code {
    public CCode(String dirpath) {
        super(new CLanguage(), dirpath);
    }
    public CCode(String dirpath, String mainfile) {
        super(new CLanguage(), dirpath, mainfile);
    }
}
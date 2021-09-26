package com.syed.clab;

import com.syed.core.Code;
import java.util.Map;

public class CCode extends Code {
    public CCode(Map<String, String> code, String mainfile) {
        super(new CLanguage(), code, mainfile);
    }
    public CCode(String dirpath) {
        super(new CLanguage(), dirpath);
    }
    public CCode(String dirpath, String mainfile) {
        super(new CLanguage(), dirpath, mainfile);
    }
}
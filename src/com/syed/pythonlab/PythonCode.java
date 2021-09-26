package com.syed.pythonlab;

import com.syed.core.Code;

import java.util.Map;

public class PythonCode extends Code {
    public PythonCode(Map<String, String> code, String mainfile) {
        super(new PythonLanguage(), code, mainfile);
    }
    public PythonCode(String dirpath) {
        super(new PythonLanguage(), dirpath);
    }
    public PythonCode(String dirpath, String mainfile) {
        super(new PythonLanguage(), dirpath, mainfile);
    }
}


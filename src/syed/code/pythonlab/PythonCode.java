package syed.code.pythonlab;

import syed.code.core.Code;

public class PythonCode extends Code {
    public PythonCode(String dirpath) {
        super(new PythonLanguage(), dirpath);
    }
    public PythonCode(String dirpath, String mainfile) {
        super(new PythonLanguage(), dirpath, mainfile);
    }
}


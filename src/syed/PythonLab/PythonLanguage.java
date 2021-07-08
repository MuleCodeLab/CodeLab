package syed.PythonLab;

import syed.core.Language;

public class PythonLanguage extends Language { 
    public PythonLanguage() { 
        super(
            null, 
            "python3", 
            ".py",
            ".*if\\s+__name__\\s+==\\s+\"__main__\"\\:.*"
        ); 
    }
}

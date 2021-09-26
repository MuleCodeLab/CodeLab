package com.syed.pythonlab;

import com.syed.core.CodeCompiler;
import com.syed.core.CodeRunner;
import com.syed.core.Util;

public class PythonRunner extends CodeRunner {
    // DUMMY COMPILER [PYTHON DOES NOT NEED A COMPILER]
    // this stays static so we can use it inside this class (error otherwise)
    private static class PythonCompiler extends CodeCompiler {
        public PythonCompiler(PythonCode codefiles) {
            super(codefiles, null);
            this.writeScript();
        }

        @Override 
        protected String scriptify() {
            StringBuilder script = new StringBuilder();
            script.append("#! /bin/bash\n");
            script.append("# +++++++++++++++++++++++++++++++++\n");
            script.append("cat > vpl_execution <<EEOOFF\n");
            script.append("#! /bin/bash\n");
            script.append("echo \"Run the file.\"\n");
            script.append("EEOOFF\n");
            script.append("chmod +x vpl_execution\n");
            script.append("# +++++++++++++++++++++++++++++++++");
            return script.toString();
        }
    }
    
    public PythonRunner(PythonCode codefiles) {
        this(codefiles, null);
        this.setRunFile(this.getCompiler().getMainFile());
    }


    public PythonRunner(PythonCode codefiles, String runfile) {
        super(new PythonCompiler(codefiles));
        this.setRunFile(runfile);
    }

    @Override
    protected String scriptify() {
        if (this.getCompiler().getCode().getCount() == 0) {
            Util.ERROR("Cannot run 0 files");
            return null;
        }
        String runner = this.getLanguage().getRunner();
        String extension = this.getLanguage().getExtension();

        String runFmt = "%s \\${prog1}%s\n";

        StringBuilder script = new StringBuilder();
        script.append("#! /bin/bash\n");
        script.append("# +++++++++++++++++++++++++++++++++\n");
        script.append("cat > vpl_execution <<EEOOFF\n");
        script.append("#! /bin/bash\n");
        script.append("EXIT_CODE=-1\n");
        script.append("FAILED=false\n");
        script.append(String.format("prog1=%s\n", this.getRunFile()));
        script.append(String.format(runFmt, runner, extension));
        script.append("EXIT_CODE=\\$?\n");
        script.append("if ((\\$EXIT_CODE > 0));then\n");
        script.append("    FAILED=true\n");
        script.append("fi\n");
        script.append("if [ \\$FAILED = true ]; then\n");
        script.append("    echo \"Error running your program\"\n");
        script.append("    exit\n");
        script.append("fi\n");
        script.append("EEOOFF\n");
        script.append("chmod +x vpl_execution\n");
        script.append("# +++++++++++++++++++++++++++++++++");
        return script.toString();
    }
}

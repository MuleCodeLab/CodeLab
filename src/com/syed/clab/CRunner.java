package com.syed.clab;

import com.syed.core.CodeRunner;
import com.syed.core.Util;

public class CRunner extends CodeRunner {
    
    public CRunner(CCompiler compiler) {
        super(compiler);
    }

    @Override
    public String scriptify() {
        if (this.getCompiler().getCode().getCount() == 0) {
            Util.ERROR("Cannot run 0 files");
            return null;
        }

        final String compiler = this.getLanguage().getCompiler();
        final String runner = this.getLanguage().getRunner();
        final String extension = this.getLanguage().getExtension();

        String compileFmt = "%s \\${prog1}%s -o %s &> grepLines.out\n";
        String runFmt = "%s\\${run1}\n";

        StringBuilder script = new StringBuilder();
        script.append("#! /bin/bash\n");
        script.append("# +++++++++++++++++++++++++++++++++\n");
        script.append("cat > vpl_execution <<EEOOFF\n");
        script.append("#! /bin/bash\n");
        script.append("EXIT_CODE=-1\n");
        script.append("FAILED=false\n");
        script.append(String.format("prog1=%s\n", this.getCompiler().getMainFile()));
        script.append(String.format("run1=%s\n", this.getRunFile()));
        script.append(String.format(compileFmt, compiler, extension, this.getRunFile()));
        script.append("EXIT_CODE=\\$?\n");
        script.append("if ((\\$EXIT_CODE > 0));then\n");
        script.append("    FAILED=true\n");
        script.append("fi\n");
        script.append("if [ \\$FAILED = true ]; then\n");
        script.append("    echo \"Error running your program\"\n");
        script.append("    cat grepLines.out\n");
        script.append("    exit\n");
        script.append("fi\n");
        script.append(String.format(runFmt, runner));
        script.append("if ((\\$? > 0)); then\n");
        script.append("    echo \"Runtime error in your code\"\n");
        script.append("fi\n");
        script.append("EEOOFF\n");
        script.append("chmod +x vpl_execution\n");
        script.append("# +++++++++++++++++++++++++++++++++");
        return script.toString();
    }
}

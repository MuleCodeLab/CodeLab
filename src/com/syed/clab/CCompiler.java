package com.syed.clab;

import com.syed.core.CodeCompiler;
import com.syed.core.Util;

public class CCompiler extends CodeCompiler {

    public CCompiler(CCode codefiles) {
        this(codefiles, "a.out");
    }

    public CCompiler(CCode codefiles, String outfile) {
        super(codefiles, outfile);
    }

    @Override
    protected String scriptify() {
        final int count = this.getCode().getCount();
        if (count == 0) {
            Util.ERROR("Cannot compile 0 files");
            return null;
        }

        final String compiler = this.getLanguage().getCompiler();
        final String extension = this.getLanguage().getExtension();

        StringBuilder script = new StringBuilder();
        script.append("#! /bin/bash\n");
        script.append("# +++++++++++++++++++++++++++++++++\n");
        script.append("cat > vpl_execution <<EEOOFF\n");
        script.append("#! /bin/bash\n");
        script.append("EXIT_CODE=-1\n");
        script.append("FAILED=false\n");

        script.append(String.format("prog1=%s\n", this.getMainFile()));

        script.append(
            String.format(
                "%s \\${prog1}%s -o %s &> grepLines.out\n", 
                compiler,
                extension,
                this.getOutFile()
            )
        );
        script.append("EXIT_CODE=\\$?\n");
        script.append("if ((\\$EXIT_CODE > 0));then\n");
        script.append("    FAILED=true\n");
        script.append(
            String.format("    echo \"%s failed to compile\"\n", this.getMainFile())
        );
        script.append("fi\n");

        script.append("if [ \\$FAILED = true ]; then\n");
        script.append("    echo \"Error compiling your program\"\n");
        script.append("    cat grepLines.out\n");
        script.append("    exit\n");
        script.append("else\n");
        script.append("    echo \"Compilation succeeded\"\n");
        script.append("    echo \"compiled: ==> true\"\n");
        script.append("fi\n");
        script.append("EEOOFF\n");
        script.append("chmod +x vpl_execution\n");
        script.append("# +++++++++++++++++++++++++++++++++");
        return script.toString();
    }
}

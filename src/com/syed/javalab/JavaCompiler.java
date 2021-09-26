package com.syed.javalab;

import com.syed.core.CodeCompiler;
import com.syed.core.Util;

public class JavaCompiler extends CodeCompiler {

    public JavaCompiler(JavaCode codefiles) {
        super(codefiles, null);
        this.setOutFile(this.getMainFile());
    }

    @Override
    public String scriptify() {
        final int count = this.getCode().getCount();
        if (count == 0) {
            Util.ERROR("Cannot compile 0 files");
            return null;
        }

        final String compiler = this.getLanguage().getCompiler();
        final String extension = this.getLanguage().getExtension();

        String[] titles = this.getCode().getFileTitles();
        StringBuilder script = new StringBuilder();
        script.append("#! /bin/bash\n");
        script.append("# +++++++++++++++++++++++++++++++++\n");
        script.append("cat > vpl_execution <<EEOOFF\n");
        script.append("#! /bin/bash\n");
        script.append("EXIT_CODE=-1\n");
        script.append("FAILED=false\n");
        if (count == 1) {
            script.append(String.format("prog1=%s\n", titles[0]));
            script.append(
                String.format(
                    "%s \\${prog1}%s  &> grepLines.out\n", 
                    compiler,
                    extension
                )
            );
            script.append("EXIT_CODE=\\$?\n");
            script.append("if ((\\$EXIT_CODE > 0));then\n");
            script.append("    FAILED=true\n");
            script.append(String.format("    echo \"%s failed to compile\"\n", titles[0]));
            script.append("fi\n");
        } else {
            for (String title : titles) {
                script.append(String.format("echo \"Compiling %s\"\n", title));
                script.append(
                    String.format(
                        "%s %s%s &> grepLines.out\n", 
                        compiler, 
                        title, 
                        extension
                    )
                );
                script.append("EXIT_CODE=\\$?\n");
                script.append("if ((\\$EXIT_CODE > 0));then\n");
                script.append("    FAILED=true\n");
                script.append(String.format("    echo \"%s failed to compile\"\n", title));
                script.append("fi\n");
            }
        }
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

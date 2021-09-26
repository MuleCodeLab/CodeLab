package com.syed.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class Code {
    
    private final Language language;
    private final HashMap<String, String> files;
    private String basePath;
    private String mainfile;

    public Code(Language language, Map<String, String> code, String mainfile) {
        this.language = language;
        this.mainfile = mainfile;
        this.files = new HashMap<>();
        this.files.putAll(code);
    }

    public Code(Language language, String path) {
        this(language, path, null);
        this.setMainFile(
            (this.getCount() == 1) ?
            this.files.keySet().iterator().next() : 
            (this.getCount() > 1) ?
            this.locateMainFile() : 
            null
        );
    }

    public Code(Language language, String path, String mainfile) {
        this.basePath = path.charAt(path.length()-1) == '/' ? path : path.concat("/");
        this.language = language;
        this.mainfile = mainfile;
        this.files = new HashMap<>();
        File[] filesIn = Util.filesInDir(this.basePath, this.language.getExtension());
        for (File file : filesIn) {
            this.files.put(file.getName(), Util.readlines(file.getAbsolutePath()));
        }
    }

    public String locateMainFile() {
        String regex = this.getLanguage().getMainRegex();
        for (Map.Entry<String, String> pair : this.files.entrySet()) {
            if (Util.checkRegex(regex, pair.getValue())) {
                return pair.getKey();
            }
        }
        Util.ERROR("NO file with main method found");
        return null;
    }

    public String[] getFileTitles() {
        String[] names = this.getFileNames();
        String[] titles = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            titles[i] = Util.fileTitle(names[i]);
        }
        return titles;
    }

    public String[] getFilePaths() {
        String[] names = this.getFileNames();
        String[] filePaths = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            filePaths[i] = basePath.concat(names[i]);
        }
        return filePaths;
    }

    public void print() {
        for (Map.Entry<String, String> pair : this.files.entrySet()) {
            Util.ECHO(
                String.format(
                    "[%s]\n+++++++++++\n\n%s\n+++++++++++\n",
                    pair.getKey(),
                    pair.getValue()    
                )
            );
        }
    }
    
    public void setMainFile(String mainfile) {
        this.mainfile = mainfile;
    }

    public String getMainFile() {
        return this.mainfile;
    }

    public Language getLanguage() {
        return this.language;
    }

    public String getBasePath() {
        return this.basePath;
    }
    
    public int getCount() {
        return this.files == null ? 0 : this.files.keySet().size();
    }

    public Map<String, String> getFileTree() {
        return this.files;
    }

    public String[] getFileNames() {
        String[] names = new String[this.files.keySet().size()];
        int i = 0; for (String s : this.files.keySet()) { names[i++] = s; }
        return names;
    }

}

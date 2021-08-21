package syed.code.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Code {
    
    private final int count;
    private final String basePath;
    private final Language language;
    private final HashMap<String, String> files;
    private String mainfile;

    public Code( Language language, String path) {
        this(language, path, null);
        this.setMainFile(
            (this.count == 1) ? 
            this.files.keySet().iterator().next() : 
            (this.count > 1) ?
            this.locateMainFile() : 
            null
        );
    }

    public Code(Language language, String path, String mainfile) {
        this.basePath = path.charAt(path.length()-1) == '/' ? path : path.concat("/");
        this.language = language;
        this.mainfile = mainfile;
        this.files = new HashMap<>();
        for (String file : this.getFileTitles()) {
            String code = Util.readlines(this.basePath+(file+this.language.getExtension()));
            this.files.put(file, code);
        }
        this.count = this.files.keySet().size();
    }

    public String locateMainFile() {
        String regex = this.getLanguage().getMainRegex();
        for (Map.Entry<String, String> pair : this.files.entrySet()) {
            if (Util.checkRegex(regex, pair.getValue())) {
                String name = pair.getKey();
                return name;
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
            Util.DEBUG(titles[i]);
        }
        return titles;
    }

    public String[] getFilePaths() {
        String[] names = getFileNames();
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
        return this.count;
    }

    public Map<String, String> getFileTree() {
        return this.files;
    }

    public String[] getFileNames() {
        return Util.getFileNames(this.basePath, this.language.getExtension());
    }

}

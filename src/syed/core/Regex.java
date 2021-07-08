package syed.core;

public class Regex {

    private String regex;
    private String comment;
    
    public Regex(String regex, String comment) {
        this.regex = regex;
        this.comment = comment;
    }

    public String use() {
        return this.regex;
    }

    public String getComment() {
        return this.comment;
    }

    public Regex follow(String s) {
        this.regex = this.regex.concat(s);
        return this;
    }
}
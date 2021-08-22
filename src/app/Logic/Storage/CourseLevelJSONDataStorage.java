package app.Logic.Storage;

import syed.code.core.Util;

public class CourseLevelJSONDataStorage {
    public String title;
    public boolean visible = true;

    public String getTitle() {
        return title;
    }

    public boolean isVisible() {
        return visible;
    }

    public void print() {
        Util.ECHO("Title: " + getTitle());
        Util.ECHO("Visible: " + isVisible());
    }

    public boolean isReady() {
        return this.title != null;
    }
}

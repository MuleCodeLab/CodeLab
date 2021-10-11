package app.storage;

import app.ui.LabSessionTableData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.syed.core.Util;


public class QuestionLevelJSONDataStorage {
    // local use only !!
    public LabLevelJSONDataStorage labData;

	public int questionNumber;
	public int labNumber;
	public String title;
	public String course;
	public ArrayList<String> files = new ArrayList<>();
	public String qid;

	// hidden question attributes
	public boolean hiddenQuestion;
	public String group, startDate, startHour, startMinute;
    public ArrayList<LabSessionTableData> sessions;

	public String lengthHour = "0", lengthMinute = "0";
	public LocalDateTime pgStart, pgEnd;

    public QuestionLevelJSONDataStorage(LabLevelJSONDataStorage labData) {
        this.labData = labData;
        sessions = new ArrayList<>();
    }


	public int getQuestionNumber() {
	    return questionNumber;
    }

    public int getLabNumber() {
	    return labNumber;
    }

    public String getTitle() {
	    return title;
    }

    public String getCourse() {
	    return course;
    }

    public String[] getFiles() {
	    String[] copyFileNames = new String[files.size()];
	    int i = 0;
	    for (String f : files) { copyFileNames[i++] = f; }
	    return copyFileNames;
    }

    public String getQid() {
	    return qid; // implement
    }

    public boolean isHiddenQuestion() {
	    return hiddenQuestion;
    }

    public LocalDateTime getStart() {
        String[] ddmmyyyy = startDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(startHour);
        int minute = Integer.parseInt(startMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public int getLength() {
	    return Integer.parseInt(lengthMinute) + (Integer.parseInt(lengthHour) * 60);
    }

    public List<LabSessionTableData> getSessions() {
	    return sessions;
    }

	public LocalDateTime getPGStart() {
	    return labData.getPGStart();
    }

	public LocalDateTime getPGEnd() {
	    return labData.getPGEnd();
    }

	public String getGroup() {
        return group;
    }

    public boolean isReady() {
	    boolean isReady =   labData.isReady() &&
                            getQuestionNumber() != 0 &&
                            getLabNumber() != 0 &&
                            getTitle() != null &&
                            getCourse() != null &&
                            getFiles() != null;
	    if (isHiddenQuestion()) {
            return isReady && getLength() != 0 &&
                    getSessions() != null &&
                    getPGStart() != null &&
                    getPGEnd() != null;
        }
	    return isReady;
    }


    public void print() {
        Util.ECHO("Question Number: "+getQuestionNumber());
        Util.ECHO("Lab Number: " + getLabNumber());
        Util.ECHO("Title: "+ getTitle());
        Util.ECHO("Course: "+getCourse());
        Util.ECHO("ID: "+ getQid());
        Util.ECHO("Files: "+Arrays.toString(getFiles()));
        Util.ECHO("Access Start: "+ labData.getAccessStart());
        Util.ECHO("Access End: "+ labData.getAccessEnd());
        Util.ECHO("CA Eval Start: "+ labData.getCAEvalStart());
        Util.ECHO("CA Eval End: "+ labData.getCAEvalEnd());
        if (isHiddenQuestion()) {
            Util.ECHO("Hidden: " + isHiddenQuestion());
            Util.ECHO("Group: " + getGroup());
            Util.ECHO("Length: " + getLength());
            Util.ECHO("Hidden Q Start: " + getStart());
            Util.ECHO("PG Start: " + labData.getPGStart());
            Util.ECHO("PG End: " + labData.getPGEnd());
            for (LabSessionTableData s : sessions) {
                Util.ECHO(s.getDate() + " | "+s.getTime());
            }
        }
    }
}


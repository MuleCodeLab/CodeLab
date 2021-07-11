package app;

import syed.code.core.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class JSONDataStorage {

    CourseLevelJSONData courseData = new CourseLevelJSONData();
    LabLevelJSONData labData = new LabLevelJSONData();
    QuestionLevelJSONData questionData = new QuestionLevelJSONData();

    void print() {
        Util.ECHO("------------------\n");
        courseData.print();
        Util.ECHO("------------------\n");
        labData.print();
        Util.ECHO("------------------\n");
        questionData.print();
        Util.ECHO("------------------\n");
    }
}


class CourseLevelJSONData {
    String title;
    boolean visible = true;

    String getTitle() {
        return title;
    }

    boolean isVisible() {
        return visible;
    }

    void print() {
        Util.ECHO("Title: "+ getTitle());
        Util.ECHO("Visible: "+isVisible());
    }
}

class LabLevelJSONData {

	int labNumber;
    String label;
	String accessStartDate, accessStartHour, accessStartMinute;
    String accessEndDate, accessEndHour, accessEndMinute;
    String caEvalStartDate, caEvalStartHour, caEvalStartMinute;
    String caEvalEndDate, caEvalEndHour, caEvalEndMinute;

    int getLabNumber() {
        return labNumber;
    }

    String getLabLabel() {
        return label;
    }

    LocalDateTime getAccessStart() {
        String[] ddmmyyyy = accessStartDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(accessStartHour);
        int minute = Integer.parseInt(accessStartMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    LocalDateTime getAccessEnd() {
        String[] ddmmyyyy = accessEndDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(accessEndHour);
        int minute = Integer.parseInt(accessEndMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    LocalDateTime getCAEvalStart() {
        String[] ddmmyyyy = caEvalStartDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(caEvalStartHour);
        int minute = Integer.parseInt(caEvalStartMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    LocalDateTime getCAEvalEnd() {
        String[] ddmmyyyy = caEvalEndDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(caEvalEndHour);
        int minute = Integer.parseInt(caEvalEndMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    void print() {
        Util.ECHO("Lab Label: "+ getLabLabel());
        Util.ECHO("Lab Number: " + getLabNumber());
        Util.ECHO("Access Start: "+ getAccessStart().toString());
        Util.ECHO("Access End: "+ getAccessEnd().toString());
        Util.ECHO("CA Eval Start: "+ getCAEvalStart().toString());
        Util.ECHO("CA Eval End: "+ getCAEvalEnd().toString());
    }

}

class QuestionLevelJSONData {
	int questionNumber; // int
	int labNumber; // int
	String title;
	String course;
	ArrayList<String> files = new ArrayList<>();
	String qid;

	// hidden question attributes
	boolean hiddenQuestion;

    String startDate, startHour, startMinute;
	int length;
	LocalDateTime pgStart;
	LocalDateTime pgEnd;
	String group;


	int getQuestionNumber() {
	    return questionNumber;
    }

    int getLabNumber() {
	    return labNumber;
    }

    String getTitle() {
	    return title;
    }

    String getCourse() {
	    return course;
    }

    String[] getFiles() {
	    String[] copyFileNames = new String[files.size()];
	    int i = 0;
	    for (String f : files) { copyFileNames[i++] = f; }
	    return copyFileNames;
    }

    String getQid() {
	    return null; // implement
    }

    boolean isHiddenQuestion() {
	    return hiddenQuestion;
    }

    LocalDateTime getStart() {
        String[] ddmmyyyy = startDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(startHour);
        int minute = Integer.parseInt(startMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    int getLength() {
	    return length;
    }

	LocalDateTime getPGStart() {
	    return pgStart;
    }

	LocalDateTime getPGEnd() {
	    return pgEnd;
    }

	String getGroup() {
        return group;
    }

    void print() {
        Util.ECHO("Question Number: "+getQuestionNumber());
        Util.ECHO("Lab Number: " + getLabNumber());
        Util.ECHO("Title: "+ getTitle());
        Util.ECHO("Course: "+getCourse());
        Util.ECHO("ID: "+ getQid());
        Util.ECHO("Files: "+Arrays.toString(getFiles()));
        Util.ECHO("Hidden: "+ isHiddenQuestion());
        Util.ECHO("Group: "+ getGroup());
        Util.ECHO("Length: "+ getLength());
        Util.ECHO("Start: "+ getStart());
        Util.ECHO("PG Start: "+ getPGStart());
        Util.ECHO("PG End: "+ getPGEnd());
    }
}


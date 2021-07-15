package app;

import syed.code.core.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONDataStorage {

    CourseLevelJSONData courseData = new CourseLevelJSONData();
    LabLevelJSONData labData = new LabLevelJSONData();
    QuestionLevelJSONData questionData = new QuestionLevelJSONData(labData);


    public boolean isReady() {
        return courseData.isReady() && labData.isReady() && questionData.isReady();
    }

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

    public boolean isReady() {
        return this.title != null;
    }
}

class LabLevelJSONData {

	int labNumber = 0;
    String label;
	String accessStartDate, accessStartHour, accessStartMinute;
    String accessEndDate, accessEndHour, accessEndMinute;
    String caEvalStartDate, caEvalStartHour, caEvalStartMinute;
    String caEvalEndDate, caEvalEndHour, caEvalEndMinute;
    String pgStartDate, pgStartHour, pgStartMinute;
    String pgEndDate, pgEndHour, pgEndMinute;



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

    LocalDateTime getPGStart() {
        String[] ddmmyyyy = pgStartDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(pgStartHour);
        int minute = Integer.parseInt(pgStartMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    LocalDateTime getPGEnd() {
        String[] ddmmyyyy = pgEndDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(pgEndHour);
        int minute = Integer.parseInt(pgEndMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }


    void print() {
        Util.ECHO("Lab Label: "+ getLabLabel());
        Util.ECHO("Lab Number: " + getLabNumber());
        Util.ECHO("Access Start: "+ getAccessStart().toString());
        Util.ECHO("Access End: "+ getAccessEnd().toString());
        Util.ECHO("CA Eval Start: "+ getCAEvalStart().toString());
        Util.ECHO("CA Eval End: "+ getCAEvalEnd().toString());
//        Util.ECHO("PG Start: "+ getPGStart().toString());
//        Util.ECHO("PG End: "+ getPGEnd().toString());
    }


    public boolean isReady() {
        return (
            getLabLabel() != null &&
            getLabNumber() != 0 &&
            getAccessStart() != null &&
            getAccessEnd() != null &&
            getCAEvalStart() != null &&
            getCAEvalEnd() != null
        );
    }

}

class QuestionLevelJSONData {
    // local use only
    LabLevelJSONData labData;
    QuestionLevelJSONData(LabLevelJSONData labData) {
        this.labData = labData;
        sessions = new ArrayList<>();
    }


	int questionNumber; // int
	int labNumber; // int
	String title;
	String course;
	ArrayList<String> files = new ArrayList<>();
	String qid;

	// hidden question attributes
	boolean hiddenQuestion;

    String startDate, startHour, startMinute;
	String lengthHour = "0", lengthMinute = "0";
	LocalDateTime pgStart, pgEnd;
	String group;

	ArrayList<LabSessionTableData> sessions;



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
	    return qid; // implement
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
	    return Integer.parseInt(lengthMinute) + (Integer.parseInt(lengthHour) * 60);
    }

    public List<LabSessionTableData> getSessions() {
	    return sessions;
    }

	LocalDateTime getPGStart() {
	    return labData.getCAEvalEnd();
    }

	LocalDateTime getPGEnd() {
	    return labData.getAccessEnd();
    }

	String getGroup() {
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


    void print() {
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


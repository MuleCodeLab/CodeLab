package app.storage;

import com.syed.core.Util;
import java.time.LocalDateTime;

public class LabLevelJSONDataStorage {

	public int labNumber = 0;
    public String label;
	public String accessStartDate, accessStartHour, accessStartMinute;
    public String accessEndDate, accessEndHour, accessEndMinute;
    public String caEvalStartDate, caEvalStartHour, caEvalStartMinute;
    public String caEvalEndDate, caEvalEndHour, caEvalEndMinute;
    public String pgStartDate, pgStartHour, pgStartMinute;
    public String pgEndDate, pgEndHour, pgEndMinute;

    public int getLabNumber() {
        return labNumber;
    }

    public String getLabLabel() {
        return label;
    }

    public LocalDateTime getAccessStart() {
        String[] ddmmyyyy = accessStartDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(accessStartHour == null ? "0" : accessStartHour);
        int minute = Integer.parseInt(accessStartMinute == null ? "0" : accessStartMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public LocalDateTime getAccessEnd() {
        String[] ddmmyyyy = accessEndDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(accessEndHour);
        int minute = Integer.parseInt(accessEndMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public LocalDateTime getCAEvalStart() {
        String[] ddmmyyyy = caEvalStartDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(caEvalStartHour);
        int minute = Integer.parseInt(caEvalStartMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public LocalDateTime getCAEvalEnd() {
        String[] ddmmyyyy = caEvalEndDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(caEvalEndHour);
        int minute = Integer.parseInt(caEvalEndMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public LocalDateTime getPGStart() {
        String[] ddmmyyyy = pgStartDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(pgStartHour);
        int minute = Integer.parseInt(pgStartMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public LocalDateTime getPGEnd() {
        String[] ddmmyyyy = pgEndDate.split("/");
        int day = Integer.parseInt(ddmmyyyy[0]);
        int month = Integer.parseInt(ddmmyyyy[1]);
        int year = Integer.parseInt(ddmmyyyy[2]);
        int hour = Integer.parseInt(pgEndHour);
        int minute = Integer.parseInt(pgEndMinute);

        return LocalDateTime.of(year, month, day, hour, minute);
    }


    public void print() {
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

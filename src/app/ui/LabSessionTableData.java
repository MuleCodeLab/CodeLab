package app.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDateTime;

// To populate lab session table
// do not mess with names !!
public class LabSessionTableData {
        private StringProperty group;
        private StringProperty date;
        private StringProperty time;

        public LabSessionTableData(String group, String date, String time) {
            this.group = new SimpleStringProperty(group);
            this.date = new SimpleStringProperty(date);
            this.time = new SimpleStringProperty(time);
        }

        public StringProperty groupProperty() {
            return group;
        }

        public StringProperty dateProperty() {
            return date;
        }

        public StringProperty timeProperty() {
            return time;
        }

        public String getGroup() {
            return group.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getTime() {
            return time.get();
        }

        public LocalDateTime getSessionStartTime() {
            String[] yyyymmdd = getDate().split("-");
            int year = Integer.parseInt(yyyymmdd[0]);
            int month = Integer.parseInt(yyyymmdd[1]);
            int dayOfMonth = Integer.parseInt(yyyymmdd[2]);

            String time = getTime();
            int hour = Integer.parseInt(time.substring(0,time.indexOf(':')));
            int min = Integer.parseInt(getTime().substring(time.indexOf(':')+1));

            return LocalDateTime.of(year, month, dayOfMonth, hour, min);
        }

        public void setGroup(String group) {
            this.group = new SimpleStringProperty(group);
        }

        public void setDate(String date) {
            this.date = new SimpleStringProperty(date);
        }

        public void setTime(String time) {
            this.time = new SimpleStringProperty(time);
        }

    }

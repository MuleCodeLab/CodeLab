package app;

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
            String[] ddmmyyyy = getDate().split("-");
            int year = Integer.parseInt(ddmmyyyy[0]);
            int month = Integer.parseInt(ddmmyyyy[1]);
            int dayOfMonth = Integer.parseInt(ddmmyyyy[2]);

            int hour = Integer.parseInt(getTime().substring(0,2));
            int min = Integer.parseInt(getTime().substring(3));

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

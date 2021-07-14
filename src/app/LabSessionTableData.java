package app;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

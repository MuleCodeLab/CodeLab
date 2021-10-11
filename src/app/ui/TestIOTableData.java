package app.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// To populate TestIO table
// do not mess with names !!
public class TestIOTableData {

    private StringProperty input;
    private StringProperty output;

    public TestIOTableData(String input, String output) {
        this.input = new SimpleStringProperty(input);
        this.output = new SimpleStringProperty(output);
    }

    public StringProperty inputProperty() {
        return input;
    }

    public StringProperty outputProperty() {
        return output;
    }

    public String getInput() {
        return input.get();
    }

    public String getOutput() {
        return output.get();
    }

    public void setInput(String input) {
        this.input = new SimpleStringProperty(input);
    }

    public void setOutput(String output) {
        this.output = new SimpleStringProperty(output);
    }
}

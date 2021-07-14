package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.util.Callback;
import javafx.util.StringConverter;
import syed.code.core.Regex;
import syed.code.core.Util;

public class Controller {

    JSONDataStorage jsonStorage = new JSONDataStorage();
    ObservableList<TableData> tableData = FXCollections.observableArrayList();


    public void hideCourseAndLabPanes(MouseEvent e) {
        Util.DEBUG("question level clicked");
        vb_CourseLevelData.setDisable(true);
        vb_LabLevelData.setDisable(true);
        vb_QuestionLevelData.setDisable(false);
    }

    public void hideCoursePane(MouseEvent e) {
        Util.DEBUG("Lab level clicked");
        vb_CourseLevelData.setDisable(true);
        vb_QuestionLevelData.setDisable(false);
        vb_LabLevelData.setDisable(false);
    }

    public void unHideAllPanes(MouseEvent e) {
        Util.DEBUG("all level clicked");
        vb_CourseLevelData.setDisable(false);
        vb_QuestionLevelData.setDisable(false);
        vb_LabLevelData.setDisable(false);
    }

    public void setCourseName(KeyEvent e) {
        jsonStorage.courseData.title = tf_CourseName.getText();
        jsonStorage.questionData.course = tf_CourseName.getText();
        Util.DEBUG(jsonStorage.courseData.title);
        Util.DEBUG(jsonStorage.questionData.course);
    }

    public void setLabLabel(KeyEvent e) {
        jsonStorage.labData.label = tf_LabLabel.getText().trim();
        Util.DEBUG(jsonStorage.labData.label);
    }

    public void setLabNumber(KeyEvent e) {
        int value = Integer.parseInt(tf_LabNumber.getText().trim());
        jsonStorage.labData.labNumber = value;
        jsonStorage.questionData.labNumber = value;
        Util.DEBUG(jsonStorage.labData.labNumber);
        Util.DEBUG(jsonStorage.questionData.labNumber);
    }

    public void setQuestionNumber(KeyEvent e) {
        jsonStorage.questionData.questionNumber = Integer.parseInt(tf_QuestionNumber.getText().trim());
        Util.DEBUG(jsonStorage.questionData.questionNumber);
    }

    public void setQuestionTitle(KeyEvent e) {
        jsonStorage.questionData.title = tf_QuestionTitle.getText().trim();
        Util.DEBUG(jsonStorage.questionData.title);
    }

    public void setAccessStartDate(Event e) {
        jsonStorage.labData.accessStartDate = dp_AccessStart.getEditor().getText().trim();
        Util.DEBUG(jsonStorage.labData.accessStartDate);
    }

    public void setAccessStartHour(KeyEvent e) {
        jsonStorage.labData.accessStartHour = tf_AccessStart_hour.getText().trim();
        Util.DEBUG(jsonStorage.labData.accessStartHour);
    }

    public void setAccessStartMinute(KeyEvent e) {
        jsonStorage.labData.accessStartMinute = tf_AccessStart_minute.getText().trim();
        Util.DEBUG(jsonStorage.labData.accessStartMinute);
    }

    public void setAccessEndDate(Event e) {
        jsonStorage.labData.accessEndDate = dp_AccessEnd.getEditor().getText().trim();
        Util.DEBUG(jsonStorage.labData.accessEndDate);
    }

    public void setAccessEndHour(KeyEvent e) {
        jsonStorage.labData.accessEndHour = tf_AccessEnd_hour.getText().trim();
        Util.DEBUG(jsonStorage.labData.accessEndHour);
    }

    public void setAccessEndMinute(KeyEvent e) {
        jsonStorage.labData.accessEndMinute = tf_AccessEnd_minute.getText().trim();
        Util.DEBUG(jsonStorage.labData.accessEndMinute);
    }

    public void setCAStartDate(Event e) {
        jsonStorage.labData.caEvalStartDate = dp_CaEvalStart.getEditor().getText().trim();
        Util.DEBUG(jsonStorage.labData.caEvalStartDate);
    }

    public void setCAStartHour(KeyEvent e) {
        jsonStorage.labData.caEvalStartHour = tf_CaEvalStart_hour.getText().trim();
        Util.DEBUG(jsonStorage.labData.caEvalStartHour);
    }

    public void setCAStartMinute(KeyEvent e) {
        jsonStorage.labData.caEvalStartMinute = tf_CaEvalStart_minute.getText().trim();
        Util.DEBUG(jsonStorage.labData.caEvalStartMinute);
    }

    public void setCAEndDate(Event e) {
        jsonStorage.labData.caEvalEndDate = dp_CaEvalEnd.getEditor().getText().trim();
        Util.DEBUG(jsonStorage.labData.caEvalEndDate);
    }

    public void setCAEndHour(KeyEvent e) {
        jsonStorage.labData.caEvalEndHour = tf_CaEvalEnd_hour.getText().trim();
        Util.DEBUG(jsonStorage.labData.caEvalEndHour);
    }

    public void setCAEndMinute(KeyEvent e) {
        jsonStorage.labData.caEvalEndMinute = tf_CaEvalEnd_minute.getText().trim();
        Util.DEBUG(jsonStorage.labData.caEvalEndMinute);
    }

    public void addCodeFile(MouseEvent e) {
        String file = tf_CodeFileNames.getText().trim();
        if (!file.isBlank()) {
            list_CodeFileNames.getItems().add(file);
            list_MainFile_FileNames.getItems().add(file);
            jsonStorage.questionData.files = new ArrayList<>(list_CodeFileNames.getItems());
            scriptData.files = jsonStorage.questionData.files;
        }
    }

    public void removeCodeFile(MouseEvent e) {
        if (list_CodeFileNames.getItems().size() > 0) {
            int index = list_CodeFileNames.getSelectionModel().getSelectedIndex();
            list_CodeFileNames.getItems().remove(index);
            list_MainFile_FileNames.getItems().remove(index);
            jsonStorage.questionData.files = new ArrayList<>(list_CodeFileNames.getItems());
            scriptData.files = jsonStorage.questionData.files;
        } else {
            Util.DEBUG("Empty");
        }
    }

    public void setQuestionHidden(ActionEvent e) {
        if (cb_IsHiddenQuestion.isSelected()) {
            jsonStorage.questionData.hiddenQuestion = true;
            vb_HiddenQuestionData.setDisable(false);
        } else {
            jsonStorage.questionData.hiddenQuestion = false;
            vb_HiddenQuestionData.setDisable(true);
        }
        Util.ECHO(jsonStorage.questionData.hiddenQuestion);
    }

    public void setLabSessionLengthHour(KeyEvent e) {
        String value = tf_Hidden_LabSessionLength_hours.getText().trim();
        if (!value.isBlank()) {
            jsonStorage.questionData.lengthHour = value;
        }
    }

    public void setLabSessionLengthMinute(KeyEvent e) {
        String value = tf_Hidden_LabSessionLength_minutes.getText().trim();
        if (!value.isBlank()) {
            jsonStorage.questionData.lengthMinute = value;
        }
    }

    public void setLabGroup(KeyEvent e) {
        String value = tf_Hidden_LabSessions_Group.getText().trim();
        if (!value.isBlank()) {
            jsonStorage.questionData.group = value;
        }
    }

    public void setLabSessionDate(Event e) {
        String value = dp_Hidden_LabSessionDate.getEditor().getText().trim();
        if (!value.isBlank()) {
            jsonStorage.questionData.startDate = value;
        }
    }

    public void setLabSessionHour(KeyEvent e) {
        String value = tf_Hidden_LabSessions_hour.getText().trim();
        if (!value.isBlank()) {
            jsonStorage.questionData.startHour = value;
        }
    }

    public void setLabSessionMinute(KeyEvent e) {
        String value = tf_Hidden_LabSessions_minute.getText().trim();
        if (!value.isBlank()) {
            jsonStorage.questionData.startMinute = value;
        }
    }

    public void printJSON(MouseEvent e) {
        Util.ECHO("Key Pressed");
        jsonStorage.print();
    }

    public void addToSessionsTable(MouseEvent e) {

        String group = jsonStorage.questionData.getGroup();
        String[] dateTime = jsonStorage.questionData.getStart().toString().split("T");

        Util.DEBUG(group);
        Util.DEBUG(Arrays.toString(dateTime));

        if (!group.isBlank() && dateTime.length == 2) {
            tableData.add(new TableData(group, dateTime[0], dateTime[1]));
            table_Hidden_LabSessions.setItems(tableData);
            tc_GroupColumn.setCellValueFactory(new PropertyValueFactory<TableData, String>("group"));
            tc_DateColumn.setCellValueFactory(new PropertyValueFactory<TableData, String>("date"));
            tc_TimeColumn.setCellValueFactory(new PropertyValueFactory<TableData, String>("time"));

        }

    }

    public void removeFromSessionsTable(MouseEvent e) {
        int index = table_Hidden_LabSessions.getSelectionModel().getSelectedIndex();
        tableData.remove(index);
        Util.DEBUG(tableData.size() + " - " + tableData);
    }



    public void setHiddenPgStartDate() {
        String value = dp_Hidden_PgStart_date.getEditor().getText().trim();
        if (!value.isBlank()) {
            jsonStorage.labData.pgStartDate = value;
        }
    }

    public void setHiddenPgStartHour() {
        String value = tf_Hidden_PgStart_hour.getText().trim();
        if (!value.isBlank()) {
            jsonStorage.labData.pgStartHour = value;
        }
    }

    public void setHiddenPgStartMinute() {
        String value = tf_Hidden_PgStart_minute.getText().trim();
        if (!value.isBlank()) {
            jsonStorage.labData.pgStartMinute = value;
        }
    }

    public void setHiddenPgEndDate() {
        String value = dp_Hidden_PgEnd_date.getEditor().getText().trim();
        if (!value.isBlank()) {
            jsonStorage.labData.pgEndDate = value;
        }
    }

    public void setHiddenPgEndHour() {
        String value = tf_Hidden_PgEnd_hour.getText().trim();
        if (!value.isBlank()) {
            jsonStorage.labData.pgEndHour = value;
        }
    }

    public void setHiddenPgEndMinute() {
        String value = tf_Hidden_PgEnd_minute.getText().trim();
        if (!value.isBlank()) {
            jsonStorage.labData.pgEndMinute = value;
        }
    }













    // --------------------------------------------------------------------------------
    // ---------------------------------------[HTML START]-----------------------------

    HTMLDataStorage htmlStorage = new HTMLDataStorage();

    public void printHTML(MouseEvent e) {
        Util.ECHO("Key Pressed");
        htmlStorage.print();
    }

    public void setDescription(KeyEvent e) {
        String value = ta_DescriptionBody.getText();
        if (!value.isBlank()) {
            htmlStorage.description = value;
        } else {
            htmlStorage.description = "";
        }
    }


    public void addImage(MouseEvent e) {
        String url = tf_Images.getText();
        if (!url.isBlank()) {
            htmlStorage.imagesURLs.add(url);
            list_Images.getItems().add(url);
            Util.DEBUG(url);
        }
    }


    public void removeImage(MouseEvent e) {
        if (list_Images.getItems().size() > 0) {
            int index = list_Images.getSelectionModel().getSelectedIndex();
            list_Images.getItems().remove(index);
            htmlStorage.imagesURLs.remove(index);
        } else {
            Util.DEBUG("Empty");
        }
    }


    public void addNote(MouseEvent e) {
        String note = tf_Notes.getText();
        if (!note.isBlank()) {
            htmlStorage.notes.add(note);
            list_Notes.getItems().add(note);
        }
    }


    public void removeNote(MouseEvent e) {
        if (list_Notes.getItems().size() > 0) {
            int index = list_Notes.getSelectionModel().getSelectedIndex();
            list_Notes.getItems().remove(index);
            htmlStorage.notes.remove(index);
        } else {
            Util.DEBUG("Empty");
        }
    }


    public void addCodeSample(MouseEvent e) {
        String code = ta_CodeSamples.getText().trim();
        if (!code.isBlank()) {
            htmlStorage.codeSamples.add(code);
            list_CodeSamples.getItems().add(code);
        }
    }

    public void removeCodeSample(MouseEvent e) {
        if (list_CodeSamples.getItems().size() > 0) {
            int index = list_CodeSamples.getSelectionModel().getSelectedIndex();
            list_CodeSamples.getItems().remove(index);
            htmlStorage.codeSamples.remove(index);
        } else {
            Util.DEBUG("Empty");
        }
    }

    public void loadCodeSampleIntoEditor(MouseEvent e) {
        if (list_CodeSamples.getItems().size() > 0) {
            int index = list_CodeSamples.getSelectionModel().getSelectedIndex();
            ta_CodeSamples.setText(list_CodeSamples.getItems().get(index));
        } else {
            Util.DEBUG("Empty");
        }
    }


    public void setOutputType(MouseEvent e) {
        if (rb_OutputType_SampleIO.isSelected()) {
            htmlStorage.typeOfOutput = 2;
            Util.DEBUG("2 select");
        } else if (rb_OutputType_Single.isSelected()) {
            htmlStorage.typeOfOutput = 1;
            Util.DEBUG("1 select");
        } else if (rb_OutputType_None.isSelected()) {
            htmlStorage.typeOfOutput = 0;
            Util.DEBUG("0 select");
        } else {
            Util.DEBUG("Invalid select");
        }
    }


    public void addSampleIO(MouseEvent e) {
        String input = ta_SampleIO_Input.getText().trim();
        String output = ta_SampleIO_Output.getText().trim();

        if (!input.isBlank() && !output.isBlank()) {
            htmlStorage.sampleInputs.add(input);
            htmlStorage.sampleOutputs.add(output);
            list_SampleIO.getItems().add(input);
            list_SampleIO.getItems().add(output);
        } else {
            Util.DEBUG("No input output");
        }
    }

    public void removeSampleIO(MouseEvent e) {
        if (list_SampleIO.getItems().size() > 1) {
            int index = list_SampleIO.getSelectionModel().getSelectedIndex();
            if (index > -1 && index < list_SampleIO.getItems().size()) {
                htmlStorage.sampleInputs.remove((index/2));
                htmlStorage.sampleOutputs.remove((index/2));
                list_SampleIO.getItems().remove(index);
                list_SampleIO.getItems().remove((index % 2 == 0) ? index :index-1);
                Util.DEBUG(htmlStorage.sampleInputs.toString());
                Util.DEBUG(htmlStorage.sampleOutputs.toString());
            }
        } else {
            Util.DEBUG("Empty");
        }
    }

    public void loadSampleIOIntoEditors(MouseEvent e) {
        int index = list_SampleIO.getSelectionModel().getSelectedIndex();
//        if (index > 0 && index < list_SampleIO.getItems().size()-1) {
            if (index % 2 == 0) { // input selected
                ta_SampleIO_Input.setText(list_SampleIO.getItems().get(index));
                ta_SampleIO_Output.setText(list_SampleIO.getItems().get(index+1));
            } else { // output selected
                ta_SampleIO_Input.setText(list_SampleIO.getItems().get(index-1));
                ta_SampleIO_Output.setText(list_SampleIO.getItems().get(index));
            }

//        }
    }

    public void setSingleOutput(KeyEvent e) {
        htmlStorage.singleExpectedOutput = ta_SingleOutput.getText().trim();
        Util.DEBUG(htmlStorage.singleExpectedOutput);
    }


    // --------------------------------------------------------------------------------
    // ---------------------------------------[HTML END]-------------------------------











    // --------------------------------------------------------------------------------
    // ---------------------------------------[SCRIPTS Start]--------------------------


    ScriptsDataStorage scriptData = new ScriptsDataStorage(jsonStorage.questionData);


    public void setJavaLab(MouseEvent e) {
        scriptData.labLanguage = "JAVA";
        Util.DEBUG(scriptData.labLanguage + " Lab selected");
    }

    public void setPythonLab(MouseEvent e) {
        scriptData.labLanguage = "PYTHON";
        Util.DEBUG(scriptData.labLanguage + " Lab selected");
    }

    public void setCLab(MouseEvent e) {
        scriptData.labLanguage = "C";
        Util.DEBUG(scriptData.labLanguage + " Lab selected");
    }


    public void selectMainFile(MouseEvent e) {
        scriptData.mainFile = list_MainFile_FileNames.getSelectionModel().getSelectedItem();
        Util.DEBUG("MainFile selected = " + scriptData.getMainFile());
    }

    public void startingCode(KeyEvent e) {
        String value = ta_StartCode.getText().trim();
        if (!value.isBlank()) {
            scriptData.codeBuffer = value;
        } else {
            Util.DEBUG("Code ta is empty");
        }
    }

    public void setStartingCode(MouseEvent e) {
        String code = scriptData.codeBuffer.trim();
        String file = list_MainFile_FileNames.getSelectionModel().getSelectedItem();
        if (!code.isBlank()) {
            if (scriptData.code.containsKey(file)) {
                scriptData.code.remove(file);
            }
            scriptData.code.put(file, code);
            list_StartCode_FileNames.getItems().clear();
            for (String f : scriptData.code.keySet()) {
                list_StartCode_FileNames.getItems().add(f);
            }
        } else {
            Util.DEBUG(code + " is empty");
        }
    }


    public void loadStartingCode() {
        String file = list_StartCode_FileNames.getSelectionModel().getSelectedItem();
        ta_StartCode.setText(scriptData.getCode().get(file));
    }


    public void setPredefinedRegex(Event e) {

        ObservableList<Regex> reglist = FXCollections.observableList(scriptData.getPredefinedRegex());
        combo_Regex_Predefined.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Regex> call(ListView<Regex> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Regex item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) { setText(item.getComment()); }
                    }
                };
            }
        });
        combo_Regex_Predefined.setItems(reglist);
        combo_Regex_Predefined.setConverter(new StringConverter<>() {
            @Override
            public String toString(Regex object) {
                String comment = object.getComment();
                tf_Regex_Custom.setText(object.use());
                return comment;
            }

            @Override
            public Regex fromString(String string) {
                return null;
            }
        });
    }



    public void addPredefinedRegex() {
        String file = list_MainFile_FileNames.getSelectionModel().getSelectedItem();
        Regex reg = combo_Regex_Predefined.getSelectionModel().getSelectedItem();

        Util.DEBUG(reg.getComment() + " | " + reg.use());

        if (scriptData.regexes.containsKey(file)) {
            List<Regex> reglist = scriptData.regexes.get(file);
            reglist.add(reg);
            scriptData.regexes.remove(file);
            scriptData.regexes.put(file, reglist);
        } else {
            scriptData.regexes.put(file, new ArrayList<>());
            scriptData.regexes.get(file).add(reg);
        }
    }


    public void addCustomRegex() {

    }


    public void addTestCaseIO(MouseEvent e) {
        String input = ta_TestCases_Input.getText().trim();
        String output = ta_TestCases_Output.getText().trim();

        if (!output.isBlank()) {
            if (!input.isBlank()) {
                scriptData.testCaseIOs.put(input, output);
            } else {
                scriptData.testCaseIOs.put("", output);
            }
            Util.DEBUG("[testIO]\nInput: "+input+"| "+scriptData.testCaseIOs.get(output));
        } else {
            Util.DEBUG("output cannot be empty");
        }
    }

    public void removeTestCaseIO(MouseEvent e) {

    }






    // --------------------------------------------------------------------------------
    // ---------------------------------------[SCRIPTS END]----------------------------


    @FXML
    private VBox vb_Level;
    @FXML
    private RadioButton rb_Level_Question;
    @FXML
    private ToggleGroup rb_LevelChooser;
    @FXML
    private RadioButton rb_Level_Lab;
    @FXML
    private RadioButton rb_Level_Course;
    @FXML
    private VBox vb_CourseLevelData;
    @FXML
    private TextField tf_CourseName;
    @FXML
    private VBox vb_LabLevelData;
    @FXML
    private TextField tf_LabLabel;
    @FXML
    private TextField tf_LabNumber;
    @FXML
    private DatePicker dp_AccessStart;
    @FXML
    private TextField tf_AccessStart_hour;
    @FXML
    private TextField tf_AccessStart_minute;
    @FXML
    private DatePicker dp_CaEvalStart;
    @FXML
    private TextField tf_CaEvalStart_hour;
    @FXML
    private TextField tf_CaEvalStart_minute;
    @FXML
    private DatePicker dp_CaEvalEnd;
    @FXML
    private TextField tf_CaEvalEnd_hour;
    @FXML
    private TextField tf_CaEvalEnd_minute;
    @FXML
    private VBox vb_QuestionLevelData;
    @FXML
    private TextField tf_QuestionNumber;
    @FXML
    private TextField tf_QuestionTitle;
    @FXML
    private VBox vb_JavaFileNames;
    @FXML
    private TextField tf_CodeFileNames;
//    @FXML
//    private TextField tf_JavaFileNames;
    @FXML
    private Button btn_JavaFileNames_Add;
    @FXML
    private Button btn_JavaFileNames_Delete;
    @FXML
    private ListView<String> list_CodeFileNames;
    @FXML
    private DatePicker dp_AccessEnd;
    @FXML
    private TextField tf_AccessEnd_hour;
    @FXML
    private TextField tf_AccessEnd_minute;
    @FXML
    private VBox vb_HiddenQuestionData;
    @FXML
    private CheckBox cb_IsHiddenQuestion;
    @FXML
    private TextField tf_Hidden_LabSessionLength_hours;
    @FXML
    private TextField tf_Hidden_LabSessionLength_minutes;
    @FXML
    private VBox vb_LabSessions;
    @FXML
    private TextField tf_Hidden_LabSessions_Group;
    @FXML
    private HBox hb_LabSessions;
    @FXML
    private DatePicker dp_Hidden_LabSessionDate;
    @FXML
    private TextField tf_Hidden_LabSessions_hour;
    @FXML
    private TextField tf_Hidden_LabSessions_minute;
    @FXML
    private Button btn_LabSessions_Add;
    @FXML
    private Button btn_LabSessions_Delete;
    @FXML
    private TableColumn tc_GroupColumn;
    @FXML
    private TableColumn tc_DateColumn;
    @FXML
    private TableColumn tc_TimeColumn;
    @FXML
    private TableView table_Hidden_LabSessions;
    @FXML
    private DatePicker dp_Hidden_PgStart_date;
    @FXML
    private TextField tf_Hidden_PgStart_hour;
    @FXML
    private TextField tf_Hidden_PgStart_minute;
    @FXML
    private DatePicker dp_Hidden_PgEnd_date;
    @FXML
    private TextField tf_Hidden_PgEnd_hour;
    @FXML
    private TextField tf_Hidden_PgEnd_minute;
    @FXML
    private VBox vb_DescriptionBody;
    @FXML
    private TextArea ta_DescriptionBody;
    @FXML
    private VBox vb_Images;
    @FXML
    private TextField tf_Images;
    @FXML
    private Button btn_Images_Add;
    @FXML
    private Button btn_Images_Delete;
    @FXML
    private ListView<String> list_Images;
    @FXML
    private VBox vb_Notes;
    @FXML
    private TextField tf_Notes;
    @FXML
    private Button btn_Notes_Add;
    @FXML
    private Button btn_Notes_Delete;
    @FXML
    private ListView<String> list_Notes;
    @FXML
    private VBox vb_CodeSamples;
    @FXML
    private TextArea ta_CodeSamples;
    @FXML
    private Button btn_CodeSamples_Add;
    @FXML
    private Button btn_CodeSamples_Delete;
    @FXML
    private ListView<String> list_CodeSamples;
    @FXML
    private VBox vb_OutputType;
    @FXML
    private RadioButton rb_OutputType_SampleIO;
    @FXML
    private ToggleGroup rb_TypeOfOutputChooser;
    @FXML
    private RadioButton rb_OutputType_Single;
    @FXML
    private RadioButton rb_OutputType_None;
    @FXML
    private VBox vb_SampleIO;
    @FXML
    private TextArea ta_SampleIO_Input;
    @FXML
    private TextArea ta_SampleIO_Output;
    @FXML
    private Button btn_SampleIO_Add;
    @FXML
    private Button btn_SampleIO_Delete;
    @FXML
    private ListView<String> list_SampleIO;
    @FXML
    private VBox vb_SingleOutput;
    @FXML
    private TextArea ta_SingleOutput;
    

    // -------- NEW ITEMS 13/07/2021 -------- //
    @FXML
    private Button btn_JSON_Generate;
    @FXML
    private Button btn_HTML_Generate;
    @FXML
    private VBox vb_Lang;
    @FXML
    private RadioButton rb_Lang_Java;
    @FXML
    private ToggleGroup rb_Lang;
    @FXML
    private RadioButton rb_Lang_Python;
    @FXML
    private RadioButton rb_Lang_C;
    @FXML
    private VBox vb_MainFile;
    @FXML
    private ListView<String> list_MainFile_FileNames;
    @FXML
    private Button btn_MainFile_Set;
    @FXML
    private TextField tf_MainFile_Uneditable;
    @FXML
    private VBox vb_StartCode;
    @FXML
    private TextArea ta_StartCode;
    @FXML
    private Button btn_StartCode_Set;
    @FXML
    private ListView<String> list_StartCode_FileNames;
    @FXML
    private VBox vb_Regex;
    @FXML
    private ListView<?> list_Regex_FileNames;
    @FXML
    private ComboBox<Regex> combo_Regex_Predefined;
    @FXML
    private Button btn_Regex_Add_Predefined;
    @FXML
    private TextField tf_Regex_Custom;
    @FXML
    private Button btn_Regex_Add_Custom;
    @FXML
    private ListView<?> list_Regex_Regexes;
    @FXML
    private Button btn_Regex_Delete;
    @FXML
    private VBox vb_TestCases;
    @FXML
    private TextArea ta_TestCases_Input;
    @FXML
    private TextArea ta_TestCases_Output;
    @FXML
    private Button btn_TestCases_Add;
    @FXML
    private Button btn_TestCases_Delete;
    @FXML
    private ListView<?> list_TestCases;
    @FXML
    private VBox vb_gradeProportions;
    @FXML
    private TextField tf_GradeProportions_Compile;
    @FXML
    private TextField tf_GradeProportions_Regex;
    @FXML
    private TextField tf_GradeProportions_TestCases;
    @FXML
    private TextField tf_GradeProportions_Sum_Uneditable;
    @FXML
    private Button btn_Scripts_GenerateAndFinish;
}

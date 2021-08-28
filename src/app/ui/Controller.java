package app.ui;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import syed.code.core.Regex;
import syed.code.core.TestIO;

import app.logic.FileProducer;
import app.logic.TestIOTableData;
import app.logic.LabSessionTableData;
import app.logic.storage.JSONDataStorage;
import app.logic.storage.HTMLDataStorage;
import app.logic.storage.ScriptsDataStorage;


public class Controller {

    private JSONDataStorage jsonStorage;
    private HTMLDataStorage htmlStorage;
    private ScriptsDataStorage scriptStorage;
    private FileProducer fileProducer;
    private ObservableList<LabSessionTableData> labSessionTableData;
    private ObservableList<TestIOTableData> testIOTableData;

    public Controller() throws IOException {
        jsonStorage = new JSONDataStorage();
        htmlStorage = new HTMLDataStorage();
        scriptStorage = new ScriptsDataStorage(jsonStorage.questionData);
        fileProducer = new FileProducer(jsonStorage, htmlStorage, scriptStorage);
        labSessionTableData = FXCollections.observableArrayList();
        testIOTableData = FXCollections.observableArrayList();
    }

    public void setCourseName(KeyEvent e) {
        jsonStorage.courseData.title = tf_CourseName.getText();
        jsonStorage.questionData.course = tf_CourseName.getText();
    }

    public void setLabLabel(KeyEvent e) {
        jsonStorage.labData.label = tf_LabLabel.getText().trim();
    }

    public void setLabNumber(KeyEvent e) {
        int value = Integer.parseInt(tf_LabNumber.getText().trim());
        jsonStorage.labData.labNumber = value;
        jsonStorage.questionData.labNumber = value;
    }

    public void setQuestionNumber(KeyEvent e) {
        jsonStorage.questionData.questionNumber = tf_QuestionNumber.getText().trim();
    }

    public void setQuestionTitle(KeyEvent e) {
        jsonStorage.questionData.title = tf_QuestionTitle.getText().trim();
    }

    public void setAccessStartDate(Event e) {
        jsonStorage.labData.accessStartDate = dp_AccessStart.getEditor().getText().trim();
    }

    public void setAccessStartHour(KeyEvent e) {
        jsonStorage.labData.accessStartHour = tf_AccessStart_hour.getText().trim();
    }

    public void setAccessStartMinute(KeyEvent e) {
        jsonStorage.labData.accessStartMinute = tf_AccessStart_minute.getText().trim();
    }

    public void setAccessEndDate(Event e) {
        jsonStorage.labData.accessEndDate = dp_AccessEnd.getEditor().getText().trim();
    }

    public void setAccessEndHour(KeyEvent e) {
        jsonStorage.labData.accessEndHour = tf_AccessEnd_hour.getText().trim();
    }

    public void setAccessEndMinute(KeyEvent e) {
        jsonStorage.labData.accessEndMinute = tf_AccessEnd_minute.getText().trim();
    }

    public void setCAStartDate(Event e) {
        jsonStorage.labData.caEvalStartDate = dp_CaEvalStart.getEditor().getText().trim();
    }

    public void setCAStartHour(KeyEvent e) {
        jsonStorage.labData.caEvalStartHour = tf_CaEvalStart_hour.getText().trim();
    }

    public void setCAStartMinute(KeyEvent e) {
        jsonStorage.labData.caEvalStartMinute = tf_CaEvalStart_minute.getText().trim();
    }

    public void setCAEndDate(Event e) {
        jsonStorage.labData.caEvalEndDate = dp_CaEvalEnd.getEditor().getText().trim();
    }

    public void setCAEndHour(KeyEvent e) {
        jsonStorage.labData.caEvalEndHour = tf_CaEvalEnd_hour.getText().trim();
    }

    public void setCAEndMinute(KeyEvent e) {
        jsonStorage.labData.caEvalEndMinute = tf_CaEvalEnd_minute.getText().trim();
    }

    public void addCodeFile(MouseEvent e) {
        String file = tf_CodeFileNames.getText().trim();
        if (!file.isBlank()) {
            list_CodeFileNames.getItems().add(file);
            list_CodeFileOptions_Files.getItems().add(file);
            jsonStorage.questionData.files = new ArrayList<>(list_CodeFileNames.getItems());
            scriptStorage.files = new ArrayList<>(list_CodeFileNames.getItems());
        }
    }

    public void removeCodeFile(MouseEvent e) {
        if (list_CodeFileNames.getItems().size() > 0) {
            int index = list_CodeFileNames.getSelectionModel().getSelectedIndex();
            list_CodeFileNames.getItems().remove(index);
            list_CodeFileOptions_Files.getItems().remove(index);
            jsonStorage.questionData.files = new ArrayList<>(list_CodeFileNames.getItems());
            scriptStorage.files = new ArrayList<>(list_CodeFileNames.getItems());
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
        jsonStorage.print();
    }

    public void addToSessionsTable(MouseEvent e) {
        String group = jsonStorage.questionData.getGroup();
        String[] dateTime = jsonStorage.questionData.getStart().toString().split("T");

        if (!group.isBlank() && dateTime.length == 2) {
            labSessionTableData.add(new LabSessionTableData(group, dateTime[0], dateTime[1]));
            jsonStorage.questionData.sessions.clear();
            jsonStorage.questionData.sessions.addAll(labSessionTableData);
            table_Hidden_LabSessions.setItems(labSessionTableData);
            tc_GroupColumn.setCellValueFactory(new PropertyValueFactory<LabSessionTableData, String>("group"));
            tc_DateColumn.setCellValueFactory(new PropertyValueFactory<LabSessionTableData, String>("date"));
            tc_TimeColumn.setCellValueFactory(new PropertyValueFactory<LabSessionTableData, String>("time"));
        }
    }

    public void removeFromSessionsTable(MouseEvent e) {
        int index = table_Hidden_LabSessions.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            labSessionTableData.remove(index);
            jsonStorage.questionData.sessions.clear();
            jsonStorage.questionData.sessions.addAll(labSessionTableData);
        }
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


    public void printHTML(MouseEvent e) {
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
        }
    }

    public void removeImage(MouseEvent e) {
        if (list_Images.getItems().size() > 0) {
            int index = list_Images.getSelectionModel().getSelectedIndex();
            list_Images.getItems().remove(index);
            htmlStorage.imagesURLs.remove(index);
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
        }
    }

    public void loadCodeSampleIntoEditor(MouseEvent e) {
        if (list_CodeSamples.getItems().size() > 0) {
            int index = list_CodeSamples.getSelectionModel().getSelectedIndex();
            ta_CodeSamples.setText(list_CodeSamples.getItems().get(index));
        }
    }

    public void setOutputType(MouseEvent e) {
        if (rb_OutputType_SampleIO.isSelected()) {
            htmlStorage.typeOfOutput = 2;
            vb_SampleIO.setDisable(false);
            vb_SingleOutput.setDisable(true);
        } else if (rb_OutputType_Single.isSelected()) {
            htmlStorage.typeOfOutput = 1;
            vb_SampleIO.setDisable(true);
            vb_SingleOutput.setDisable(false);
        } else if (rb_OutputType_None.isSelected()) {
            htmlStorage.typeOfOutput = 0;
            vb_SampleIO.setDisable(true);
            vb_SingleOutput.setDisable(true);
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
           Widget.ERROR("Invalid Input/Output.", "Cannot add empty sample input/output.");
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
            }
        } else {
            Widget.ERROR("Cannot remove sample IO.", "Sample IO list is empty.");
        }
    }

    public void loadSampleIOIntoEditors(MouseEvent e) {
        int index = list_SampleIO.getSelectionModel().getSelectedIndex();
        if (index % 2 == 0) { // input selected
            ta_SampleIO_Input.setText(list_SampleIO.getItems().get(index));
            ta_SampleIO_Output.setText(list_SampleIO.getItems().get(index+1));
        } else { // output selected
            ta_SampleIO_Input.setText(list_SampleIO.getItems().get(index-1));
            ta_SampleIO_Output.setText(list_SampleIO.getItems().get(index));
        }
    }

    public void setSingleOutput(KeyEvent e) {
        htmlStorage.singleExpectedOutput = ta_SingleOutput.getText().trim();
    }


    // --------------------------------------------------------------------------------
    // ---------------------------------------[HTML END]-------------------------------



    // --------------------------------------------------------------------------------
    // ---------------------------------------[SCRIPTS Start]--------------------------


    public void setLabLanguage(MouseEvent e) {
        if (rb_Lang_Java.isSelected()) {
            scriptStorage.labLanguage = "JAVA";
        } else if (rb_Lang_Python.isSelected()) {
            scriptStorage.labLanguage = "PYTHON";
        } else if (rb_Lang_C.isSelected()) {
            scriptStorage.labLanguage = "C";
        }
    }

    public void selectFile(MouseEvent e) {
        int index = list_CodeFileOptions_Files.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            String value = list_CodeFileOptions_Files.getItems().get(index).trim();
            label_SelectedFileDisplay.setText(value);
            label_SelectedFileDisplay.setTextFill(Color.GREY);
            if (scriptStorage.getCode().containsKey(value)) {
                ta_CodeFileOptions_StartCode_StartCode.setText(scriptStorage.getCode().get(value));
            } else {
                ta_CodeFileOptions_StartCode_StartCode.clear();
            }
        }
    }

    public void selectAsMainFile(MouseEvent e) {
        int index = list_CodeFileOptions_Files.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            String filename = list_CodeFileOptions_Files.getItems().get(index);
           if (filename.equals(label_SelectedFileDisplay.getText())) {
               scriptStorage.mainFile = filename;
               label_CodeFileOptions_SetAsMain_MainFileDisplay.setText(scriptStorage.getMainFile());
               label_CodeFileOptions_SetAsMain_MainFileDisplay.setTextFill(Color.GREY);
           }
        }
    }

    public void setStartingCode(KeyEvent e) {
        String code = ta_CodeFileOptions_StartCode_StartCode.getText().trim();
        String file = label_SelectedFileDisplay.getText();
        if (!code.isBlank() && !file.isBlank() && !file.equals("Filename.ext")) {
            if (scriptStorage.code.containsKey(file)) {
                scriptStorage.code.remove(file);
            }
            scriptStorage.code.put(file, code);
        }
    }

    public void setPredefinedRegex(Event e) {
        ObservableList<Regex> reglist = FXCollections.observableList(scriptStorage.getPredefinedRegex());
        combo_CodeFileOptions_Regexes_Predefined.setCellFactory(new Callback<>() {
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
        combo_CodeFileOptions_Regexes_Predefined.setItems(reglist);
        combo_CodeFileOptions_Regexes_Predefined.setConverter(new StringConverter<>() {
            @Override
            public String toString(Regex object) {
                String comment = object.getComment(), reg = object.use();
                ta_CodeFileOptions_Regexes_RegexEditable.setText(reg);
                tf_CodeOptions_Regexes_Comment.setText(comment);
                return comment;
            }

            @Override
            public Regex fromString(String string) {
                return null;
            }
        });
    }

    public void addRegex(MouseEvent e) {
        String file = label_SelectedFileDisplay.getText();
        String reg = ta_CodeFileOptions_Regexes_RegexEditable.getText();
        String comment = tf_CodeOptions_Regexes_Comment.getText();

        if (!file.isBlank() && !reg.isBlank() && !comment.isBlank()) {
            if (scriptStorage.regexes.containsKey(file)) {
                List<Regex> reglist = scriptStorage.regexes.get(file);
                reglist.add(new Regex(reg, comment));
                scriptStorage.regexes.remove(file);
                scriptStorage.regexes.put(file, reglist);
            } else {
                scriptStorage.regexes.put(file, new ArrayList<>());
                scriptStorage.regexes.get(file).add(new Regex(reg, comment));
            }

            List<String> regexes = new ArrayList<>();
            for (Map.Entry<String, List<Regex>> item : scriptStorage.getRegex().entrySet()) {
                for (Regex regex : item.getValue()) {
                    regexes.add(item.getKey() + "|>  " + regex.use());
                }
            }
            list_CodeFileOptions_Regexes.setItems(FXCollections.observableList(regexes));
        }
    }

    public void removeRegex(MouseEvent e) {
        int validIndex = list_CodeFileOptions_Regexes.getSelectionModel().getSelectedIndex();
        if (validIndex > -1) {
            String selectedItem = list_CodeFileOptions_Regexes.getItems().get(validIndex);
            int splitIndex = selectedItem.indexOf("|> ");
            String filename = selectedItem.substring(0, splitIndex);
            String regex = selectedItem.substring(splitIndex + 4);// 4 for |>

            if (!filename.isBlank() && scriptStorage.getRegex().containsKey(filename)) {
                List<Regex> regexlist = scriptStorage.getRegex().get(filename);
                for (Regex R : regexlist) {
                    if (R.use().equals(regex)) {
                        regexlist.remove(R);
                        list_CodeFileOptions_Regexes.getItems().remove(validIndex);
                        break;
                    }
                }
            }
        }
    }

    public void loadRegexIntoEditors(MouseEvent e) {
        int validIndex = list_CodeFileOptions_Regexes.getSelectionModel().getSelectedIndex();
        if (validIndex > -1) {
            String selectedItem = list_CodeFileOptions_Regexes.getItems().get(validIndex);
            int splitIndex = selectedItem.indexOf("|> ");
            String filename = selectedItem.substring(0, splitIndex);
            String regex = selectedItem.substring(splitIndex + 4);// 4 for |>

            if (!filename.isBlank() && scriptStorage.getRegex().containsKey(filename)) {
                ta_CodeFileOptions_Regexes_RegexEditable.setText(regex);
                for (Regex R : scriptStorage.getRegex().get(filename)) {
                    if (R.use().equals(regex)) {
                        tf_CodeOptions_Regexes_Comment.setText(R.getComment());
                    }
                }
            }
        }
    }


    public void addTestCaseIO(MouseEvent e) {
        String input = ta_TestCases_Input.getText().trim();
        String output = ta_TestCases_Output.getText().trim();

        if (!output.isBlank()) {
            testIOTableData.add(new TestIOTableData(input, output));
            table_TestCases.setItems(testIOTableData);
            scriptStorage.testCaseIOs.clear();
            for (TestIOTableData s : testIOTableData) {
                scriptStorage.testCaseIOs.add(new TestIO(s.getInput(), s.getOutput()));
            }
            tc_InputColumn.setCellValueFactory(new PropertyValueFactory<TestIOTableData, String>("input"));
            tc_OutputColumn.setCellValueFactory(new PropertyValueFactory<TestIOTableData, String>("output"));
        } else {
            Widget.ERROR("Invalid Input/Output.", "At-least one output is required.");
        }
    }

    public void removeTestCaseIO(MouseEvent e) {
        int index = table_TestCases.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            testIOTableData.remove(index);
            scriptStorage.testCaseIOs.clear();
            for (TestIOTableData s : testIOTableData) {
                scriptStorage.testCaseIOs.add(new TestIO(s.getInput(), s.getOutput()));
            }
        } else {
            Widget.ERROR("Cannot remove testcase IO.", "Testcase IO list is empty.");
        }
    }


    public void setCompileGrade(KeyEvent e) {
        String value = tf_GradeProportions_Compile.getText().trim();
        if (!value.isBlank()) {
            scriptStorage.compileGrade = value;
            tf_GradeProportions_Sum_Uneditable.setText(Integer.toString(scriptStorage.getTotalGrade()));
        }
    }

    public void setRegexGrade(KeyEvent e) {
        String value = tf_GradeProportions_Regex.getText().trim();
        if (!value.isBlank()) {
            scriptStorage.regexGrade = value;
            tf_GradeProportions_Sum_Uneditable.setText(Integer.toString(scriptStorage.getTotalGrade()));
        }
    }

    public void setTestCaseIOGrade(KeyEvent e) {
        String value = tf_GradeProportions_TestCases.getText().trim();
        if (!value.isBlank()) {
            scriptStorage.tcGrade = value;
            tf_GradeProportions_Sum_Uneditable.setText(Integer.toString(scriptStorage.getTotalGrade()));
        }
    }


    public void printSciptData(MouseEvent e) {
        scriptStorage.print();
    }


    // --------------------------------------------------------------------------------
    // ---------------------------------------[SCRIPTS END]----------------------------



    // --------------------------------------------------------------------------------
    // ---------------------------------------[File GENERATION]------------------------


    public void generateJSONFiles(MouseEvent e) {
        try {
            if (fileProducer.json()) {
                Widget.OK("Success!", "JSON files created successfully.");
            } else {
                Widget.ERROR("Failure!","Please provide all required information.");
            }
        } catch (IOException err) {
            Widget.ERROR("Unexpected Error!", err.getMessage());
        }
    }

    public void generateHTMLFiles(MouseEvent e) {
        try {
            if (fileProducer.html()) {
                Widget.OK("Success!","HTML files created successfully.");
            } else {
                Widget.ERROR("Failure!","Please provide all required information.");
            }
        } catch (IOException err) {
            Widget.ERROR("Unexpected Error!", err.getMessage());
        }
    }

    public void generateScriptFiles(MouseEvent e) {
        try {
            if (fileProducer.scripts()) {
                Widget.OK("Success!","Script files created successfully");
            } else {
                Widget.ERROR("Failure!","Please provide all required information.");
            }
        } catch (IOException err) {
            Widget.ERROR("Unexpected Error!", err.getMessage());
        }
    }


    // --------------------------------------------------------------------------------
    // ---------------------------------------[END]------------------------------------



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
    private ListView<String> list_CodeFileOptions_Files;
    @FXML
    private Label label_SelectedFileDisplay;
    @FXML
    private Label label_CodeFileOptions_SetAsMain_MainFileDisplay;
    @FXML
    private Button btn_MainFile_Set;
    @FXML
    private TextField tf_MainFile_Uneditable;
    @FXML
    private VBox vb_StartCode;
    @FXML
    private TextArea ta_CodeFileOptions_StartCode_StartCode;
    @FXML
    private Button btn_StartCode_Set;
    @FXML
    private ListView<String> list_StartCode_FileNames;
    @FXML
    private VBox vb_Regex;
    @FXML
    private ListView<String> list_CodeFileOptions_Regexes;
    @FXML
    private ComboBox<Regex> combo_CodeFileOptions_Regexes_Predefined;
    @FXML
    private Button btn_Regex_Add_Predefined;
    @FXML
    private TextArea ta_CodeFileOptions_Regexes_RegexEditable;
    @FXML
    private TextField tf_CodeOptions_Regexes_Comment;
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
    private TableView<TestIOTableData> table_TestCases;
    @FXML
    private TableColumn tc_InputColumn;
    @FXML
    private TableColumn tc_OutputColumn;
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
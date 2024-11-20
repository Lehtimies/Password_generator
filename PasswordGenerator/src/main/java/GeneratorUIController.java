import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

public class GeneratorUIController {
    @FXML private TableView<Password> passwordDisplay;
    @FXML private TableColumn<Password, Integer> passwordNumberColumn;
    @FXML private TableColumn<Password, String> passwordColumn;
    @FXML private TextField numberOfPasswordsField;
    @FXML private ChoiceBox<String> passwordLengthChoiceBox;
    @FXML private CheckBox includeNumbersBox;
    @FXML private CheckBox includeLowercaseBox;
    @FXML private CheckBox includeUppercaseBox;
    @FXML private CheckBox includeSpecialBox;
    @FXML private CheckBox noDuplicateBox;
    @FXML private CheckBox noSequentialBox;

    private static final ObservableList<String> passwordLengths = FXCollections.observableArrayList("6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25");
    private ObservableList<Password> passwordList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        passwordLengthChoiceBox.setItems(passwordLengths);
        passwordNumberColumn.setCellValueFactory(new PropertyValueFactory<Password, Integer>("passwordNumber"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Password, String>("password"));
        passwordDisplay.setItems(passwordList);
        passwordDisplay.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void generatePasswordBtn() {
        // TODO: Add code
        clearDisplay();
        int numberOfPasswords = Integer.parseInt(numberOfPasswordsField.getText());
        // Hard coded test password
        for (int i = 1; i < numberOfPasswords + 1; i++) {
            passwordList.add(new Password(i, "TestPassword"));
        }
    }

    @FXML
    private void copyPasswordBtn() {
        // TODO: Add code
        String passwordToClipboard = passwordDisplay.getSelectionModel().getSelectedItem().getPassword();
        StringSelection stringSelection = new StringSelection(passwordToClipboard);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    @FXML
    private void copyAllPasswordsBtn() {
        // TODO: Add code
    }

    private void clearDisplay() {
        passwordList.clear();
        passwordDisplay.setItems(passwordList);
    }
}

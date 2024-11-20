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

    PasswordGenerator generator;

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
        clearDisplay();
        int passwordLength;
        int numberOfPasswords;
        try {
            passwordLength = Integer.parseInt(passwordLengthChoiceBox.getValue());
            numberOfPasswords = Integer.parseInt(numberOfPasswordsField.getText());
        } catch (NumberFormatException e) {
            ErrorWindowCreator errorWindow = new ErrorWindowCreator("Error", null, "Select valid password length or enter a valid value for 'Number of passwords'");
            errorWindow.show();
            return;
        }
        generator = new PasswordGenerator(passwordLength, includeNumbersBox.isSelected(), includeLowercaseBox.isSelected(), includeUppercaseBox.isSelected(), includeSpecialBox.isSelected(), noDuplicateBox.isSelected(), noSequentialBox.isSelected());
        for (int i = 1; i < numberOfPasswords + 1; i++) {
            String password = generator.generatePassword();
            passwordList.add(new Password(i, password));
        }
    }

    @FXML
    private void copyPasswordBtn() {
        String passwordToClipboard = passwordDisplay.getSelectionModel().getSelectedItem().getPassword();
        StringSelection stringSelection = new StringSelection(passwordToClipboard);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    @FXML
    private void copyAllPasswordsBtn() {
        String passwordsToClipboard = "";
        for (Password password : passwordList) {
            passwordsToClipboard += password.getPassword() + "\n";
        }
        StringSelection stringSelection = new StringSelection(passwordsToClipboard);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private void clearDisplay() {
        passwordList.clear();
        passwordDisplay.setItems(passwordList);
    }
}

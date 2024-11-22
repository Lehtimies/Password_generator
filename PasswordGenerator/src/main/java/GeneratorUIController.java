import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

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

    private final static int MAX_NUMBER_OF_PASSWORDS = 1000000;
    private static final ObservableList<String> passwordLengths = FXCollections.observableArrayList("6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25");
    private final ObservableList<Password> passwordList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        passwordLengthChoiceBox.setItems(passwordLengths);
        passwordLengthChoiceBox.setValue("20");
        passwordNumberColumn.setCellValueFactory(new PropertyValueFactory<Password, Integer>("passwordNumber"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Password, String>("password"));
        passwordDisplay.setItems(passwordList);
        passwordDisplay.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void generatePasswordBtn() {
        populatePasswordList();
    }

    @FXML
    private void generatePasswordEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            populatePasswordList();
        }
    }

    @FXML
    private void copyPasswordBtn() {
        String passwordToClipboard = passwordDisplay.getSelectionModel().getSelectedItem().getPassword();
        stringToClipboard(passwordToClipboard);
    }

    @FXML
    private void copyAllPasswordsBtn() {
        String passwordsToClipboard = ""; // Not using a StringBuilder for improved code readability
        for (Password password : passwordList) { // Add all passwords to a single string
            passwordsToClipboard += password.getPassword() + "\n";
        }
        stringToClipboard(passwordsToClipboard);
    }

    /**
     * Generates the passwords based on the User's selected options and adds them to the passwordList.
     */
    private void populatePasswordList() {
        long startTime = System.currentTimeMillis();
        // Clear the display before generating new passwords
        clearDisplay();
        int numberOfPasswords;

        // Get the password length and number of passwords to generate
        int passwordLength = Integer.parseInt(passwordLengthChoiceBox.getValue());
        try {
            numberOfPasswords = Integer.parseInt(numberOfPasswordsField.getText());
            if (numberOfPasswords >= MAX_NUMBER_OF_PASSWORDS + 1) {
                showError("The Maximum number of passwords you can generate is: " + MAX_NUMBER_OF_PASSWORDS + "." +
                        " Please enter a number less than or equal to: " + MAX_NUMBER_OF_PASSWORDS + ".");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid number of passwords to generate.");
            return;
        }
        // Create a new PasswordGenerator object with the selected options
        PasswordGenerator generator = new PasswordGenerator(passwordLength, includeNumbersBox.isSelected(), includeLowercaseBox.isSelected(), includeUppercaseBox.isSelected(), includeSpecialBox.isSelected(), noDuplicateBox.isSelected(), noSequentialBox.isSelected());
        for (int i = 1; i < numberOfPasswords + 1; i++) { // Generate the number of passwords specified
            String password = generator.generatePassword();
            passwordList.add(new Password(i, password));
        }
        // Check the runtime of the password generation process
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to generate passwords: " + (endTime - startTime) + " ms");
    }

    /**
     * Clears the display of all passwords
     */
    private void clearDisplay() {
        passwordList.clear();
        passwordDisplay.setItems(passwordList);
    }

    /**
     * Copies a string to the clipboard
     * @param string The string to copy
     */
    private void stringToClipboard(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * Shows an error message in a new window. Used for displaying basic errors to the user.
     * @param error The error message to display
     */
    private void showError(String error) {
        ErrorWindowCreator errorWindow = new ErrorWindowCreator("Error", null, error);
        errorWindow.show();
    }
}

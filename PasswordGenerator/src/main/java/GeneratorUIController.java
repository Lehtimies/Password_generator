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

/**
 * Controller class for the Password Generator UI.
 */
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
        try {
            String passwordToClipboard = passwordDisplay.getSelectionModel().getSelectedItem().getPassword();
            stringToClipboard(passwordToClipboard);
        } catch (NullPointerException e) {
            System.out.println("No password selected. \nError Message: " + e.getMessage());
        }
    }

    @FXML
    private void copyAllPasswordsBtn() {
        // Create a StringBuilder to store all the passwords
        StringBuilder passwordsToClipboard = new StringBuilder();
        for (Password password : passwordList) { // Add all passwords to the StringBuilder
            passwordsToClipboard.append(password.getPassword()).append("\n");
        }
        stringToClipboard(passwordsToClipboard.toString());
    }

    /**
     * Generates the passwords based on the user's selected options and adds them to the passwordList.
     */
    private void populatePasswordList() {
        long startTime = System.currentTimeMillis();
        // Check if the user has entered valid inputs
        if (!validateInputs()) {
            long endTime = System.currentTimeMillis();
            System.out.println("Process terminated, runtime: " + (endTime - startTime) + " ms");
            return;
        }
        // Clear the display and get user inputs
        clearDisplay();
        int numberOfPasswords = Integer.parseInt(numberOfPasswordsField.getText());
        int passwordLength = Integer.parseInt(passwordLengthChoiceBox.getValue());

        // Create a new PasswordGenerator object with the selected options
        PasswordGenerator generator = new PasswordGenerator(
                passwordLength,
                includeNumbersBox.isSelected(),
                includeLowercaseBox.isSelected(),
                includeUppercaseBox.isSelected(),
                includeSpecialBox.isSelected(),
                noDuplicateBox.isSelected(),
                noSequentialBox.isSelected()
        );
        // Generate the number of passwords specified by the user
        for (int i = 1; i < numberOfPasswords + 1; i++) {
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
     * Checks the user inputs to make sure they are valid
     * @return True if the inputs are valid, false otherwise
     */
    private Boolean validateInputs() {
        try {
            int numberOfPasswords = Integer.parseInt(numberOfPasswordsField.getText());
            if (numberOfPasswords >= MAX_NUMBER_OF_PASSWORDS + 1) {
                showError("The maximum number of passwords you can generate is: " + MAX_NUMBER_OF_PASSWORDS + ".");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid number of passwords.");
            return false;
        }
        return true;
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

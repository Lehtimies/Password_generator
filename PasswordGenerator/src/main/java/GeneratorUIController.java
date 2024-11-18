import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GeneratorUIController {
    @FXML private TableView<Password> passwordDisplay;
    @FXML private TableColumn<Password, String> passwordNumberColumn;
    @FXML private TableColumn<Password, Integer> passwordColumn;
    @FXML private TextField passwordLengthField;
    @FXML private TextField numberOfPasswordsField;
    @FXML private CheckBox includeSpecialBox;
    @FXML private CheckBox includeNumbersBox;
    @FXML private CheckBox includeLettersBox;


    @FXML
    private void generatePasswordBtn() {
        // TODO: Add code
    }

    @FXML
    private void savePasswordBtn() {
        // TODO: Add code
    }
}

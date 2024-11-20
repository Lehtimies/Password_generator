import javafx.scene.control.Alert;

public class ErrorWindowCreator {
    private String title;
    private String header;
    private String content;

    /**
     * Creates an Error Window with the given title, header and content
     * @param title
     * @param header
     * @param content
     */
    public ErrorWindowCreator(String title, String header, String content) {
        this.title = title;
        this.header = header;
        this.content = content;
    }

    public void show() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

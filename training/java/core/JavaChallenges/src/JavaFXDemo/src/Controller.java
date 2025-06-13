import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;

    @FXML
    private Label greetingLabel;

    @FXML
    private void handleGreet() {
        String name = nameField.getText();
        greetingLabel.setText("Hello, " + name + "!");
    }
}

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent; 
public class Controller {

    @FXML
    private TextField nameField;

    @FXML
    private Label greetingLabel;

    @FXML
    public void handleGreet(ActionEvent event) { 
        String name = nameField.getText(); 
        if (name != null && !name.trim().isEmpty()) {
            greetingLabel.setText("Hello, " + name + "!");
        } else {
            greetingLabel.setText("Please enter your name.");
        }
    }
}
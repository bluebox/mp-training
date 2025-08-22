package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML private TextField nameField;
    @FXML private RadioButton maleButton;
    @FXML private RadioButton femaleButton;
    @FXML private DatePicker datePicker;
    private final ToggleGroup genderGroup = new ToggleGroup();

    
    @FXML
    public void initialize() {
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);
    }

    @FXML
    private void handleSubmit() {
        String name = nameField.getText();
        RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
        String gender = selectedGender != null ? selectedGender.getText() : "Not selected";
        String dob = datePicker.getValue() != null ? datePicker.getValue().toString() : "Not selected";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Form Submission");
        alert.setHeaderText("User Details");
        alert.setContentText("Name: " + name + "\nGender: " + gender + "\nDOB: " + dob);
        alert.showAndWait();
    }
}
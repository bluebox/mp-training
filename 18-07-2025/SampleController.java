
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class SampleController {

    @FXML
    private void handleButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Clicked!");
        alert.setHeaderText(null);
        alert.setContentText("You clicked the button!");
        alert.showAndWait();
        
        
    }
    
    
}

package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
public class ReportMenuController {
    public void handleOverdue(ActionEvent event) throws IOException {
        loadScene(event, "/application/OverdueBooks.fxml");
    }
    
    public void handleCategory(ActionEvent event) throws IOException {
        loadScene(event, "/application/BooksPerCategory.fxml");
    }
    public void handleActiveMembers(ActionEvent event) throws IOException {
        loadScene(event, "/application/ActiveMembers.fxml");
    }
    public void handleBack(ActionEvent event) throws IOException {
        loadScene(event, "/application/Home.fxml");
    }
    private void loadScene(ActionEvent event, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene newScene = new Scene(loader.load());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
    }
}
package application;

import javafx.scene.control.TextField;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scene1Controller {
	@FXML
	 TextField nameTextField;
    private Stage stage;
	private Scene scene;
	private Parent root;
	public void login(ActionEvent event) throws IOException
	{
		String username=nameTextField.getText();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Scene4.fxml"));
		root=loader.load();
		Scene2Controller scene2controller =loader.getController();
		scene2controller.displayName(username);
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();		
	}
}

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class controller {

	@FXML
	private TextField nameField;
	
	@FXML
	private Button helloButton;
	
	@FXML
	private Button byeButton;
	
	@FXML
	private CheckBox ourCheckBox;
	
//	public void onButtonClicked(ActionEvent e){
//		System.out.println("Hello, "+nameField.getText());
//		System.out.println("The following button was pressed: "+e.getSource());
//	}
	
	
	public void initialize() {
		helloButton.setDisable(true);
		byeButton.setDisable(true);
	}
	
	
	public void onButtonClicked(ActionEvent e){
		if(e.getSource().equals(helloButton)) {
			System.out.println("Hello, "+nameField.getText());
		}else if(e.getSource().equals(byeButton)) {
			System.out.println("Bye, "+nameField.getText());
		}
		
		if(ourCheckBox.isSelected()) {
			nameField.clear();
			helloButton.setDisable(true);
			byeButton.setDisable(true);
		}
	}


	public void handleKeyReleased() {
		String text = nameField.getText();
		
		boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
		
		helloButton.setDisable(disableButtons);
		byeButton.setDisable(disableButtons);
	}
	
	public void handleChange() {
		System.out.println("The checkbox is "+(ourCheckBox.isSelected() ? "checked": "not checked"));
	}
}

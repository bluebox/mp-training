package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller3 {
	@FXML
	private Label myLabel;
	@FXML
	private TextField myTextField;
	@FXML
	private Button myButton;
	int age;
	public void submit(ActionEvent event)
	{
		try
		{
			age=Integer.parseInt(myTextField.getText());
			//System.out.println(age);
			if(age>=18)
			{
				myLabel.setText("You are now signed up!");
			}
			else
			{
				myLabel.setText("You must be 18+");
			}
		}
		catch(NumberFormatException e)
		{
			myLabel.setText("Enter only numbers plz");
			//System.out.println("Enter only Numbers plz");
		}
		catch(Exception e)
		{
			myLabel.setText("Error");
			System.out.println(e);
		}
	}
}

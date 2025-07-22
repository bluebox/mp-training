package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class Controller6 {
	@FXML
	private DatePicker mydatePicker;
	@FXML
	private Label myLabel;
	public void getDate(ActionEvent event)
	{
		LocalDate myDate=mydatePicker.getValue();
		String FormattedDate=myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		myLabel.setText(FormattedDate);
		//System.out.println(myDate.toString());
	}

}

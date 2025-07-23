package application;


import java.time.LocalDate;

import Data.ToDoData;
import Data.TodoDataSave;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DialogController {
	
	@FXML
	private TextField shortDescriptionField;
	
	@FXML
	private TextArea detailsArea;
	
	@FXML
	private DatePicker deadlinePicker;
	
	public ToDoData processResult() {
		String shortDescritption = shortDescriptionField.getText().trim();
		String detail = detailsArea.getText().trim();
		LocalDate deadlineValue = deadlinePicker.getValue();
		
		ToDoData data = new ToDoData(shortDescritption, detail, deadlineValue);
		TodoDataSave.getInstance().addTodoItem(data);
		return data;
		
	}

}

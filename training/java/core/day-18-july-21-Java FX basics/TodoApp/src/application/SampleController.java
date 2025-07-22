package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

public class SampleController {
	private List<TodoItem> todoItems;

	@FXML
	private ListView<TodoItem> todoListView;

	@FXML
	private TextArea itemDetailsTextArea;

	public void initialize() {
		TodoItem item1 = new TodoItem("Clean room", "Clean the whole room", LocalDate.now().minusDays(1));
		TodoItem item2 = new TodoItem("Wash clothes", "Put clothes in washing machine and dry them on terrace",
				LocalDate.now().plusDays(2));
		TodoItem item3 = new TodoItem("Buy Groceries", "Go to D-mart and buy grocieries", LocalDate.now().plusDays(4));
		todoItems = new ArrayList<>(Arrays.asList(item1, item2, item3));
		todoListView.getItems().setAll(todoItems);
		todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	@FXML
	public void handleClickListView() {
		TodoItem item = todoListView.getSelectionModel().getSelectedItem();
		StringBuilder sb=new StringBuilder();
		sb.append("Details: ");
		sb.append(item.getDetails());
		sb.append("\n");
		sb.append("Deadline: ");
		sb.append(item.getDeadline());
		itemDetailsTextArea.setText(sb.toString());
	}
}

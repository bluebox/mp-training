package TodoList.July22;

import java.time.format.DateTimeFormatter;

import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

public class MainController {

//	private List<TodoItem> todoItems;

	@FXML
	private ListView<TodoItem> todoListView;
	@FXML
	private TextArea itemDetailsTextArea;
	@FXML
	private Label deadlineLabel;

	public void initialize() {

		todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
			@Override
			public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
				if (newValue != null) {
					TodoItem item = todoListView.getSelectionModel().getSelectedItem();
					itemDetailsTextArea.setText(item.getDetails());
					DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
					deadlineLabel.setText(df.format(item.getDeadLine()));
				}
			}
		});

		todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
		todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		todoListView.getSelectionModel().selectFirst();
	}

	@FXML
	public void handleClickListView() {
		TodoItem item = todoListView.getSelectionModel().getSelectedItem();
		itemDetailsTextArea.setText(item.getDetails());
		deadlineLabel.setText(item.getDeadLine().toString());
	}
}

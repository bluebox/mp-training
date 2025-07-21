package application.todolist;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import application.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

public class Controller {
	
	private List<TodoItem> todoItems;
	
	@FXML
	private ListView<TodoItem> todoListView;
	
	@FXML
	private TextArea itemDetailsTextArea;
	
	public void initialize() {
		TodoItem item1= new TodoItem("Task 1", "Complete task 1",
				LocalDate.of(2025,Month.AUGUST,20));
		TodoItem item2= new TodoItem("Task 2", "Complete task 2",
				LocalDate.of(2025,Month.SEPTEMBER,2));
		TodoItem item3= new TodoItem("Task 3", "Complete task 3",
				LocalDate.of(2025,Month.JUNE,23));
		TodoItem item4= new TodoItem("Task 4", "Complete task 4",
				LocalDate.of(2025,Month.JULY,8));
		TodoItem item5= new TodoItem("Task 5", "Complete task 5",
				LocalDate.of(2025,Month.DECEMBER,9));
		TodoItem item6= new TodoItem("Task 6", "Complete task 6",
				LocalDate.of(2025,Month.OCTOBER,2));
		
		todoItems = new ArrayList<>();
		todoItems.add(item1);
		todoItems.add(item2);
		todoItems.add(item3);
		todoItems.add(item4);
		todoItems.add(item5);
		todoItems.add(item6);
		
		todoListView.getItems().setAll(todoItems);
		todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	public void handleClickListView() {
		TodoItem item = todoListView.getSelectionModel().getSelectedItem();
//		System.out.println("The Selected Item is: "+item);
		
		StringBuilder sb = new StringBuilder(item.getDetails());
		sb.append("\n\n\n");
		sb.append("Due: ");
		sb.append(item.getDeadline().toString());
		itemDetailsTextArea.setText(sb.toString());
	}

}

package application;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import Data.ToDoData;
import Data.TodoDataSave;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class SampleController {
	
	
	@FXML
	private ListView<ToDoData> todo;
	@FXML
	private TextArea itemstoprint;
	@FXML
	private Label DeadLable;
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private ContextMenu listContextMenu;
	
	public void initialize() {
		
		
		listContextMenu = new ContextMenu();
		MenuItem deleteMenuItem = new MenuItem("Delete");
		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToDoData data = todo.getSelectionModel().getSelectedItem();
				deleteItem(data);
				
			}
		});
		
		listContextMenu.getItems().addAll(deleteMenuItem);
		todo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoData>() {
			@Override
			public void changed(ObservableValue<? extends ToDoData> observable, ToDoData oldValue, ToDoData newValue) {
				if( newValue != null) {
					ToDoData data = todo.getSelectionModel().getSelectedItem();
					itemstoprint.setText(data.getDetails());
					DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
					DeadLable.setText(df.format(data.getDeadline()));
					
 				}
				
			}
		});
		
		todo.setItems(TodoDataSave.getInstance().getTodoItems());
		todo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		todo.getSelectionModel().selectFirst();
		
		todo.setCellFactory(new Callback<ListView<ToDoData>, ListCell<ToDoData>>() {
			
			@Override
			public ListCell<ToDoData> call(ListView<ToDoData> arg0) {
				// TODO Auto-generated method stub
				ListCell<ToDoData> cell = new ListCell<ToDoData>() {
					
					@Override
					protected void updateItem(ToDoData data, boolean empty) {
						super.updateItem(data, empty);
						if(empty) {
							setText(null);
						}else {
							setText(data.getShortDescription());
							if(data.getDeadline().equals(LocalDate.now())) {
								setTextFill(javafx.scene.paint.Color.RED);
							}
						}
					}
				};
				
				cell.emptyProperty().addListener(
						(obs, wasEmpty, isNowEmpty) -> {
							if (isNowEmpty) {
								cell.setContextMenu(null);
							}else {
								cell.setContextMenu(listContextMenu);
							}
						}
						);
				
				return cell;
			}
		});
	
	}
	@FXML
	public void showNewItem() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Add New TODO");
		dialog.setHeaderText("This is a Header TEXT");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("Dialog.fxml"));
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
			
			
		}catch(IOException e) {
			System.out.println("Cant LOAD");
			e.printStackTrace();
			return;
		}
		
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		Optional<ButtonType> result = dialog.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			DialogController controller = fxmlLoader.getController();
			ToDoData data = controller.processResult();
			//todo.getItems().setAll(TodoDataSave.getInstance().getTodoItems());
			todo.getSelectionModel().select(data);
		}
	}
	
	
	@FXML
	public void handelClickListView() {
		ToDoData data =  todo.getSelectionModel().getSelectedItem();
		itemstoprint.setText(data.getDetails());
		DeadLable.setText(data.getDeadline().toString());
	}
	
	public void deleteItem(ToDoData data) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete Todo Item");
		alert.setHeaderText("Delete Item" + data.getShortDescription());
		alert.setContentText("Are you sure you want to delete this item");
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && (result.get()== ButtonType.OK)) {
			TodoDataSave.getInstance().deleteTodoItem(data);
			
		}
	}
	
	
	
}

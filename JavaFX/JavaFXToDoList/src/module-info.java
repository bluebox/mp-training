module JavaFXToDoList {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application.todolist to javafx.graphics, javafx.fxml;
}

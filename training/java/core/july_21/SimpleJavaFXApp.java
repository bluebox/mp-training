package javaFX;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SimpleJavaFXApp extends Application {

 @Override
 public void start(Stage primaryStage) {
  
     Label nameLabel = new Label("Enter Name:");
     TextField nameField = new TextField();
     HBox inputBox = new HBox(nameLabel, nameField);
     inputBox.setSpacing(50);
     inputBox.setPadding(new Insets(85));
     Button greetButton = new Button("Greet");
     Label greetLabel = new Label();
     greetButton.setOnAction(e -> greetLabel.setText("Hello, " + nameField.getText() + "!"));

   
     TableView<String> tableView = new TableView<>();
     TableColumn<String, String> column = new TableColumn<>("Sample Items");
     column.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()));
     tableView.getColumns().add(column);
     ObservableList<String> data = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
     tableView.setItems(data);

  
     VBox root = new VBox(15, inputBox, greetButton, greetLabel, tableView);
     root.setPadding(new Insets(20));

 
     Scene scene = new Scene(root, 400, 300);
     primaryStage.setTitle("Single File JavaFX App");
     primaryStage.setScene(scene);
     primaryStage.show();
 }

 public static void main(String[] args) {
     launch(args);
 }
}
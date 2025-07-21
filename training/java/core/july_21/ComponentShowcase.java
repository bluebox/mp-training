package javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets; // <-- Add this import

public class ComponentShowcase extends Application {

 @Override
 public void start(Stage primaryStage) {
    
     Label nameLabel = new Label("Name:");
     TextField nameField = new TextField();
     HBox hBox = new HBox(10, nameLabel, nameField);

         Button greetButton = new Button("Greet");
     Label greetingLabel = new Label();
     greetButton.setOnAction(e -> {
         String name = nameField.getText();
         greetingLabel.setText("Hello, " + name + "!");
     });

    
     TableView<Person> tableView = new TableView<>();

     TableColumn<Person, String> colName = new TableColumn<>("Name");
     colName.setCellValueFactory(data -> data.getValue().nameProperty());

     TableColumn<Person, String> colEmail = new TableColumn<>("Email");
     colEmail.setCellValueFactory(data -> data.getValue().emailProperty());

     tableView.getColumns().addAll(colName, colEmail);
     tableView.setItems(getSampleData());

      VBox rootLayout = new VBox(15);
     rootLayout.setPadding(new Insets(15));
     rootLayout.getChildren().addAll(hBox, greetButton, greetingLabel, tableView);

     Scene scene = new Scene(rootLayout, 500, 400);
     primaryStage.setTitle("JavaFX Components Showcase");
     primaryStage.setScene(scene);
     primaryStage.show();
 }

 private ObservableList<Person> getSampleData() {
     return FXCollections.observableArrayList(
         new Person("Alice", "alice@example.com"),
         new Person("Bob", "bob@example.com"),
         new Person("Charlie", "charlie@example.com")
     );
 }

 public static void main(String[] args) {
     launch(args);
 }
}

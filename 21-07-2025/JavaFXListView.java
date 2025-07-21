package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
public class JavaFXListView extends Application {
   public void start(Stage stage) {
      Label label = new Label("Educational qualification:");
      Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
      label.setFont(font);
      ObservableList<String> names = FXCollections.observableArrayList("Engineering", "MCA", "MBA", "Graduation", "MTECH", "Mphil", "Phd");
      ListView<String> listView = new ListView<String>(names);
      listView.setMaxSize(200, 160);
      VBox layout = new VBox(10);
      layout.setPadding(new Insets(5, 5, 5, 50));
      layout.getChildren().addAll(label, listView);
      layout.setStyle("-fx-background-color: BEIGE");
      Scene scene = new Scene(layout, 400, 300);
      stage.setTitle("List View Example");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}

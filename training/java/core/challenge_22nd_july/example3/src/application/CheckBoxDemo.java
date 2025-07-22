package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
public class CheckBoxDemo extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      Label label = new Label("Click the box to select: ");
      // Creating three CheckBoxes
      CheckBox checkBx1 = new CheckBox("Item1");
      checkBx1.setTextFill(Color.GREEN);
      checkBx1.setSelected(false);
      CheckBox checkBx2 = new CheckBox("Item2");
      checkBx2.setTextFill(Color.BLUE);
      checkBx2.setSelected(false);
      CheckBox checkBx3 = new CheckBox("Item3");
      checkBx3.setTextFill(Color.SKYBLUE);
      checkBx3.setSelected(false);
      
      
      Label selectLabel = new Label();
      selectLabel.setTextFill(Color.RED);
      checkBx1.setOnAction(e -> selectLabel.setText("You selected: " + 
         (checkBx1.isSelected() ? "Item1" : "")
      ));
      checkBx2.setOnAction(e -> selectLabel.setText("You selected: " + 
         (checkBx2.isSelected() ? "Item2" : "")
      )); 
      checkBx3.setOnAction(e -> selectLabel.setText("You selected: " + 
         (checkBx2.isSelected() ? "Item3" : "")
      ));     
      VBox vbox = new VBox();
      vbox.setAlignment(Pos.CENTER); 
      vbox.setPadding(new Insets(10));
      vbox.setSpacing(10);
      vbox.getChildren().addAll(label, checkBx1, checkBx2, checkBx3, selectLabel);
      Scene scene = new Scene(vbox, 400, 300);
      stage.setScene(scene);
      stage.setTitle("CheckBox in JavaFX");
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}

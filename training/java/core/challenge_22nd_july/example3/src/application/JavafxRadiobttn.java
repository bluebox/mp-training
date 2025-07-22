package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class JavafxRadiobttn extends Application {
   @Override
   public void start(Stage stage) {
	   
	  Label l1=new Label("select any language");
	  Label l2=new Label("select an IDE");
      RadioButton button1 = new RadioButton("java");
      RadioButton button2 = new RadioButton("python");
      RadioButton button3 = new RadioButton("c++");
      
      RadioButton button4 = new RadioButton("IntelliJ");
      RadioButton button5 = new RadioButton("eclipse");
      //Toggle button group
      ToggleGroup group1 = new ToggleGroup();
      button1.setToggleGroup(group1);
      button2.setToggleGroup(group1);
      button3.setToggleGroup(group1);      
      
      ToggleGroup group2=new ToggleGroup();
      button4.setToggleGroup(group2);
      button5.setToggleGroup(group2);
      
      //Adding the toggle button to the pane
      VBox box = new VBox(5);
      box.setFillWidth(false);
      box.setPadding(new Insets(10,10,10,50));
//      box.getChildren().addAll(l1,l2);
      box.getChildren().addAll(
    	         l1, button1, button2, button3,
    	         l2, button4, button5
    	      );
      
      
      Scene scene = new Scene(box, 400, 300, Color.BEIGE);
      stage.setTitle("Toggled Button Example");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}
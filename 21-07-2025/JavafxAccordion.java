package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class JavafxAccordion extends Application {
   @Override
   public void start(Stage stage) {
      TitledPane paneOne = new TitledPane();
      paneOne.setText("Your Name");
      paneOne.setContent(new Label("My name is: \n Pranamya"));
      TitledPane paneTwo = new TitledPane();
      paneTwo.setText("Your Address");
      paneTwo.setContent(new Label("My address is: \n Hyderabad \n Telangana"));
      TitledPane paneThree = new TitledPane();
      paneThree.setText("Your Job");
      paneThree.setContent(new Label("My job role is: \n Software Engineer"));
      Accordion accordionNew = new Accordion();
      accordionNew.getPanes().addAll(paneOne, paneTwo, paneThree);
      accordionNew.setExpandedPane(paneOne);
      VBox root = new VBox(accordionNew);
      Scene scene = new Scene(root, 500, 500, Color.BEIGE);
      stage.setTitle("Accordion in JavaFX");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}   

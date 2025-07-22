package application;

import javafx.application.Application;
 import javafx.geometry.Insets;
 import javafx.scene.Scene;
 import javafx.scene.control.Label;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.control.Tooltip;
 import javafx.scene.layout.VBox;
 import java.io.FileInputStream; 
 import java.io.FileNotFoundException; 
 import javafx.stage.Stage;
 public class JavafxTooltip extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
       Label labeltext = new Label("Hover over Image to see the details....");
       Image image = new Image(new FileInputStream("tutorials_point.jpg"));  
       ImageView imageView = new ImageView(image); 
       imageView.setX(50); 
       imageView.setY(25); 
       imageView.setFitHeight(350); 
       imageView.setFitWidth(350); 
       imageView.setPreserveRatio(true);  
       Tooltip toolTipTxt = new Tooltip("This is the new logo of Tutorialspoint");
       Tooltip.install(imageView, toolTipTxt);
       VBox box = new VBox(5);
       box.setPadding(new Insets(25, 5 , 5, 50));
       box.getChildren().addAll(labeltext, imageView);
       Scene scene = new Scene(box, 400, 350);
       stage.setTitle("Example of Tooltip in JavaFX");
       stage.setScene(scene);
       stage.show();
    }
    public static void main(String args[]){
       launch(args);
    }
 }


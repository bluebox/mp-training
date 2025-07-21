package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Orientation;
import javafx.geometry.Pos; 
public class SliderJavafx extends Application {
   @Override
   public void start(Stage stage) {
      Slider slide = new Slider();
      slide.setMin(0);
      slide.setMax(100);
      slide.setOrientation(Orientation.VERTICAL);
      slide.setMajorTickUnit(10);
      slide.setMinorTickCount(9);
      slide.setShowTickMarks(true);
      slide.setShowTickLabels(true);
      Label valueLabel = new Label("Value: " + slide.getValue());
      slide.valueProperty().addListener((observable, oldValue, newValue) -> {
         valueLabel.setText("Value: " + newValue.intValue());
      });
      VBox root = new VBox();
      root.setAlignment(Pos.CENTER); 
      root.setPadding(new Insets(10));
      root.setSpacing(10);
      root.getChildren().addAll(slide, valueLabel);
      Scene scene = new Scene(root, 400, 300);
      stage.setTitle("Slider in JavaFX");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}
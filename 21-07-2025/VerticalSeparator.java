package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Orientation;
public class VerticalSeparator extends Application {
   @Override
   public void start(Stage stage) {
      TextField textField1 = new TextField();
      TextField textField2 = new TextField();
      TextField textField3 = new TextField();
      Separator separator1 = new Separator(Orientation.VERTICAL);
      Separator separator2 = new Separator(Orientation.VERTICAL);
      HBox hbox = new HBox();
      hbox.setPadding(new Insets(15, 12, 15, 12));
      hbox.setSpacing(10);
      hbox.getChildren().addAll(textField1, separator1, textField2, separator2, textField3);
      Scene scene = new Scene(hbox, 500, 300);
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}

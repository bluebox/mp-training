package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class Toolbar extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      Button btnNew = new Button("New");
      Button btnOpen = new Button("Open");
      Button btnSave = new Button("Save");
      Button btnExit = new Button("Exit");
      TextField txtSearch = new TextField();
      txtSearch.setPromptText("Search");
      Separator sepOne = new Separator();
      Separator sepTwo = new Separator();
      ToolBar toolsB = new ToolBar();
      toolsB.getItems().addAll(btnNew, btnOpen, btnSave, sepOne, txtSearch, sepTwo, btnExit);
      BorderPane root = new BorderPane();
      root.setTop(toolsB);
      Scene scene = new Scene(root, 400, 300);
      stage.setTitle("ToolBar in JavaFX");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}

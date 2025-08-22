package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
public class JavafxTreeview extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      TreeItem<String> root = new TreeItem<>("Smartphones");
      root.setExpanded(true);
      TreeItem<String> android = new TreeItem<>("Android");
      android.getChildren().addAll(
         new TreeItem<>("Samsung Galaxy S23 Ultra"),
         new TreeItem<>("Xiaomi Redmi Note 13 Pro"),
         new TreeItem<>("OnePlus 11R")
      );
      TreeItem<String> ios = new TreeItem<>("iOS");
      ios.getChildren().addAll(
         new TreeItem<>("iPhone 15 Plus"),
         new TreeItem<>("iPhone 14")
      );
      root.getChildren().addAll(android, ios);
      TreeView<String> treesV = new TreeView<>(root);
      treesV.setOnMouseClicked(event -> {
         TreeItem<String> item = treesV.getSelectionModel().getSelectedItem();
         if (item != null) {
            System.out.println(item.getValue());
         }
      });
      Scene scene = new Scene(treesV, 400, 300);
      stage.setTitle("TreeView in JavaFX");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}
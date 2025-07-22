package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
public class JavafxmenuBar extends Application {
   public void start(Stage stage) {
      Menu category = new Menu("Category");
      MenuItem itemOne = new MenuItem("Programming");
      MenuItem itemTwo = new MenuItem("Cyber Security");
      MenuItem itemThree = new MenuItem("Marketing");
      category.getItems().addAll(itemOne, itemTwo, itemThree);
      Menu library = new Menu("Library");
      MenuItem itemFour = new MenuItem("HTML");
      MenuItem itemFive = new MenuItem("Java");
      MenuItem itemSix = new MenuItem("JavaFX");
      library.getItems().addAll(itemFour, itemFive, itemSix);
      Menu articles = new Menu("Articles");
      MenuItem itemSeven = new MenuItem("Constructor");
      MenuItem itemEight = new MenuItem("Inheritance");
      MenuItem itemNine = new MenuItem("Current Affairs");
      articles.getItems().addAll(itemSeven, itemEight, itemNine);
      MenuBar newmenubar = new MenuBar();
      newmenubar.getMenus().addAll(category, library, articles);
      Group newgroup = new Group(newmenubar);
      Scene scene = new Scene(newgroup, 600, 400);
      stage.setTitle("MenuBar in JavaFX");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}

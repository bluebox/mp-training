package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.collections.*;
import javafx.stage.Stage;
import javafx.scene.text.Text.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
public class contextMenu extends Application {
    Label label;
    public void start(Stage stage)
    {
        stage.setTitle("creating contextMenu ");
        Label label1 = new Label("This is a ContextMenu example ");
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("menu item 1");
        MenuItem menuItem2 = new MenuItem("menu item 2");
        MenuItem menuItem3 = new MenuItem("menu item 3");
        contextMenu.getItems().add(menuItem1);
        contextMenu.getItems().add(menuItem2);
        contextMenu.getItems().add(menuItem3);
        Label label = new Label("context menu hidden");
        EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e)
            {
                if (contextMenu.isShowing())
                    label.setText("context menu showing");
                else
                    label.setText("context menu hidden");
            }
        };
        contextMenu.setOnShowing(event);
        contextMenu.setOnHiding(event);
        TilePane tilePane = new TilePane(label1);
        tilePane.getChildren().add(label);
        label.setContextMenu(contextMenu);
        Scene sc = new Scene(tilePane, 200, 200);
        stage.setScene(sc);

        stage.show();
    }

    public static void main(String args[])
    {
        launch(args);
    }
}
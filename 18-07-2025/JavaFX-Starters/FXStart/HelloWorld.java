package fxapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	

	@Override
	public void start(@SuppressWarnings("exports") Stage arg0) throws Exception {
		Button btn=new Button("Click to proceed");
		
		btn.setOnAction(new EventHandler<ActionEvent>() {

			
			public void handle(ActionEvent event) {
				System.out.println("Hello World");
				
			}
			
		});
		StackPane root=new StackPane();
		root.getChildren().add(btn);
		Scene scene=new Scene(root,500,300);
		arg0.setScene(scene);
		arg0.show();
		
	}
}

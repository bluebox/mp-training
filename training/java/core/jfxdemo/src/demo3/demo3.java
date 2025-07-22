package demo3;

import javafx.application.Application;


import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class demo3 extends Application {
//	public static void main(String[] args)
//	 {
//		 launch(args);
//	 }
	 public void start(Stage ps)
	 {
		
		 
		 ps.setTitle("this is demo1");
		 Button btn=new Button();
		 btn.setText("Click me");
		 StackPane sp=new StackPane();
		 sp.getChildren().add(btn);
		 Scene sc=new Scene(sp,300,300);
		 ps.setScene(sc);
		 ps.show();
		 
		 
		 
	 }
}

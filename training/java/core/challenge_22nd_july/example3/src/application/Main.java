package application;
	


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		TextField textfield=new TextField("Enter a value");
		Button b1=new Button("Pause");
		Button b2=new Button("Play");
//		HBox hbox=new HBox();
//		hbox.setSpacing(15);
//		hbox.getChildren().addAll(textfield,b1,b2);
//		hbox.setMargin(textfield, new Insets(20,20,20,20));
//		hbox.setMargin(b1, new Insets(20,20,20,20)t);
//		hbox.setMargin(b2,new Insets(20,20,20,20));
//	    hbox.setAlignment(Pos.CENTER);
//		Scene scene=new Scene(hbox,400,300);
		VBox vbox=new VBox();
		vbox.setSpacing(10);
		vbox.getChildren().addAll(textfield,b1,b2);
		vbox.setMargin(textfield, new Insets(20,20,20,20));
		vbox.setMargin(b1, new Insets(20,20,20,20));
		vbox.setMargin(b2, new Insets(20,20,20,20));
		vbox.setAlignment(Pos.BASELINE_LEFT);
		Scene scene=new Scene(vbox,400,400);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
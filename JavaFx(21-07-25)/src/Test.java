import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
public class Test extends Application
{
	public static void main(String args[])
	{
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		 Button btn=new Button("Click me");
		 Button exit=new Button("exit");
		 exit.setOnAction(e->System.out.println("Exit Button"));
		 btn.setOnAction(new EventHandler<ActionEvent>()
				 {
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						System.out.println("Hello World");
					}
			 	
				 });
		// StackPane root=new StackPane();
		 VBox root=new VBox();
		 //root.getChildren().add(btn);
		 root.getChildren().addAll(btn,exit);
		 Scene scene=new Scene(root,500,300);
		 primaryStage.setTitle("My Tittle");
		 primaryStage.setScene(scene);
		primaryStage.show(); 
	}
}

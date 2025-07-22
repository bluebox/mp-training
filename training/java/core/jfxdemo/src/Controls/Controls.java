package Controls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controls  extends Application{
	
//	public static void main(String [] args)
//	{
//		launch(args);
//	}
	@Override
	public void start(Stage stg) throws Exception {
		// TODO Auto-generated method stub
		Label lbl=new Label("Food Items:");
		 ObservableList<String> names = FXCollections.observableArrayList("Biryani","Starters","Juice","Softdrinks");
	      ListView<String> listView = new ListView<String>(names);
	      listView.setMaxSize(200, 160);
	      VBox layout = new VBox(10);
	      layout.setPadding(new Insets(5, 5, 5, 50));
	      layout.getChildren().addAll(lbl, listView);
	      layout.setStyle("-fx-background-color: green");
	     // listView.setStyle("-fx-background-color: skyblue");
	    
	      Scene scene = new Scene(layout, 400, 300);
	      stg.setTitle("List View Example");
	      stg.setScene(scene);
	      stg.show();
		
	}

}

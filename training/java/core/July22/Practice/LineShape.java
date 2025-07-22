package Practice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LineShape extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		
//		Line line = new Line();
//		line.setStartX(100);
//		line.setStartY(150);
//		line.setEndX(200);
//		line.setEndY(150);
//		Group root = new Group(line);
		
		Line line1 = new Line(100, 150, 200, 150);
		Line line2 = new Line(200, 150, 200, 250);
		Line line3 = new Line(200, 250, 100, 250);
		Line line4 = new Line(100, 250, 100, 150);
		
		Group root = new Group();
		root.getChildren().addAll(line1, line2, line3, line4);
		
		Scene scene = new Scene(root, 400, 400, Color.BLUEVIOLET);
		
		arg0.setTitle("Practicing line");
		arg0.setScene(scene);
		arg0.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

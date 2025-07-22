package Practice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class PolygonShape extends Application {

	@Override
	public void start(Stage arg0) throws Exception {

		Polygon poly = new Polygon();

//		poly.getPoints().addAll(new Double[]{
//				200.0, 50.0, 
//		         400.0, 50.0, 
//		         450.0, 150.0,          
//		         400.0, 250.0, 
//		         200.0, 250.0,                   
//		         150.0, 150.0});

		poly.getPoints().addAll(new Double[] { 300.0, 50.0, 450.0, 150.0, 300.0, 250.0, 150.0, 150.0, });
		poly.setFill(Color.BLUE);
		poly.setStroke(Color.BLACK);
		poly.setStrokeWidth(10);
//		poly.setStrokeType(StrokeType.OUTSIDE);
//		poly.setStrokeLineJoin(StrokeLineJoin.ROUND);
//		poly.setStrokeLineCap(StrokeLineCap.ROUND);
		poly.setSmooth(false);

		Group root = new Group(poly);

		Scene scene = new Scene(root, 600, 300);
		arg0.setTitle("Polygon");
		arg0.setScene(scene);
		arg0.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

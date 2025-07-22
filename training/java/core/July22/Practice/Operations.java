package Practice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Operations extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Circle circle1 = new Circle(250, 135, 100, Color.AQUA);
		Circle circle2 = new Circle(350, 135, 100, Color.AQUA);
		
//		Shape shape = Shape.union(circle1, circle2);
//		Shape shape = Shape.intersect(circle1, circle2);
//		Shape shape = Shape.subtract(circle1, circle2);
		Shape shape = Shape.subtract(circle2, circle1);

		shape.setFill(Color.DARKSLATEBLUE);

		Group root = new Group(shape);

		Scene scene = new Scene(root, 600, 300);
		stage.setTitle("Operations");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

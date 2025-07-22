package Practice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleShape extends Application {

	@Override
	public void start(Stage arg0) throws Exception {

//		Circle circle = new Circle();
//		circle.setCenterX(200);
//		circle.setCenterY(200);
//		circle.setRadius(100);
//		circle.setFill(Color.BLUE);
//		Group root = new Group(circle);

		Circle circle1 = new Circle(100, 100, 50);
		circle1.setStroke(Color.BLUE);
		circle1.setFill(Color.WHITE);

		Circle circle2 = new Circle(220, 100, 50);
		circle2.setStroke(Color.BLACK);
		circle2.setFill(Color.WHITE);

		Circle circle3 = new Circle(340, 100, 50);
		circle3.setStroke(Color.RED);
		circle3.setFill(Color.WHITE);

		Circle circle4 = new Circle(160, 150, 50);
		circle4.setStroke(Color.YELLOW);
		circle4.setFill(Color.WHITE);

		Circle circle5 = new Circle(280, 150, 50);
		circle5.setStroke(Color.GREEN);
		circle5.setFill(Color.WHITE);

		Group root = new Group();
		root.getChildren().addAll(circle1, circle2, circle3, circle4, circle5);

		Scene scene = new Scene(root, 400, 400, Color.SKYBLUE);
		arg0.setTitle("Circle");
		arg0.setScene(scene);
		arg0.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

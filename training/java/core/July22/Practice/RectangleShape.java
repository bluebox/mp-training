package Practice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RectangleShape extends Application {

	@Override
	public void start(Stage arg0) throws Exception {

		Rectangle rec = new Rectangle();
		rec.setX(100);
		rec.setY(100);
		rec.setWidth(200);
		rec.setHeight(150);
		rec.setArcWidth(80);
		rec.setArcHeight(50);
		rec.setFill(Color.ROYALBLUE);
		Group root = new Group(rec);

//		Rectangle rectangle1 = new Rectangle();
//		Rectangle rectangle2 = new Rectangle(200.0f, 120.0f, 50.0f, 30.0f);
//		rectangle2.setFill(Color.BROWN);
//		rectangle1.setX(150.0f);
//		rectangle1.setY(75.0f);
//		rectangle1.setWidth(150.0f);
//		rectangle1.setHeight(75.0f);
//		rectangle1.setFill(Color.PINK);
//		Line line1 = new Line(150, 75, 225, 30);
//		Line line2 = new Line(225, 30, 300, 75);
//
//		Group root = new Group();
//		root.getChildren().addAll(rectangle1, rectangle2, line1, line2);

		Scene scene = new Scene(root, 400, 400);
		arg0.setTitle("Rectangle");
		arg0.setScene(scene);
		arg0.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

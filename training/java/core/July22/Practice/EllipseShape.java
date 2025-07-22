package Practice;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EllipseShape extends Application {

	@Override
	public void start(Stage arg0) throws Exception {

//		Ellipse ellipse = new Ellipse();
//		ellipse.setCenterX(200);
//		ellipse.setCenterY(200);
//		ellipse.setRadiusX(150);
//		ellipse.setRadiusY(100);
//		Group root = new Group(ellipse);

		Ellipse orbit = new Ellipse(300, 150, 150, 100);
		orbit.setFill(Color.WHITE);
		orbit.setStroke(Color.BLACK);

		Circle planet = new Circle(300, 50, 40);

		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(10000));
		pathTransition.setNode(planet);
		pathTransition.setPath(orbit);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(50);
		pathTransition.setAutoReverse(false);
		pathTransition.play();

		Group root = new Group();
		root.getChildren().addAll(orbit, planet);

		Scene scene = new Scene(root, 600, 400);
		arg0.setTitle("Ellipse");
		arg0.setScene(scene);
		arg0.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

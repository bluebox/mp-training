import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Text1 extends Application {
	  public static void main(String[] args) {
	        launch(args);
	    }
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Text text = new Text("Sai Sreeja");
        text.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC, 36));
        text.setFill(Color.RED);
        text.setX(50);
        text.setY(50);
        //root.setCenter(text);
        text.setStroke(Color.BLUE);
        text.setStrokeWidth(2);
        text.setUnderline(true);
        text.setEffect(new BoxBlur());
        //text.setEffect(new Reflection());
        Line line =new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(50);
        line.setStrokeWidth(5);
        line.setStroke(Color.RED);
        line.setOpacity(0.5);
        line.setRotate(45);
        Rectangle rec=new Rectangle();
        rec.setX(100);
        rec.setY(100);
        rec.setWidth(100);
        rec.setHeight(100);
        rec.setFill(Color.YELLOW);
        rec.setStrokeWidth(5);
        rec.setStroke(Color.BLUE);
        Polygon triangle=new Polygon();
        triangle.getPoints().setAll(
        		200.0,200.0,
        		300.0,300.0,
        		200.0,300.0
        		);
        triangle.setFill(Color.ORANGE);
        Circle circle=new Circle();
        circle.setCenterX(350);
        circle.setCenterY(350);
        circle.setRadius(50);
        circle.setFill(Color.GREEN);
        Image image=new Image("Cartoon.jpg");
        ImageView iv=new ImageView(image);
        iv.setX(400);
        iv.setY(400);
        root.getChildren().addAll(text,line,rec,triangle,circle,iv);
        Scene scene = new Scene(root, 600, 400, Color.LIGHTSKYBLUE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Centered Text Example");
        primaryStage.show();
    }
}


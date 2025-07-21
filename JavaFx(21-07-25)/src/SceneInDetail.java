import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Cursor;

public class SceneInDetail extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane root=new BorderPane();
		Text text=new Text();
		text.setText("Sreeja");
		text.setX(400);
		text.setY(800);
		text.setFont(Font.font("Times New Roman",60));
		text.setFill(Color.RED);
		Image img = new Image("Cartoon.jpg");
		ImageView iV = new ImageView(img);
		iV.setFitWidth(1000);
        iV.setPreserveRatio(true);
        iV.setSmooth(true);
		root.getChildren().addAll(iV,text);
		Scene scene =new Scene(root,500,300,Color.BLACK);
		scene.setCursor(Cursor.CLOSED_HAND);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Scene in Detail");
		primaryStage.show();	
		}
}

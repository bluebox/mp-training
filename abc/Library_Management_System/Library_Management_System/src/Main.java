import controller.MemberManagementController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		// TODO Auto-generated method stud
		 MemberManagementController controller=new MemberManagementController();
		 //controller.start();
		 launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stud
		        Parent root = FXMLLoader.load(getClass().getResource("/resources/Main.fxml"));
		        primaryStage.setTitle("Library Management System");
		        primaryStage.setScene(new Scene(root, 400, 400));
		        primaryStage.show();
		    }
}

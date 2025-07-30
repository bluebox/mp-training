package TodoList.July22;

import java.io.IOException;

import datamodel.TodoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		scene = new Scene(loadFXML("main"), 640, 480);
		stage.setScene(scene);
		stage.setTitle("Todo List");
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void stop() throws Exception {

		try {
			TodoData.getInstance().storeTodoItems();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void init() throws Exception {

		try {
			TodoData.getInstance().loadTodoItems();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
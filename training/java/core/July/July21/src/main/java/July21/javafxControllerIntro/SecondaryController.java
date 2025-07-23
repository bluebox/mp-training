package July21.javafxControllerIntro;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController {

	@FXML
	private TextField nameField;
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private CheckBox checkBox;
	@FXML
	private Label label;

	@FXML
	public void initialize() {
		button1.setDisable(true);
		button2.setDisable(true);
	}

	@SuppressWarnings("exports")
	@FXML
	public void onButtonClick(ActionEvent e) {
		if (e.getSource().equals(button1)) {
			System.out.println("button 1 " + nameField.getText());
		} else if (e.getSource().equals(button2)) {
			System.out.println("button 2 " + nameField.getText());
		}

		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
					System.out.println("in try start " + s);
					Thread.sleep(10000);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
							System.out.println("in platform run " + s);
							label.setText("done something");
						}
					});
				} catch (InterruptedException e1) {
					System.out.println(e1.getMessage());
				}
			}

		};
		new Thread(task).start();
		if (checkBox.isSelected()) {
			nameField.clear();
			button1.setDisable(true);
			button2.setDisable(true);
		}
	}

	@FXML
	public void handleKeyReleased() {
		String text = nameField.getText();
		boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
		button1.setDisable(disableButtons);
		button2.setDisable(disableButtons);

	}

	@FXML
	public void handleChange() {

	}
}
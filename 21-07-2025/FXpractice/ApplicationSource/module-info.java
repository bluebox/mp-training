module FXPractice {
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	
	exports application;
	opens application to javafx.graphics, javafx.fxml;
}

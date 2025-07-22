module CalculatorUsingFX {
	requires javafx.controls;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.fxml;
	exports application;
	opens application to javafx.graphics, javafx.fxml;
}

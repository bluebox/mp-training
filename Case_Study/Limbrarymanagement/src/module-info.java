/**
 * 
 */
/**
 * 
 */
module library_management_system {
	requires java.sql;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	opens application to javafx.graphics, javafx.fxml;
	opens domain to javafx.graphics, javafx.base;
	opens Controller to javafx.fxml;
	exports application;
	exports Controller;
}
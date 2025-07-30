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
	requires javafx.base;
	requires jdk.incubator.vector;
	requires junit;
	opens application to javafx.graphics, javafx.fxml;
	opens domain to javafx.graphics, javafx.base;
	opens Controller to javafx.fxml;
	exports application;
	exports Controller;
}
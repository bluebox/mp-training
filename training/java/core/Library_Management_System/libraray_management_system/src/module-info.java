module libraray_management_system {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires jdk.compiler;
	
	opens application to javafx.graphics, javafx.fxml;
	exports Controller;
	exports application;
	opens domain to javafx.graphics, javafx.base;
}

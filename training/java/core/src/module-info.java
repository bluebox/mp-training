module NewLibraryManagementSystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	
	opens controller to javafx.graphics, javafx.fxml;
	
	exports application;
	exports controller;
	exports Domain;
}

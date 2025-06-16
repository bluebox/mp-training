/**
 * 
 */
/**
 * 
 */
module LibraryManagementSystem {
	requires java.sql;
	requires junit;
	requires org.junit.jupiter.api;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	opens com.library.controller to javafx.fxml;
	exports com.library.controller to javafx.graphics;
	 
	 
}
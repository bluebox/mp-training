module MP_Trainee_Assignment_3 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires java.desktop;

	opens PustakaLokam.library.controller to javafx.fxml;

	exports application;
	exports PustakaLokam.library.controller;

	opens PustakaLokam.library.controller.book to javafx.fxml;
	opens PustakaLokam.library.model to javafx.base, javafx.fxml;

}
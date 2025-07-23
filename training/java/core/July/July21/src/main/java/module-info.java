module July21.javafxControllerIntro {
    requires javafx.controls;
    requires javafx.fxml;

    opens July21.javafxControllerIntro to javafx.fxml;
    exports July21.javafxControllerIntro;
}

module BookUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires mysql.connector.j;
    requires java.sql;
    requires jdk.incubator.vector;

    exports com.LibraryManagement.controller;

    opens com.LibraryManagement.controller to javafx.fxml;
    opens com.LibraryManagement.application to javafx.graphics, javafx.fxml;

    // 🔧 Add this line to fix the IllegalAccessException:
    opens com.LibraryManagement.models to javafx.base, javafx.fxml;
}

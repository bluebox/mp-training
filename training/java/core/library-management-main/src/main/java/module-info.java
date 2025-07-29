/* doesn't work with source level 1.8:
module com.library_management {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.library_management to javafx.fxml;
    exports com.library_management;
}
*/

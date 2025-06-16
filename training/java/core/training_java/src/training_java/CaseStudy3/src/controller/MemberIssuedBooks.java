package controller;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MemberIssuedBooks {
    public VBox getView() {
        VBox root = new VBox(10);
        ComboBox<String> memberBox = new ComboBox<>();
        ListView<String> issuedBooks = new ListView<>();

        root.getChildren().addAll(new Label("Select Member"), memberBox, issuedBooks);
        return root;
    }
}

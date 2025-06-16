package controller;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class BookUsedByMembers {
    public VBox getView() {
        VBox root = new VBox(10);
        ComboBox<String> bookBox = new ComboBox<>();
        ListView<String> usedMembers = new ListView<>();

        root.getChildren().addAll(new Label("Select Book"), bookBox, usedMembers);
        return root;
    }
}

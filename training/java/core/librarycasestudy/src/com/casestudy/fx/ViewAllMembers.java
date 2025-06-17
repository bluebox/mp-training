package com.casestudy.fx;

import java.util.List;

import com.casestudy.domain.Member;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewAllMembers extends VBox {

    public ViewAllMembers(List<Member> members) {
        this.setPadding(new Insets(10));

        Label heading = new Label("All Members");
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TableView<Member> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(members));

        TableColumn<Member, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());
        idCol.setMinWidth(60);

        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        nameCol.setMinWidth(350);

        TableColumn<Member, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        emailCol.setMinWidth(350);

        TableColumn<Member, Long> mobileCol = new TableColumn<>("Mobile");
        mobileCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getMobile()).asObject());
        mobileCol.setMinWidth(120);

        TableColumn<Member, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGender().toString()));
        genderCol.setMinWidth(100);

        TableColumn<Member, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));
        addressCol.setMinWidth(250);

        // Enable word wrap for address column
        addressCol.setCellFactory(col -> {
            return new TableCell<Member, String>() {
                private final Text text = new Text();

                {
                    text.wrappingWidthProperty().bind(col.widthProperty().subtract(10)); // Allow text to wrap
                    text.getStyleClass().add("wrapped-text");
                    setGraphic(text);
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        text.setText("");
                    } else {
                        text.setText(item);
                    }
                }
            };
        });

        table.getColumns().addAll(idCol, nameCol, emailCol, mobileCol, genderCol, addressCol);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); // allow manual column widths

        ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);
        scrollPane.setStyle("-fx-background-color: transparent;");

        this.getChildren().addAll(heading, scrollPane);
    }
}

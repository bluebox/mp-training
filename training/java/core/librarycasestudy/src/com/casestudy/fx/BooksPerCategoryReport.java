package com.casestudy.fx;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class BooksPerCategoryReport extends VBox {

    public BooksPerCategoryReport(Map<String, Long> categoryCountMap) {
        this.setSpacing(10);
        this.getChildren().add(new Text("Books Count per Category"));

        // Convert Map to list of entries
        List<Map.Entry<String, Long>> entries = categoryCountMap.entrySet()
                .stream()
                .collect(Collectors.toList());

        TableView<Map.Entry<String, Long>> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(entries));

        TableColumn<Map.Entry<String, Long>, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getKey())
        );

        TableColumn<Map.Entry<String, Long>, String> countCol = new TableColumn<>("Count");
        countCol.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getValue()))
        );

        table.getColumns().addAll(categoryCol, countCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.getChildren().add(table);
    }
}

package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import Service.ServiceInterface;
import Service.ServiceLayer;

import java.util.List;
import java.util.Map;

public class BooksPerCategoryController {

    private ServiceInterface service = new ServiceLayer();

    @FXML private TableView<Map.Entry<String, Long>> categoryTable;
    @FXML private TableColumn<Map.Entry<String, Long>, String> categoryColumn;
    @FXML private TableColumn<Map.Entry<String, Long>, Long> countColumn;

    @FXML
    public void initialize() {
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKey()));
        countColumn.setCellValueFactory(data -> new SimpleLongProperty(data.getValue().getValue()).asObject());
        loadData();
    }

    private void loadData() {
        try {
            Map<String, Long> data = service.getBookCountByCategory();
            categoryTable.setItems(FXCollections.observableArrayList(data.entrySet()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
}

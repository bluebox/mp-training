package Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Service.Reports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BooksperCategory {

    @FXML
    private TableView<CategoryCount> categoryTable;

    @FXML
    private TableColumn<CategoryCount, String> categoryColumn;

    @FXML
    private TableColumn<CategoryCount, Long> countColumn;

    private Reports reports = new Reports();

    @FXML
    public void initialize() {
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
        countColumn.setCellValueFactory(data -> new SimpleLongProperty(data.getValue().getCount()).asObject());

        loadCategoryCounts();
    }

    private void loadCategoryCounts() {
        try {
            Map<String, Long> map = reports.count_of_books_percategory(); 
            List<CategoryCount> rows = new ArrayList<>();
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                rows.add(new CategoryCount(entry.getKey(), entry.getValue()));
            }

            ObservableList<CategoryCount> data = FXCollections.observableArrayList(rows);
            categoryTable.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static class CategoryCount {
        private final String category;
        private final Long count;

        public CategoryCount(String category, Long count) {
            this.category = category;
            this.count = count;
        }

        public String getCategory() {
            return category;
        }

        public Long getCount() {
            return count;
        }
    }
    @FXML
    private void handleBack() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/applicationView/HomeView.fxml")); // adjust the path
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) categoryTable.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("Home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

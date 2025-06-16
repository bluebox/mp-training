package Controller;

import casestudy.LibraryException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Map;

import Service.BookService;

public class ViewBooksByCategoryController {
    @FXML private TableView<CategoryCount> categoryTable;
    @FXML private TableColumn<CategoryCount, String> categoryColumn;
    @FXML private TableColumn<CategoryCount, Long> countColumn;
    private BookService bookService = new BookService();
    private Stage primaryStage;

    public ViewBooksByCategoryController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        categoryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory()));
        countColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleLongProperty(cellData.getValue().getCount()).asObject());
        loadCategoryReport();
    }

    @FXML
    private void goBack() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        MainController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleClear() {
        categoryTable.getItems().clear();
    }

    private void loadCategoryReport() {
        try {
            Map<String, Long> categoryCounts = bookService.getBooksCountByCategory();
            categoryTable.setItems(FXCollections.observableArrayList(
                categoryCounts.entrySet().stream()
                    .map(entry -> new CategoryCount(entry.getKey(), entry.getValue()))
                    .collect(java.util.stream.Collectors.toList())
            ));
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Helper class for TableView
    public static class CategoryCount {
        private final String category;
        private final Long count;

        public CategoryCount(String category, Long count) {
            this.category = category;
            this.count = count;
        }

        public String getCategory() { return category; }
        public Long getCount() { return count; }
    }
}
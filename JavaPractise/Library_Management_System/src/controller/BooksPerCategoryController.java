package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import service.ReportService;
import javafx.util.Pair;

public class BooksPerCategoryController {

    @FXML private TableView<Pair<String, Long>> categoryTable;
    @FXML private TableColumn<Pair<String, Long>, String> colCategory;
    @FXML private TableColumn<Pair<String, Long>, Long> colCount;

    private ReportService reportService = new ReportService();

    public void initialize() {
        colCategory.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getKey()));
        colCount.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getValue()).asObject());

        categoryTable.getItems().setAll(reportService.getBooksCountPerCategory());
    }
    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/RecordOperations.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void handleGoHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

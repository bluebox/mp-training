package controller;

import exceptions.ManagementException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import service.ReportServiceInterface;

import java.util.Map;

public class BooksPerCategoryController {

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
	private TableView<CategoryCount> categoryTable;
	@FXML
	private TableColumn<CategoryCount, String> colCategory;
	@FXML
	private TableColumn<CategoryCount, Long> colCount;

	private ReportServiceInterface reportService = new ReportService();

	@FXML
	public void initialize() {
		colCategory.setCellValueFactory(
				data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
		colCount.setCellValueFactory(
				data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getCount()).asObject());

		try {
			Map<String, Long> countMap = reportService.getBooksCountPerCategory();
			ObservableList<CategoryCount> data = FXCollections.observableArrayList();

			countMap.forEach((category, count) -> data.add(new CategoryCount(category, count)));
			categoryTable.setItems(data);
		} catch (ManagementException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleBack(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/resources/RecordOperations.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	private void handleGoHome(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/resources/Main.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
}

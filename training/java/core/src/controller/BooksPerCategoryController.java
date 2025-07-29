package controller;



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BooksPerCategoryController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	
    @FXML
    private TableView<CategoryCount> categoryTable;

    @FXML
    private TableColumn<CategoryCount, String> categoryColumn;

    @FXML
    private TableColumn<CategoryCount, Long> countColumn;

    private ReportMenuController reports = new ReportMenuController();

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
    
    
	// for going to books
    public void gotoHome(ActionEvent event) throws IOException {
 	   root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
 	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
 	    stage.setTitle("Library Management System");
 	    scene=new Scene(root);
 	    stage.setScene(scene);
 	    stage.show();
    }

}


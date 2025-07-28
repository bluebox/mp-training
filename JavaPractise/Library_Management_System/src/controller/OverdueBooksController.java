package controller;

import domain.IssueRecord;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ReportService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OverdueBooksController {

    @FXML private TableView<IssueRecord> overdueTable;
    @FXML private TableColumn<IssueRecord, Integer> colBookId;
    @FXML private TableColumn<IssueRecord, String> colTitle;
    @FXML private TableColumn<IssueRecord, String> colMember;
    @FXML private TableColumn<IssueRecord, String> colReturnDate;

    private final ReportService reportService = new ReportService();

    public void initialize() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));

        // Format LocalDate to string
        colReturnDate.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return new SimpleStringProperty(
                    cellData.getValue().getReturnDate().format(formatter)
            );
        });

        List<IssueRecord> list = reportService.getOverdueBooks();
        overdueTable.getItems().setAll(list);
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws Exception {
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

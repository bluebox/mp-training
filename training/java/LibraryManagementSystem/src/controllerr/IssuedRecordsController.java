package controllerr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.IssueRecord;
import service.IssueService;
import java.time.LocalDate;

public class IssuedRecordsController {
    @FXML private TableView<IssueRecord> table;
    @FXML private TableColumn<IssueRecord, Integer> issueIdCol;
    @FXML private TableColumn<IssueRecord, Integer> bookIdCol;
    @FXML private TableColumn<IssueRecord, Integer> memberIdCol;
    @FXML private TableColumn<IssueRecord, Character> statusCol;
    @FXML private TableColumn<IssueRecord, LocalDate> issueDateCol;
    @FXML private TableColumn<IssueRecord, LocalDate> returnDateCol;

    private final IssueService issueService = new IssueService();

    @FXML
    public void initialize() {
        issueIdCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIssueId()).asObject());
        bookIdCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getBookId()).asObject());
        memberIdCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getMemberId()).asObject());
        statusCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getStatus()));
        issueDateCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getIssueDate()));
        returnDateCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getReturnDate()));
        try {
            ObservableList<IssueRecord> data = FXCollections.observableArrayList(issueService.getAllIssuedRecords());
            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
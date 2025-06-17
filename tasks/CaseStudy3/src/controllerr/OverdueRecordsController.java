package controllerr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.IssueRecord;
import service.OverdueService;

import java.time.LocalDate;

public class OverdueRecordsController {

    @FXML private TableView<IssueRecord> table;
    @FXML private TableColumn<IssueRecord, Integer> issueIdCol;
    @FXML private TableColumn<IssueRecord, Integer> bookIdCol;
    @FXML private TableColumn<IssueRecord, Integer> memberIdCol;
    @FXML private TableColumn<IssueRecord, LocalDate> issueDateCol;

    private final OverdueService overdueService = new OverdueService();

    @FXML
    public void initialize() {
        issueIdCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getIssueId()).asObject());
        bookIdCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getBookId()).asObject());
        memberIdCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getMemberId()).asObject());
        issueDateCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getIssueDate()));

        try {
            ObservableList<IssueRecord> data = FXCollections.observableArrayList(overdueService.getOverdueRecords());
            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

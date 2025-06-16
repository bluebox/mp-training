package librarySystem.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import librarySystem.Service.libraryServices;
import model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Map;
import java.util.stream.Collectors;

public class ReportsController {
    @FXML private TableView<IssueRecordPojo> reportTable;
    @FXML private TableColumn<IssueRecordPojo,Integer> colReportBookId, colReportMemberId;
    @FXML private TableColumn<IssueRecordPojo,java.sql.Date> colReportIssueDate, colReportReturnDate;
    @FXML private ListView<String> countList, activeMembersList;
    private final libraryServices service = new libraryServices();

    @FXML public void initialize() {
        colReportBookId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getBookId()).asObject());
        colReportMemberId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getMemberId()).asObject());
        colReportIssueDate.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getIssueDate()));
        colReportReturnDate.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getReturnDate()));
    }

    public void showOverdue() {
        ObservableList<IssueRecordPojo> data = FXCollections.observableArrayList(service.getOverdueBooks(14));
        reportTable.setItems(data);
    }

    public void showCounts() {
        Map<String,Long> map = service.getBookCountPerCategory();
        ObservableList<String> list = FXCollections.observableArrayList();
        map.forEach((cat,cnt) -> list.add(cat + ": " + cnt));
        countList.setItems(list);
    }

    public void showActiveMembers() {
        ObservableList<MemberPojo> members = FXCollections.observableArrayList(service.getMembersWithActiveIssues());
        ObservableList<String> display = members.stream()
            .map(m -> m.getMemberId() + " - " + m.getName())
            .collect(Collectors.toCollection(FXCollections::observableArrayList));
        activeMembersList.setItems(display);
    }
}

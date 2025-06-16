package librarySystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.event.ActionEvent;
import librarySystem.Service.libraryServices;
import model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;
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
    	int overDueLimit = 5;
        ObservableList<IssueRecordPojo> data = FXCollections.observableArrayList(service.getOverdueBooks(overDueLimit));
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
    public void handleBooks(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/BookView.fxml");
    }

    public void handleMembers(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/MemberView.fxml");
    }

    public void handleIssueReturn(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/IssueReturn.fxml");
    }

    public void handleReports(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/ReportsView.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) reportTable.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Library System");
        stage.show();
    }
}

package Controller;
import java.sql.Date;
import java.util.List;
import domain.Issue_records;
import domain.checking_enum.Status_issue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
public class IssueprintallrecordsController {
    @FXML
    private TableView<Issue_records> issueTable;
    @FXML
    private TableColumn<Issue_records, Integer> bookIdColumn;
    @FXML
    private TableColumn<Issue_records, Integer> memberIdColumn;
    @FXML
    private TableColumn<Issue_records, Status_issue> statusColumn;
    @FXML
    private TableColumn<Issue_records, Date> issueDateColumn;
    @FXML
    private TableColumn<Issue_records, Date> returnDateColumn;
    public Service.IssueRecordService issueService = new Service.IssueRecordService();
    @FXML
    public void initialize() {
        try {
            bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookid"));
            memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberid"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status_issue"));
            issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("Issuedate"));
            returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("Returndate"));

            List<Issue_records> records = issueService.getAllIssuedRecords();
            issueTable.getItems().setAll(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleBackToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/applicationView/homeview.fxml"));
//            Stage stage = new Stage();
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Home View");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleReturnBook() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/applicationView/Returnbook.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Return Book");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleIssueBook() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/applicationView/issuebook.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Issue Book");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
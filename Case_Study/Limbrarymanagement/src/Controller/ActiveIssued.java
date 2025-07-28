package Controller;

import java.util.List;

import Service.Reports;
import domain.Book;
import domain.Member;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class ActiveIssued {
	  @FXML private TableView<Member> membersTable;
	    @FXML private TableColumn<Member, Integer> idColumn;
	    @FXML private TableColumn<Member, String> nameColumn;
	    @FXML private TableColumn<Member, String> emailColumn;
	    @FXML private TableColumn<Member, Long> mobileColumn;
	    @FXML private TableColumn<Member, String> addressColumn;
	    private Reports reports=new Reports();

	    @FXML
	    public void initialize() {
	        System.out.println("OverdueBooksController initialized");

	        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberid()).asObject());
	        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
	        emailColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
	        mobileColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getMobile()).asObject());
	        addressColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));

	        loadActiveIssue();
	    }

	    private void loadActiveIssue() {
	        try {
	            List<Member> Members = reports.members_with_statusissue();
	            membersTable.getItems().clear();

	            if (Members != null && !Members.isEmpty()) {
	            	membersTable.getItems().addAll(Members);
	                System.out.println("Loaded active status: " + Members.size());
	            } else {
	                System.out.println("No active status.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    @FXML
	    private void handleBack() {
	        try {
	            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/applicationView/HomeView.fxml")); 
	            javafx.scene.Parent root = loader.load();
	            javafx.stage.Stage stage = (javafx.stage.Stage) membersTable.getScene().getWindow();
	            stage.setScene(new javafx.scene.Scene(root));
	            stage.setTitle("Home");
	        } catch (Exception e) {
	            e.printStackTrace();
	            showAlert("Error", "Could not navigate to Home.");
	        }
	    }

	    
	    private void showAlert(String title, String message) {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
	}


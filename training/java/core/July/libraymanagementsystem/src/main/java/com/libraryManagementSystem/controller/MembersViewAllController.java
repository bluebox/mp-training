package com.libraryManagementSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;
import com.libraryManagementSystem.services.MemberService;
import com.libraryManagementSystem.services.impl.MemberServiceImpl;
import com.libraryManagementSystem.utilities.MemberGender;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MembersViewAllController implements Initializable {
	@FXML
	private TableView<Member> memberTableView;
	@FXML
	private TableColumn<Member, Integer> memberID;
	@FXML
	private TableColumn<Member, String> name;
	@FXML
	private TableColumn<Member, String> email;
	@FXML
	private TableColumn<Member, Long> mobile;
	@FXML
	private TableColumn<Member, MemberGender> gender;
	@FXML
	private TableColumn<Member, String> address;
	@FXML
	private TableColumn<Member, Void> actions;
	@FXML
	private Label error;

//	private static Member memberIdSelected = null;

	private MemberService memberService = new MemberServiceImpl();

	@FXML
	private void switchToMembers() throws Exception {
		App.setRoot("Members");
	}

	@FXML
	private void switchToPrimary() throws IOException {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<Member> members;

		try {
			memberTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			members = memberService.getMembers();

			memberID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
			name.setCellValueFactory(new PropertyValueFactory<>("name"));
			email.setCellValueFactory(new PropertyValueFactory<>("email"));
			mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
			gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
			address.setCellValueFactory(new PropertyValueFactory<>("address"));

			ObservableList<Member> memberList = FXCollections.observableArrayList();
			members.forEach(member -> {
				memberList.add(member);
			});

			memberTableView.setItems(memberList);

			for (TableColumn<Member, ?> column : memberTableView.getColumns()) {
				if (memberList.isEmpty() || column.getCellData(0) instanceof String) {
					@SuppressWarnings("unchecked")
					TableColumn<Member, String> stringColumn = (TableColumn<Member, String>) column;

					stringColumn.setCellFactory(col -> new TableCell<Member, String>() {
						private final Text text = new Text();

						{
							text.wrappingWidthProperty().bind(col.widthProperty().subtract(10));
							text.setStyle("-fx-padding: 5px;");
							setGraphic(text);
						}

						@Override
						protected void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							text.setText(empty || item == null ? "" : item);
						}
					});
				}
			}
			addActionButtons();

		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		} catch (StatementPreparationException e) {
			e.printStackTrace();
		}

	}

	private void addActionButtons() {
		actions.setCellFactory(col -> new TableCell<Member, Void>() {

			private final Button deleteButton = new Button("Delete");
			private final HBox actionBox = new HBox(10, deleteButton);

			{
				deleteButton.setOnAction(event -> {

					Member memberData = memberTableView.getItems().get(getIndex());

					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setTitle("Delete Member");
					alert.setHeaderText("Delete item: " + memberData.getName());
					alert.setContentText("Are you sure?");

					Optional<ButtonType> result = alert.showAndWait();

					if (result.isPresent() && (result.get() == ButtonType.OK)) {

						Alert deleteShow = new Alert(Alert.AlertType.INFORMATION);

						try {
							memberService.deleteMember(memberData);
							initialize(null, null);
							deleteShow.setContentText(memberData.getName() + " deleted successfully");
							deleteShow.show();
						} catch (DatabaseConnectionException e) {
							e.printStackTrace();
						} catch (StatementPreparationException e) {
							e.printStackTrace();
						}

					} else {
						Alert deleteShow = new Alert(Alert.AlertType.INFORMATION);
						deleteShow.setContentText("Cancelled");
						deleteShow.show();
					}

				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setGraphic(empty ? null : actionBox);
			}

		});
	}

}

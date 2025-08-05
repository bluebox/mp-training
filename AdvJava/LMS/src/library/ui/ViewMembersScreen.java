package library.ui;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import library.exception.LibraryException; // Using base LibraryException
import library.model.Member;
import library.model.enums.Gender;
import library.service.MemberServiceImpl; // Using MemberService
import library.service.interfaces.MemberService; // Using MemberServiceI interface

public class ViewMembersScreen {

    @FXML
    private TableView<Member> membersTable;
    @FXML
    private TableColumn<Member, Void> selectColumn; // Column for checkboxes
    @FXML
    private TableColumn<Member, Integer> memberIDColumn;
    @FXML
    private TableColumn<Member, String> nameColumn;
    @FXML
    private TableColumn<Member, String> emailColumn;
    @FXML
    private TableColumn<Member, Long> phoneNumberColumn;
    @FXML
    private TableColumn<Member, Gender> genderColumn;
    @FXML
    private TableColumn<Member, String> addressColumn;
    @FXML
    private TableColumn<Member, Void> actionsColumn;
    @FXML
    private Label statusLabel;

    private MemberService memberService; // Use service interface
    private ObservableList<Member> memberList;
    private Set<Member> selectedMembersSet; // To track selected members

    @FXML
    public void initialize() {
        memberService = new MemberServiceImpl(); // Initialize service
        memberList = FXCollections.observableArrayList();
        selectedMembersSet = new HashSet<>(); // Initialize set for tracking selections

        // Setup for checkbox column (without a 'selected' property in POJO)
        selectColumn.setCellValueFactory(new PropertyValueFactory<>(null)); // No direct property binding
        selectColumn.setCellFactory(new Callback<TableColumn<Member, Void>, TableCell<Member, Void>>() {
            @Override
            public TableCell<Member, Void> call(final TableColumn<Member, Void> param) {
                final TableCell<Member, Void> cell = new TableCell<>() {
                    private final CheckBox checkBox = new CheckBox();

                    {
                        // Listener for checkbox state change
                        checkBox.setOnAction(event -> {
                            Member member = getTableView().getItems().get(getIndex());
                            if (checkBox.isSelected()) {
                                selectedMembersSet.add(member);
                            } else {
                                selectedMembersSet.remove(member);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Member member = getTableView().getItems().get(getIndex());
                            // Ensure checkbox state reflects current selection status
                            checkBox.setSelected(selectedMembersSet.contains(member));
                            setGraphic(checkBox);
                        }
                    }
                };
                return cell;
            }
        });
        selectColumn.setEditable(true); // Allow checkboxes to be toggled

        membersTable.setEditable(true); // Make table editable for checkbox interaction

        memberIDColumn.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Cell factory for Update/Delete buttons per row
        Callback<TableColumn<Member, Void>, TableCell<Member, Void>> actionsCellFactory = new Callback<>() {
            @Override
            public TableCell<Member, Void> call(final TableColumn<Member, Void> param) {
                final TableCell<Member, Void> cell = new TableCell<>() {

                    private final Button updateButton = new Button("Update");
                    private final Button deleteButton = new Button("Delete");

                    {
                        updateButton.setOnAction((ActionEvent event) -> {
                            Member member = getTableView().getItems().get(getIndex());
                            handleUpdateMember(member);
                        });

                        deleteButton.setOnAction((ActionEvent event) -> {
                            Member member = getTableView().getItems().get(getIndex());
                            handleDeleteMember(member); // Call single delete method
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox buttonsBox = new HBox(5);
                            buttonsBox.getChildren().addAll(updateButton, deleteButton);
                            setGraphic(buttonsBox);
                        }
                    }
                };
                return cell;
            }
        };

        actionsColumn.setCellFactory(actionsCellFactory);

        membersTable.setItems(memberList);
        Label noContentLabel = new Label("No members found in the library.");
        noContentLabel.setStyle("-fx-text-fill: #999999; -fx-font-size: 16px; -fx-alignment: center;");
        membersTable.setPlaceholder(noContentLabel);

        loadMembers();
    }

    private void loadMembers() {
        memberList.clear();
        selectedMembersSet.clear(); // Clear any existing selections when loading new data
        try {
            List<Member> members = memberService.getAllMembers(); // Call service method
            if (members != null && !members.isEmpty()) {
                memberList.addAll(members);
                statusLabel.setText("");
            } else {
                statusLabel.setText("No members found in the database.");
                statusLabel.setTextFill(Color.RED);
            }
        } catch (LibraryException e) { // Catch base LibraryException
            statusLabel.setText("Error loading members: " + e.getMessage());
            statusLabel.setTextFill(Color.RED);
            e.printStackTrace();
        } catch (Exception e) {
            statusLabel.setText("An unexpected error occurred while loading members: " + e.getMessage());
            statusLabel.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadMembers();
        statusLabel.setText("Members list refreshed.");
        statusLabel.setTextFill(Color.GREEN);
    }

    private void handleUpdateMember(Member selectedMember) {
        if (selectedMember != null) {
            try {
                URL fxmlLocation = getClass().getResource("/UpdateMemberForm.fxml");
                if (fxmlLocation == null) {
                    throw new IOException("UpdateMemberForm.fxml not found.");
                }
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                Parent parent = loader.load();

                UpdateMemberForm controller = loader.getController();
                controller.setMember(selectedMember);
                controller.setParentController(this);

                Stage stage = new Stage();
                stage.setTitle("Update Member");
                stage.setScene(new Scene(parent));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

            } catch (IOException e) {
                statusLabel.setText("Error opening update form: " + e.getMessage());
                statusLabel.setTextFill(Color.RED);
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Please select a member to update.");
            statusLabel.setTextFill(Color.RED);
        }
    }

    // Handles single member deletion
    private void handleDeleteMember(Member memberToDelete) {
        if (memberToDelete != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Delete Member: " + memberToDelete.getName());
            alert.setContentText("Are you sure you want to delete member ID: " + memberToDelete.getMemberID() + "?\nThis action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    memberService.deleteMember(memberToDelete); // Call service method for single delete
                    loadMembers(); // Reload table after deletion
                    statusLabel.setText("Member deleted successfully: " + memberToDelete.getName());
                    statusLabel.setTextFill(Color.GREEN);
                } catch (LibraryException e) {
                    statusLabel.setText("Error deleting member: " + e.getMessage());
                    statusLabel.setTextFill(Color.RED);
                    e.printStackTrace();
                } catch (Exception e) {
                    statusLabel.setText("An unexpected error occurred while deleting member: " + e.getMessage());
                    statusLabel.setTextFill(Color.RED);
                    e.printStackTrace();
                }
            } else {
                statusLabel.setText("Member deletion cancelled.");
                statusLabel.setTextFill(Color.RED);
            }
        } else {
            statusLabel.setText("Please select a member to delete.");
            statusLabel.setTextFill(Color.RED);
        }
    }

    @FXML // Handles batch deletion of selected members
    private void handleDeleteSelectedMembers(ActionEvent event) {
        List<Integer> idsToDelete = selectedMembersSet.stream()
                                                    .map(Member::getMemberID)
                                                    .collect(Collectors.toList());

        if (idsToDelete.isEmpty()) {
            statusLabel.setText("No members selected for deletion.");
            statusLabel.setTextFill(Color.RED);
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Batch Deletion");
        alert.setHeaderText("Delete " + idsToDelete.size() + " selected members?");
        alert.setContentText("Are you sure you want to delete the selected members?\nThis action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                memberService.deleteMembers(idsToDelete); // Call service method for batch delete
                loadMembers(); // Reload table after deletion
                statusLabel.setText("Successfully deleted " + idsToDelete.size() + " members.");
                statusLabel.setTextFill(Color.GREEN);
            } catch (LibraryException e) {
                statusLabel.setText("Error deleting selected members: " + e.getMessage());
                statusLabel.setTextFill(Color.RED);
                e.printStackTrace();
            } catch (Exception e) {
                statusLabel.setText("An unexpected error occurred while deleting selected members: " + e.getMessage());
                statusLabel.setTextFill(Color.RED);
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Selected member deletion cancelled.");
            statusLabel.setTextFill(Color.RED);
        }
    }


    public void refreshMembersTable() {
        loadMembers();
        statusLabel.setText("Member updated successfully and list refreshed.");
        statusLabel.setTextFill(Color.GREEN);
    }

    @FXML
    private void handleBackToMainMenu(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            URL fxmlLocation = getClass().getClassLoader().getResource("MainScreen.fxml");
            if (fxmlLocation == null) {
                statusLabel.setText("Error: Could not load Main Menu screen.");
                statusLabel.setTextFill(Color.RED);
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Scene scene = new Scene(root, 900, 600);
            stage.setScene(scene);
            stage.setTitle("Library Management System - Main Menu");
            stage.show();

        } catch (IOException e) {
            System.err.println("Failed to load MainScreen.fxml: " + e.getMessage());
            statusLabel.setText("Error navigating to Main Menu.");
            statusLabel.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }
}
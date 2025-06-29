package com.gym.driver;

import com.gym.classes.Gym;
import com.gym.classes.Member;
import com.gym.classes.MembershipPlan;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GymUI extends Application {

    Gym gym = new Gym();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gym Membership Management");

        Label welcomeLabel = new Label("🏋️ Welcome to the Gym Membership System");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        Button viewMembersBtn = new Button("View Members");
        Button addMemberBtn = new Button("Add New Member");
        Button assignPlanBtn = new Button("Assign Membership Plan");
        Button exitBtn = new Button("Exit");

        viewMembersBtn.setOnAction(e -> openViewMembersWindow());
        addMemberBtn.setOnAction(e -> openAddMemberWindow());
        assignPlanBtn.setOnAction(e -> openAssignPlanWindow());
        exitBtn.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(15, welcomeLabel, viewMembersBtn, addMemberBtn, assignPlanBtn, exitBtn);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f0f8ff;");
        layout.setPrefWidth(400);
        layout.setPrefHeight(300);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openViewMembersWindow() {
        Stage stage = new Stage();
        stage.setTitle("All Members");

        TableView<Member> table = new TableView<>();
        ObservableList<Member> data = FXCollections.observableArrayList(gym.getMembers());

        TableColumn<Member, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Member, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Member, String> planCol = new TableColumn<>("Plan");
        planCol.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(
                param.getValue().memPlan == null ? "No Plan" : param.getValue().memPlan.planName
        ));

        table.getColumns().addAll(idCol, nameCol, ageCol, planCol);
        table.setItems(data);

        VBox layout = new VBox(table);
        layout.setPadding(new Insets(10));

        stage.setScene(new Scene(layout, 500, 300));
        stage.show();
    }

    private void openAddMemberWindow() {
        Stage stage = new Stage();
        stage.setTitle("Add New Member");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        Spinner<Integer> ageSpinner = new Spinner<>(12, 120, 25);
        Spinner<Integer> weightSpinner = new Spinner<>(20, 200, 70);
        Spinner<Integer> heightSpinner = new Spinner<>(100, 250, 170);

        Button submit = new Button("Add Member");

        submit.setOnAction(e -> {
            String name = nameField.getText();
            if (name.isEmpty()) {
                showAlert("Error", "Name cannot be empty.");
                return;
            }
            gym.addNewMember(name, ageSpinner.getValue(), heightSpinner.getValue(), weightSpinner.getValue());
            showAlert("Success", "Member added successfully.");
            stage.close();
        });

        VBox layout = new VBox(10,
                new Label("Name:"), nameField,
                new Label("Age:"), ageSpinner,
                new Label("Weight (kg):"), weightSpinner,
                new Label("Height (cm):"), heightSpinner,
                submit
        );
        layout.setPadding(new Insets(20));
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        stage.setScene(new Scene(layout, 350, 400));
        stage.show();
    }

    private void openAssignPlanWindow() {
        Stage stage = new Stage();
        stage.setTitle("Assign Membership Plan");

        ComboBox<Member> memberComboBox = new ComboBox<>();
        memberComboBox.setItems(FXCollections.observableArrayList(gym.getMembers()));
        memberComboBox.setPromptText("Select Member");

        ComboBox<MembershipPlan> planComboBox = new ComboBox<>();
        planComboBox.setItems(FXCollections.observableArrayList(Gym.getPlans()));
        planComboBox.setPromptText("Select Plan");

        Button submit = new Button("Assign Plan");

        submit.setOnAction(e -> {
            Member member = memberComboBox.getValue();
            MembershipPlan plan = planComboBox.getValue();
            if (member == null || plan == null) {
                showAlert("Error", "Select both member and plan.");
                return;
            }
            gym.assignPlanToMember(member.memberId, plan.planName);
            showAlert("Success", "Plan assigned successfully.");
            stage.close();
        });

        VBox layout = new VBox(15,
                new Label("Select Member:"), memberComboBox,
                new Label("Select Plan:"), planComboBox,
                submit
        );
        layout.setPadding(new Insets(20));
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        stage.setScene(new Scene(layout, 350, 300));
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        
    }
}

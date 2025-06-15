package com.casestudyfx;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.List;
import com.casestudy.Member;

public class ViewAllMembers extends VBox {

    public ViewAllMembers(List<Member> members) {
        TableView<Member> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(members));

        TableColumn<Member, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());

        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Member, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<Member, Long> mobileCol = new TableColumn<>("Mobile");
        mobileCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getMobile()).asObject());

        TableColumn<Member, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGender().toString()));

        TableColumn<Member, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));

        table.getColumns().addAll(idCol, nameCol, emailCol, mobileCol, genderCol, addressCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.getChildren().addAll(new Label("📋 All Members"), table);
    }
}

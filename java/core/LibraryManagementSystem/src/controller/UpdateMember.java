package controller;

import Pojo.Member;
import Service.LibraryService;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class UpdateMember {
    public VBox getView() {
        VBox root = new VBox(10);

        Label heading = new Label("Update Member");

        TextField memberIdField = new TextField();
        memberIdField.setPromptText("Member ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField mobileField = new TextField();
        mobileField.setPromptText("Mobile");

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female");
        genderBox.setPromptText("Gender");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        Button updateButton = new Button("Update Member");
        Label message=new Label();
        updateButton.setOnAction(ev -> {
            try {
                Member member = new Member();
                member.setName(nameField.getText());
                member.setEmail(emailField.getText());
                member.setMemberId(Integer.parseInt(memberIdField.getText()));
                member.setMobile(Long.parseLong(mobileField.getText()));
                member.setGender(genderBox.getValue().charAt(0));
                member.setAddress(addressField.getText());
                LibraryService lib = new LibraryService();
                lib.updateMember(member);
                message.setText("Member Updated Successfully");
            } catch (Exception ex) {
                message.setText("Error Occurred");
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(
            heading,
            memberIdField,
            nameField,
            emailField,
            mobileField,
            genderBox,
            addressField,
            updateButton,message
        );

        return root;
    }
}

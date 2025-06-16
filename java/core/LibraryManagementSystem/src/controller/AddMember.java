package controller;

import Pojo.Member;
import Service.LibraryService;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AddMember {
    public VBox getView() {
        VBox root = new VBox(10);

        Label heading = new Label("Add Member");

        TextField name = new TextField();
        name.setPromptText("Name");

        TextField email = new TextField();
        email.setPromptText("Email");

        TextField mobile = new TextField();
        mobile.setPromptText("Mobile");

        ComboBox<String> gender = new ComboBox<>();
        gender.getItems().addAll("Male", "Female"); // M = Male, F = Female
        gender.setPromptText("Gender");

        TextField address = new TextField();
        address.setPromptText("Address");

        Button submit = new Button("Add Member");
        Label message = new Label();
        submit.setOnAction(ev -> {
            try {
                Member member = new Member();
                member.setName(name.getText());
                member.setEmail(email.getText());
                member.setMobile(Long.parseLong(mobile.getText()));
                member.setGender(gender.getValue().charAt(0));
                member.setAddress(address.getText());
                LibraryService lib = new LibraryService();
                lib.addMember(member);
                message.setText("Member Added Successfully");
            } catch (Exception ex) {
                message.setText("Error Occurred");
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(
            heading,
            name,
            email,
            mobile,
            gender,
            address,
            submit,message
        );

        return root;
    }
}

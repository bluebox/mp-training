package com.medplus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import com.medplus.model.Member;
import com.medplus.service.LibraryService;

public class AddMemberViewController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField mobileField;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TextField addressField;

    private final LibraryService service = new LibraryService();

    @FXML
    public void handleAddMember() {
        Member member = new Member(0, nameField.getText(), emailField.getText(), Integer.parseInt(mobileField.getText()), genderCombo.getValue().charAt(0), addressField.getText());
        try {
            service.addMember(member);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.medplus.controller;

public class MemberListViewController {
    @FXML private TableView<Member> memberTable;
    private final LibraryService service = new LibraryService();
    @FXML
    public void initialize() {
        try {
            ObservableList<Member> list = FXCollections.observableArrayList(service.getAllMembers());
            memberTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
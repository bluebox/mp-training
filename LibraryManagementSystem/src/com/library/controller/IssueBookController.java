package com.library.controller;

import com.Models.Issue;

//package com.library.controller;

//import com.library.controller.IssueRecord;
import com.Service.IssueService;
//import com.library.util.AlertMsg;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Date;

public class IssueBookController {

  @FXML private TextField bookIdField;
  @FXML private TextField memberIdField;

  private final IssueService issueService = new IssueService();

  @FXML
  private void issueBook() {
      try {
          int bookId = Integer.parseInt(bookIdField.getText());
          int memberId = Integer.parseInt(memberIdField.getText());

          Issue issueRecord = new Issue(
              bookId,
              memberId,
              'I',
              new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
              null        
          );

          issueService.issueBook(bookId,memberId);

          bookIdField.clear();
          memberIdField.clear();
      } catch (Exception e) {
          AlertMsg.showError("Issue failed :"+e.getMessage());
      }
  }
}

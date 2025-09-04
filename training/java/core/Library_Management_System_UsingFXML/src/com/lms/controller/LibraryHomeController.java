package com.lms.controller;


import java.io.IOException;
import com.lms.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class LibraryHomeController {

    @FXML 
    private VBox dynamicParent;
   
    @FXML
    public void initialize() {
    	try {
    		changePage("Reports");

    	}catch(IOException e) {
    		System.out.println("Had a problem changing page open an alert here");
    	}
    }
    
    
    @FXML
    public void addBookButtonClick()  {
        try {
			changePage("AddNewBook");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    public void viewAllBooksClick()  {
        try {
			changePage("ViewAllBooks");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    public void addMemberButtonClick()  {
        try {
			changePage("AddNewMember");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    public void viewAllMembersClick()  {
        try {
			changePage("ViewAllMembers");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    public void issueBookButtonClick()  {
    	try {
			changePage("IssueBook");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML 
    public void returnBookButtonClick()  {
        try {
			changePage("ReturnBook");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void viewIssuedRecordsClick() {
        try {
			changePage("ViewAllissuedRecords");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void bookMembersButtonClick() {
    	try {
			changePage("BookMembers");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @FXML
    public void ReportsClick() {
    	try {
			changePage("Reports");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @FXML
    public void memberBooksClick() {
    	try {
			changePage("MemberBooks");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    public void bookMembersClick() {
    	try {
			changePage("BookMembers");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    public void changePage(String fxml) throws IOException {
    	dynamicParent.getChildren().clear();
    	dynamicParent.getChildren().add(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/lms/view/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
}

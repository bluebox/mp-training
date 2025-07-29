package com.library_management.utilities;

import com.library_management.domain.BookAvailability;
import com.library_management.domain.BookCategory;
import com.library_management.domain.BookStatus;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomBookForTableView {
	private final SimpleIntegerProperty bookId;
	private final SimpleStringProperty title;
	private final SimpleStringProperty author;
	private final SimpleStringProperty category;
	private final SimpleStringProperty status;
	private final SimpleStringProperty availability;

    public CustomBookForTableView(int id, String title, String author, BookCategory category, BookStatus status, BookAvailability availability) {
    	this.bookId=new SimpleIntegerProperty(id);
		this.title=new SimpleStringProperty(title);
		this.author=new SimpleStringProperty(author);
		this.category=new SimpleStringProperty(String.valueOf(category));
		this.status=new SimpleStringProperty(String.valueOf(status));
		this.availability=new SimpleStringProperty(String.valueOf(availability));
    }
    
//    Getters for values
	public int getBookId() {
		return bookId.get();
	}

	public String getTitle() {
		return title.get();
	}

	public String getAuthor() {
		return author.get();
	}

	public String getCategory() {
		return category.get();
	}

	public String getStatus() {
		return status.get();
	}

	public String getAvailability() {
		return availability.get();
	}
	
	public SimpleIntegerProperty bookIdProperty() {
		return bookId;
	}

//	getters for properties
	public SimpleStringProperty titleProperty() {
		return title;
	}

	public SimpleStringProperty authorProperty() {
		return author;
	}

	public SimpleStringProperty categoryProperty() {
		return category;
	}

	public SimpleStringProperty statusProperty() {
		return status;
	}

	public SimpleStringProperty availabilityProperty() {
		return availability;
	}

	public void setAvailability(String newAvailabilityValue) {
		availability.set(newAvailabilityValue);
		
	}
    
}

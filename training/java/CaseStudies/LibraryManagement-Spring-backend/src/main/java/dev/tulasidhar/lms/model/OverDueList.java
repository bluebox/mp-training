package dev.tulasidhar.lms.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverDueList {
	private int issueId;
	private int bookId;
	private String title;
	private String memberName;
	private LocalDate overDueDate;
	
	

}

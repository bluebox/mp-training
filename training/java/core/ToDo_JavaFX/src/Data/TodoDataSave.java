package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodoDataSave {
	
	private static TodoDataSave instance = new TodoDataSave();
	private static String filename = "TodoList.txt"; 

	private ObservableList<ToDoData> todoItems;
	private DateTimeFormatter formatter;
	
	public static TodoDataSave getInstance() {
		return instance;
		
	}
	
	private TodoDataSave() {
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
	}

	public ObservableList<ToDoData> getTodoItems() {
		return todoItems;
	}
	
	public void addTodoItem(ToDoData data) {
		todoItems.add(data);
	}
		
		

	
	public void loadTodoItems() throws IOException{
		
		todoItems = FXCollections.observableArrayList();
		Path path = Paths.get(filename);
		BufferedReader br = Files.newBufferedReader(path);
		String input;
		
		try {
			 while ((input = br.readLine())!=null) {
				 String[] itemPiece = input.split("\t");
				 
				 String shortDescription = itemPiece[0];
				 String detail = itemPiece[1];
				 String dateString = itemPiece[2];
				 
				 LocalDate date =LocalDate.parse(dateString,formatter);
				 ToDoData toDoData = new ToDoData(shortDescription, detail, date);
				 todoItems.add(toDoData);
			 }
		}finally {
			if (br != null) {
				br.close();
			}
		}
	}
	
	public void storeTodoItems() throws IOException{
		
		Path path = Paths.get(filename);
		BufferedWriter bw = Files.newBufferedWriter(path);
		
		 try {
			 Iterator<ToDoData> iter = todoItems.iterator();
			 while (iter.hasNext()) {
				ToDoData item = iter.next();
				bw.write(String.format("%s\t%s\t%s",item.getShortDescription(),item.getDetails(),item.getDeadline().format(formatter)));
				bw.newLine();
				
			}
		 }finally {
			if(bw != null) {
				bw.close();
			}
		}

	}
	
	public void deleteTodoItem(ToDoData data) {
		todoItems.remove(data);
	}
	
}

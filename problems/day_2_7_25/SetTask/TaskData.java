package day_2_7_25.SetTask;
import day_2_7_25.SetTask.Task.Status;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import day_2_7_25.SetTask.Task.Priority;

public class TaskData{
	static Set<Task> tasks=new HashSet<>();
	public static void main(String[] args) {
		Set<Task> anntasks=new HashSet<>();
		Set<Task> jilltasks=new HashSet<>();
	    Set<Task> mytasks=new HashSet<>();
		try {
		
		
		 String csvFile = "data.csv"; // Replace with your CSV file path
	        String line;
	        String cvsSplitBy = ","; // Delimiter

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(cvsSplitBy);
	                if(data[0]=="Ann") {
	                	anntasks.add(new Task(data[0],data[1],data[2],(data[3]==null)?null:Status.valueOf(data[3]),(data[4]==null)?null:Priority.valueOf(data[4])));
	     	           
	                }else if(data[0]=="jill") {
	                	jilltasks.add(new Task(data[0],data[1],data[2],(data[3]==null)?null:Status.valueOf(data[3]),(data[4]==null)?null:Priority.valueOf(data[4])));
		     	           
	                }else if(data[0]=="my") {
	                	mytasks.add(new Task(data[0],data[1],data[2],(data[3]==null)?null:Status.valueOf(data[3]),(data[4]==null)?null:Priority.valueOf(data[4])));
		     	           
	                }
	               tasks.add(new Task(data[0],data[1],data[2],(data[3]==null)?null:Status.valueOf(data[3]),(data[4]==null)?null:Priority.valueOf(data[4])));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		Task task=new Task("akdnjkn","jbchjbjhb","shbhre",Status.assigned,Priority.medium);
		Task task1=new Task("akdnjkn","jbchjbjhb","sbhbhre",Status.assigned,Priority.medium);
		
		tasks.addAll(List.of(task,task1));
		
		Sort(task);
		
	
		getTasks("Ann");
		getTasks("akdnjkn");
		
	}finally{
		
	}
	}
	
	
	public void getUnion(Set<Task> anntasks,Set<Task> jilltasks,
    Set<Task> mytasks){
		
		anntasks.addAll(jilltasks);
		anntasks.addAll(mytasks);
		 for(Task task:tasks) {
		    	System.out.println(task.toString());
		    }
		
	}
	
	public void getIntersection(Set<Task> anntasks,Set<Task> jilltasks,
		    Set<Task> mytasks){
				
				anntasks.retainAll(jilltasks);
				anntasks.retainAll(mytasks);
				 for(Task task:tasks) {
				    	System.out.println(task.toString());
				    }
				
			}
	
	public void getDifference(Set<Task> anntasks,Set<Task> jilltasks,
		    Set<Task> mytasks){
				anntasks.removeAll(jilltasks);
				anntasks.removeAll(mytasks);
				 for(Task task:tasks) {
				    	System.out.println(task.toString());
				    }
				
			}
	

	public static void getTasks(String name) {
	    for(Task task:tasks) {
	    	if(task.assignee==name || task.description==name || task.Project==name || Status.valueOf(name)==Status.notyetassigned) {
	    		System.out.println(task.toString());
	    	}
	    }
		
	}
	
	public static void Sort(Task task) {
		tasks.forEach(Task->Task.compareTo(task));
	}

}

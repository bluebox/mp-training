package SetOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	
	
	//which task is assigned to multiple members in a project
	public static void getMultiTaskAssigned(Set<Task> tasks,String project){
		
		List<Task> tasklist= new ArrayList<>(tasks);
		Set<String> result = new HashSet<>();
		for (Task task : tasklist) {
	    	  if(task.getProject()==project) {
	    		  result.add(task.getAssignee());
	    	  }
		}
	   System.out.println("The Assignees to " +project +" are : ");
	  	for(String assignee : result) {
	  		System.out.println(assignee);
	  	}
		
	}
	
	//which task assigned to multiple members
	
	public static void taskinfo(Set<Task> tasks) {
		HashMap<String,Set<String>> taskdata =new HashMap<>();
		
		for(Task task:tasks) {
			String description = task.getDescription();
	        String assignee = task.getAssignee();

	        if (!taskdata.containsKey(description)) {
	            taskdata.put(description, new HashSet<>());
	        }

	        taskdata.get(description).add(assignee);
		}
	
	        taskdata.forEach((descriptions, assignees) -> {
	            if (assignees.size() > 1) {
	                System.out.println("Task: " + descriptions + " assigned to " + assignees);
	            }
	        });

		System.out.println(taskdata);
}

	public static void main(String[] args) {
		TaskData taskdata=new TaskData();
		
		
		System.out.println("All Tasks");
		Set<Task> tasks=taskdata.getTasks("all");
		List<Task> tasklist= new ArrayList<>(tasks);
		Collections.sort(tasklist);
		for(Task task : tasklist) {
			System.out.println(task);
		}
		
      for (Task task : taskdata.getTasks("Ann")) {
    	  if(task.getProject()=="ProjectC") {
    		  task.setProject("ProjectX");
    	  }
      }
      
		
		System.out.println("\nTasks for Ann:");
		Set<Task> anntasks=taskdata.getTasks("Ann");
		List<Task> anntasklist= new ArrayList<>(anntasks);
		Collections.sort(anntasklist);
		for(Task task : anntasklist) {
			System.out.println(task);
		}
		
		System.out.println();

		getMultiTaskAssigned(taskdata.getTasks("all"),"ProjectD");
		
		System.out.println();

		taskinfo(taskdata.getTasks("all"));
		
	}

}

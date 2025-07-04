package dev.tulasidhar.july2.setOperationTaskClassChallenge;

import java.util.*;

public class TaskData {
    private Set<Task> tasks;
    
    public TaskData() {
        this.tasks = new TreeSet<>();
        initializeTestData();
    }
    
    private void initializeTestData() {
        // tasks for manager
        tasks.add(new Task("Manager", "Project Alpha", "Review specifications", "assigned", "High"));
        tasks.add(new Task("Manager", "Project Beta", "Plan timeline", "in progress", "Medium"));
        
        // tasks for Ann
        tasks.add(new Task("Ann", "Project Alpha", "Design UI mockups", "assigned", "High"));
        tasks.add(new Task("Ann", "Project Gamma", "Research user requirements", "not yet assigned", "Low"));
        
        // tasks for bob
        tasks.add(new Task("Bob", "Project Alpha", "Implement backend API", "assigned", "High"));
        tasks.add(new Task("Bob", "Project Beta", "Database setup", "assigned", "Medium"));
        
        // tasks for Carol 
        tasks.add(new Task("Carol", "Project Beta", "Frontend development", "in progress", "High"));
        tasks.add(new Task("Carol", "Project Gamma", "Testing framework", "in progress", "Medium"));
    }
    
    
    public Set<Task> getTasks(String employeeName) {
        if(employeeName == "all") {
        	return tasks;
        }
    	var ite = tasks.iterator();
      
        Set<Task> taskByName = new TreeSet<Task>();
        while(ite.hasNext()) {
        	Task temp = ite.next();
        	if(temp.getAssignee().equalsIgnoreCase(employeeName)) {
        		taskByName.add(temp);
        	};
        }
        
        return taskByName;
    }
    
   
    
}
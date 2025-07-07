package dev.tulasidhar.july3.setOperationsExtended;

import java.util.*;

public class TaskData {
    
    private Set<Task> dasuTasks;
    private Set<Task> zoroTasks;
    private Set<Task> gutsTasks;
    private Set<Task> managerTasks;
    public TaskData() {
        
        this.dasuTasks = new TreeSet<>();
        this.zoroTasks = new TreeSet<>();
        this.gutsTasks = new TreeSet<>();
        this.managerTasks = new TreeSet<>();
        initializeTestData();
    }
    
    private void initializeTestData() {
        
        // tasks for Dasu
        dasuTasks.add(new Task("Dasu", "Project Alpha", "Design UI mockups", "ASSIGNED", "HIGH"));
        dasuTasks.add(new Task("Dasu", "Project Gamma", "Research user requirements", "IN_QUEUE", "LOW"));
        
        // tasks for Zoro
        zoroTasks.add(new Task("Zoro", "Project Alpha", "Design UI mockups", "ASSIGNED", "HIGH"));
        zoroTasks.add(new Task("Zoro", "Project Alpha", "Implement backend API", "assigned", "HIGH"));
        zoroTasks.add(new Task("Zoro", "Project Beta", "Database setup", "ASSIGNED", "MEDIUM"));
        
        // tasks for Guts 
        gutsTasks.add(new Task("Guts", "Project Beta", "Frontend development", "IN_PROGRESS", "HIGH"));
        gutsTasks.add(new Task("Guts", "Project Gamma", "Testing framework", "IN_PROGRESS", "MEDIUM"));
    
        //projects given by manager , some were already assigned
        managerTasks.add(new Task("Project Alpha", "Review specifications", "IN_QUEUE", "HIGH"));
        managerTasks.add(new Task("Project Charlie", "Design Framework", "IN_QUEUE", "HIGH"));
        managerTasks.add(new Task("Guts","Project Beta", "Design Framework", "IN_QUEUE", "HIGH"));
    }
    
    
    public Set<Task> getTasks(String employeeName) {
//    	System.out.println("Checking for "+employeeName);
    	
    	Set<Task> allTasks = getUnion(List.of(managerTasks,dasuTasks,zoroTasks,gutsTasks));
        if(employeeName == "all") {
        	return allTasks;
        }
    	var ite = allTasks.iterator();
      
        Set<Task> taskByName = new TreeSet<Task>();
        
        while(ite.hasNext()) {
        	Task temp = ite.next();
//        	System.out.println("Asignee "+temp.getAssignee());
        	if(temp.getAssignee().equalsIgnoreCase(employeeName)) {
        		taskByName.add(temp);
        	};
        }
        
        return taskByName;
    }
    
    public Set<Task> getTasksAssignedToAtleastOne(){
    	Set<Task> allTasks = getUnion(List.of(managerTasks,dasuTasks,zoroTasks,gutsTasks));
    	Set<Task> result = new TreeSet<Task>();
    	var ite = allTasks.iterator();
    	
    	while(ite.hasNext()) {
    		Task current = ite.next();
    		if(current.getAssignee() != "") {
    			result.add(current);
    		}
    	}
    	return result;
    }    
    public Set<Task> getTasksAssignedToNone(){
    	Set<Task> result = new TreeSet<Task>();
    	var ite = managerTasks.iterator();
    	
    	while(ite.hasNext()) {
    		Task current = ite.next();
    		if(current.getAssignee() == "") {
    			result.add(current);
    		}
    	}
    	return result;
    }
    
    public Set<Task> getTasksAssignedToMultiple(){
    	Set<Task> result = new TreeSet<Task>();
    	Set<Task> temp1 = getIntersection(dasuTasks,zoroTasks);
    	Set<Task> temp2 = getIntersection(dasuTasks,gutsTasks);
    	Set<Task> temp3 = getIntersection(gutsTasks,zoroTasks);
    	
    	result.addAll(temp1);
    	result.addAll(temp2);
    	result.addAll(temp3);

    	
    	
    	System.out.println(result.isEmpty());
    	return result;
    }

    
    public static Set<Task> getUnion(List<Set<Task>> listOfSets){
    	Set<Task> resultSet = new TreeSet<Task>();
    	
    	for(Set<Task> set : listOfSets) {
    		resultSet.addAll(set);
    	}
    	return resultSet;
    }
    
    public static Set<Task> getIntersection(Set<Task> set1 , Set<Task> set2){
    	Set<Task> temp = new TreeSet<Task>();
    	temp.addAll(set1);
    	
    	temp.retainAll(set2);
    	return temp;
    }
    
   
    
}
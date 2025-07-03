package SetOperations;

import java.util.Set;
import java.util.HashSet;


public class TaskData {
	
	private Set<Task> tasks;
	
	public TaskData() {		
		tasks =new  HashSet<>();
		
//		tasks.add(new Task("manager", "ProjectA", "Plan meeting", "not yet assigned", "Medium"));
        tasks.add(new Task("Ann", "ProjectB", "Design UI", "in progress", Priority.HIGH));
        tasks.add(new Task("Ann", "ProjectA", "Design UI", "in progress", Priority.MED));
        tasks.add(new Task("Ann", "ProjectD", "Design UI", "in progress", Priority.HIGH));
        tasks.add(new Task("Ann", "ProjectC", "Design UI", "in progress", Priority.LOW));

        tasks.add(new Task("Bob", "ProjectC", "Write code", "assigned", Priority.LOW));
        tasks.add(new Task("Bob", "ProjectD", "Design UI", "assigned", Priority.MED));

        
        tasks.add(new Task("Carol", "ProjectD", "Test app", "in progress", Priority.HIGH));
        tasks.add(new Task("Carol", "ProjectD", "Design UI", "in progress", Priority.MED));

	}
	
	public Set<Task> getTasks(String employee){
		Set<Task> result = new HashSet<>();
		
		if(employee==null || employee.equals("all")) {
			result.addAll(tasks);
		}else {
			for(Task task: tasks) {
				if(task.getAssignee().equalsIgnoreCase(employee)) {
					result.add(task);
				}
			}
		}
		return result;
	}

}

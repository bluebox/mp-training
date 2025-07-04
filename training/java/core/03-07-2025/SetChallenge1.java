import java.util.*;
import java.util.Arrays;
import java.util.Objects;

enum Status1
{
	IN_QUEUE,ASSIGNED,IN_PROGRESS
}
enum Priority
{
	HIGH,MID,LOW
}
class Task 
{
  String project;
  String description;
  String asignee;
  Priority priority;
  Status status;
  
  
  
public Task(String project, String description, String asignee, Priority priority, Status status) {
	this.project = project;
	this.description = description;
	this.asignee = asignee;
	this.priority = priority;
	this.status = status;
}
public String getProject() {
	return project;
}
public String getDescription() {
	return description;
}
public String getAsignee() {
	return asignee;
}
public Priority getPriority() {
	return priority;
}
public Status getStatus() {
	return status;
}

public boolean equals(Object o)
{
	if(this==o)
		return true;
	if(!(o instanceof Task))
		return false;
	Task task=(Task) o;
	return Objects.equals(project, task.project) &&(Objects.equals(description, task.description));
}

public int hashCode()
{
	return Objects.hash(project,description);
}

@Override
public String toString() {
	return "Task [project=" + project + ", description=" + description + ", asignee=" + asignee + ", priority="
			+ priority + ", status=" + status + "]";
} 
}

class TaskData
{
	static Set<Task> getTasks(String name)
	{
		List<Task> ann=Arrays.asList(
				new Task("Project1","Identify errors","ann",Priority.HIGH,Status.IN_PROGRESS),
				new Task("project2","Deployement","ann",Priority.MID,Status.NEW)
				);
		
		List<Task> bob=Arrays.asList(
				new Task("Project1","Debug","bob",Priority.HIGH,Status.IN_PROGRESS),
				new Task("project2","Deployement","bob",Priority.MID,Status.NEW)
				);
		
		List<Task> carol=Arrays.asList(
				new Task("Project1","Identify errors","carol",Priority.HIGH,Status.IN_PROGRESS),
				new Task("project2","Deployement","carol",Priority.MID,Status.NEW)
				);
		
		 Set<Task> result=new HashSet<>();
		
		if(name.equalsIgnoreCase("ann"))
			result.addAll(ann);
		else if(name.equalsIgnoreCase("bob"))
			result.addAll(bob);
		else if(name.equalsIgnoreCase("carol"))
			result.addAll(carol);
		
		return result;
		
		
		
	};
	
	
	
}


public class SetChallenge1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Task> anntasks=TaskData.getTasks("ann");
		Set<Task> bobtasks=TaskData.getTasks("bob");
		System.out.println("Anns tasks are:");
		for(Task tasks:anntasks)
		{
			System.out.println(tasks);
		}
		
		System.out.println("Bobs tasks are:");
		for(Task tasks:bobtasks)
		{
			System.out.println(tasks);
		}
	}

}

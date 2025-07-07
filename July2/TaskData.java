package July2;

import java.util.Set;
import java.util.HashSet;

public class TaskData {

	public static void main(String[] args) {
		
		Set<Task> data = new HashSet<>();
		data.add(new Task("Ann", "Infrastructure", "Security", "High", "In Progress"));
		data.add(new Task("Ann", "Infrastructure", "Password Policy", "Medium", "In Progress"));
		data.add(new Task("Ann", "Research", "Cloud Solutions", "Medium", "In Progress"));
		data.add(new Task("Ann", "Data Design", "Encryption Policy", "High", "Assigned"));
		data.add(new Task("Ann", "Data Design", "Project Table", "Medium", "Assigned"));
		data.add(new Task("Ann", "Data Access", "Write Views", "Low", "In Progress"));
		data.add(new Task("Bob", "Infrastructure", "Security", "High", "In Progress"));
		data.add(new Task("Bob", "Infrastructure", "Password Policy", "Medium", "Assigned"));
		data.add(new Task("Bob", "Data Design", "Encryption Policy", "High", "Assigned"));
		data.add(new Task("Bob", "Data Access", "Write Views", "Low", "In Progress"));
		data.add(new Task("Carol", "Infrastructure", "Logging", "High", "In Progress"));
		data.add(new Task("Carol", "Infrastructure", "DB Access", "Medium", "Assigned"));
		data.add(new Task("Carol", "Infrastructure", "Password Policy", "Medium", "Assigned"));
		data.add(new Task("Carol", "Data Design", "Task Table", "High", "Assigned"));
		data.add(new Task("Carol", "Data Access", "Write Views", "Low", "Assigned"));
		
		//System.out.println(getTasks(data,"Ann"));
		Set<Task> result = getTasks(data, "all");
		result.forEach(System.out::println);
	}
	
	public static Set<Task> getTasks (Set<Task> data, String person){
		Set<Task> out = new HashSet<>();
		if(person.equalsIgnoreCase("all")) {
			return data;
		}
		for(Task temp: data) {
			if(temp.getAssignee().equalsIgnoreCase(person)) {
				out.add(temp);
			}
		}
		return out;
	}
}


import java.util.Set;
import java.util.HashSet;
public class TaskData {
	public static void main(String[] args) {
		Set<Task> data = new HashSet<>();
		data.add(new Task("Ann", "Research", "Cloud Solutions", "Medium", "In Progress"));
		data.add(new Task("Ann", "Data Access", "Write Views", "Low", "In Progress"));
		data.add(new Task("Bob", "Infrastructure", "Security", "High", "In Progress"));
		data.add(new Task("Bob", "Data Access", "Write Views", "Low", "In Progress"));
		data.add(new Task("Carol", "Infrastructure", "Logging", "High", "In Progress"));
		Set<Task> result = getTasks(data, "all");
		//result.forEach(System.out::println);
		for (Task task : result) {
		    System.out.println(task);
		}

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

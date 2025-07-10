package July3;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class TaskData {

	private Set<Task> data = new HashSet<>();

	public void add(Task task) {
		this.data.add(task);
	}

	/*
	 * private static String allTask = """ Infrastructure Logging High
	 * Infrastructure DB Access Medium Infrastructure Security High Infrastructure
	 * Password Policy Medium Data Design Task Table Medium Data Design Employee
	 * Table Medium Data Design Cross Reference Tables High Data Design Encryption
	 * Policy High Data Access Write Views Low Data Access Set Up Users Low Data
	 * Access Set Up Access Policy Low """;
	 * 
	 * private static String annTask = """ Infrastructure Security High In Progress
	 * Infrastructure Password Policy Medium In Progress Research Cloud solutions
	 * Medium In Progress Data Design Encryption Policy High Data Design Project
	 * Table Medium Data Access Write Views Low In Progress """;
	 * 
	 * private static String bobTask = """ Infrastructure Security High In Progress
	 * Infrastructure Password Policy Medium Data Design Encryption Policy High Data
	 * Access Write Views Low In Progress """;
	 * 
	 * private static String carolTask = """ Infrastructure Logging High In Progress
	 * Infrastructure DB Access Medium Infrastructure Password Policy Medium Data
	 * Design Task Table High Data Access Write Views Low """;
	 */

	public Set<Task> getTasks(String person) {
		Set<Task> out = new HashSet<>();

		if (person.equalsIgnoreCase("all")) {
			// return data;
			List<Task> allTasks = new ArrayList<>(this.data);
			// allTasks.sort(Comparator.comparing(Task::getProject).thenComparing(Task::getDescription).thenComparing(Task::getAssignee));
			return new HashSet<>(allTasks);
		}
		for (Task temp : data) {
			if (temp.getAssignee() != null) {
				if (temp.getAssignee().equalsIgnoreCase(person)) {
					out.add(temp);
				}
			}
		}
		return out;
	}

	public Set<Task> getUnion(Set<Task> sets) {
		Set<Task> result = new HashSet<>();
			result.addAll(sets);
		
		return result;
	}

	public Set<Task> getIntersect(Set<Task> set1, Set<Task> set2) {
		Set<Task> result = new HashSet<>(set1);
		result.retainAll(set2);
		return result;
	}

	public Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
		Set<Task> result = new HashSet<>(set1);
		result.removeAll(set2);
		return result;
	}

	@Override
	public String toString() {
		return "TaskData [data=" + data + "]";
	}

}

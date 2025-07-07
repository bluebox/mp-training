package day7;

import java.util.Set;
import java.util.TreeSet;

public class TaskMain {

	public static void main(String[] args) {
		String allString="Infrastructure, Logging, High\n"
				+ "Infrastructure, DB Access, Medium\n"
				+ "Infrastructure, Security, High\n"
				+ "Infrastructure, Password Policy, Medium\n"
				+ "Data Design, Task Table, Medium\n"
				+ "Data Design, Employee Table, Medium\n"
				+ "Data Design, Cross Reference Tables, High\n"
				+ "Data Design, Encryption Policy, High\n"
				+ "Data Access, Write Views, Low\n"
				+ "Data Access, Set Up Users, Low\n"
				+ "Data Access, Set Up Access Policy, Low";
		
		String annString="Infrastructure, Security, High, In Progress\n"
				+ "Infrastructure, Password Policy,Medium, In Progress\n"
				+ "Research, Cloud solutions, Medium, In Progress\n"
				+ "Data Design, Encryption Policy, High\n"
				+ "Data Design, Project Table, Medium\n"
				+ "Data Access, Write Views,Low, In Progress";
		
		String bobString="Infrastructure, Security, High, In Progress\n"
				+ "Infrastructure, Password Policy, Medium\n"
				+ "Data Design,Encryption Policy,High\n"
				+ "Data Access,Write Views, Low, In Progress";
		
		String carolString="Infrastructure, Logging, High, In Progress\n"
				+ "Infrastructure, DB Access, Medium\n"
				+ "Infrastructure, Password Policy, Medium\n"
				+ "Data Design, Task Table, High\n"
				+ "Data Access, Write Views, Low";
		
		TaskData taskData = new TaskData(allString, annString, bobString, carolString);

		Set<Task> allTasks = taskData.getTasks("all");
		System.out.println("----- all tasks -------");
		printTasks(allTasks);

		Set<Task> annsTasks = taskData.getTasks("ann");
		System.out.println("----- ann tasks -------");
		printTasks(annsTasks);

		Set<Task> bobsTasks = taskData.getTasks("bob");
		System.out.println("----- bob tasks -------");
		printTasks(bobsTasks);

		Set<Task> carolsTasks = taskData.getTasks("carol");
		System.out.println("----- carol tasks -------");
		printTasks(carolsTasks);
		
		Set<Task> assignedTasks= getUnion(annsTasks,bobsTasks,carolsTasks);
		System.out.println("----- assigned tasks -------");
		printTasks(assignedTasks);
		
		Set<Task> unAssignedTasks= getDifference(allTasks,assignedTasks);
		System.out.println("----- unassigned tasks -------");
		printTasks(unAssignedTasks);
		
		Set<Task> commonTasks =getIntersection(bobsTasks,carolsTasks);
		System.out.println("----- common tasks of bob and carol -------");
		printTasks(commonTasks);
	}
	
	private static void printTasks(Set<Task> tasks) {
		tasks.forEach(System.out::println);
		System.out.println();
	}

	private static Set<Task> getUnion(Set<Task>... sets) {
		Set<Task> union = new TreeSet<>();
		for (Set<Task> set : sets) {
			union.addAll(set);
		}
		return union;
	}

	private static Set<Task> getIntersection(Set<Task> set1, Set<Task> set2) {
		Set<Task> intersection = new TreeSet<>(set1);
		intersection.retainAll(set2);
		return intersection;
	}

	private static Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
		Set<Task> difference = new TreeSet<>(set1);
		difference.removeAll(set2);
		return difference;
	}
}

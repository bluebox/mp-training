package corejava.july3_setoperations;

import java.util.HashSet;
import java.util.Set;

public class TaskData {
	private static final Set<Task> allTasks =new HashSet<>();
	private static final Set<Task> annTasks=new HashSet<>();
	private static final Set<Task> bobTasks =new HashSet<>();
	private static final Set<Task> carolTasks =new HashSet<>();
	
	static {
		allTasks.add(new Task("Infrastructure","Logging",null,Priority.HIGH,Status.IN_QUEUE));
		allTasks.add(new Task("Infrastructure","DB Access",null,Priority.MED,Status.IN_QUEUE));
		allTasks.add(new Task("Infrastructure","Security",null,Priority.HIGH,Status.IN_QUEUE));
		allTasks.add(new Task("Infrastructure","Password Policy",null,Priority.MED,Status.IN_QUEUE));
		allTasks.add(new Task("Data Design","Task Tabel",null,Priority.MED,Status.IN_QUEUE));
		allTasks.add(new Task("Data Design","Employee Table",null,Priority.MED,Status.IN_QUEUE));
		allTasks.add(new Task("Data Design","Cross Reference Tables",null,Priority.HIGH,Status.IN_QUEUE));
		allTasks.add(new Task("Data Design","Encryption Policy",null,Priority.HIGH,Status.IN_QUEUE));
		allTasks.add(new Task("Data Access","Write Views",null,Priority.LOW,Status.IN_QUEUE));
		allTasks.add(new Task("Data Access","Set Up Users",null,Priority.LOW,Status.IN_QUEUE));
		allTasks.add(new Task("Data Access","Set Up Access Policy",null,Priority.LOW,Status.IN_QUEUE));
		
		annTasks.add(new Task("Infrastructure","Security",null,Priority.HIGH,Status.IN_PROGRESS));
		annTasks.add(new Task("Infrastructure","Password Policy",null,Priority.MED,Status.IN_PROGRESS));
		annTasks.add(new Task("Research","Cloud Solutions",null,Priority.MED,Status.IN_PROGRESS));
		annTasks.add(new Task("Data Design","Encryption Policy",null,Priority.HIGH,Status.ASSIGNED));
		annTasks.add(new Task("Data Design","Project Table",null,Priority.MED,Status.ASSIGNED));
		annTasks.add(new Task("Data Access","Write views",null,Priority.LOW,Status.IN_PROGRESS));
		
		bobTasks.add(new Task("Infrastructure","Security",null,Priority.HIGH,Status.IN_PROGRESS));
		bobTasks.add(new Task("Infrastructure","Password Policy",null,Priority.MED,Status.ASSIGNED));
		bobTasks.add(new Task("Data Design","Encryption Policy",null,Priority.HIGH,Status.ASSIGNED));
		bobTasks.add(new Task("Data Access","Write views",null,Priority.LOW,Status.IN_PROGRESS));
		
		carolTasks.add(new Task("Infrastructure","Logging",null,Priority.HIGH,Status.IN_PROGRESS));
		carolTasks.add(new Task("Infrastructure","DB Access",null,Priority.MED,Status.ASSIGNED));
		carolTasks.add(new Task("Infrastructure","Password Policy",null,Priority.MED,Status.ASSIGNED));
		carolTasks.add(new Task("Data Design","Task Tabel",null,Priority.HIGH,Status.ASSIGNED));
		carolTasks.add(new Task("Data Access","Write Views",null,Priority.LOW,Status.ASSIGNED));
		
	}
	
	public static Set<Task> getAllTasks() {
        return new HashSet<>(allTasks);
    }

    public static Set<Task> getAnnTasks() {
        return new HashSet<>(annTasks);
    }

    public static Set<Task> getBobTasks() {
        return new HashSet<>(bobTasks);
    }

    public static Set<Task> getCarolTasks() {
        return new HashSet<>(carolTasks);
    }
	
	
	
	
}

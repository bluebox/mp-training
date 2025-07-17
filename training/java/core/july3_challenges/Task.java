package july_3Challenges;
import java.util.Objects;
public class Task {
	String project;
	String description;
	String assignee;
	Priority priority;
	Status status;
	
	public String getproject() {
		return this.project;}
	
	public String getdescription() {
		return this.description;
	}
	
	public String getassignee() {
		return this.assignee;
	}
	
	public Priority getpriority() {
		return this.priority;
	}
	
	public Status getstatus() {
		return this.status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(project,description);
	}
	
	
}

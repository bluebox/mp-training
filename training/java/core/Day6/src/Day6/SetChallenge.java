package Day6;

import java.util.Comparator;
import java.util.Objects;

public class SetChallenge {
	private String assignee;
	private String projectName;
	private String description;
	private Status status;
	private Priority priority;
	
	public enum Status{
		ASSIGNED,NOT_ASSIGNED,IN_PROGRESS
	}
	
	public enum Priority{
		HIGH,LOW,MEDIUM
	}
	
	public SetChallenge(String assignee, String projectName, String description, Status status, Priority priority) {
		this.assignee = assignee;
		this.projectName = projectName;
		this.description = description;
		this.status = status;
		this.priority = priority;
	}
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(projectName);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this==obj;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Assignee : "+assignee+" name of project : "+projectName+" \ndescription : "+description+" and the status : "+status+" priority :  "+priority;
	}
	
	
	
	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}


	public static final Comparator<SetChallenge> checker = new Comparator<SetChallenge>() {
        @Override
        public int compare(SetChallenge o1, SetChallenge o2) {
            int projectCheck = o1.getProjectName().compareTo(o2.getProjectName());
            if (projectCheck == 0) {
                return o1.getDescription().compareTo(o2.getDescription());
            }
            return projectCheck;
        }
    };


	

	
	
}

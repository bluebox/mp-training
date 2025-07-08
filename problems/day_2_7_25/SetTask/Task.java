package day_2_7_25.SetTask;

import java.util.Set;


public class Task implements Comparable<Task>{
	
	@Override
	public String toString() {
		return "Task [assignee=" + assignee + ", Project=" + Project + ", description=" + description + ", status="
				+ status + ", priority=" + priority + "]";
	}




	public enum Status{
		assigned,
		inprogress,
		notyetassigned
	}
	
	public enum Priority{
		high,
		medium,
		low
	}
	
    public String assignee;
    public String Project;
    public String description;
    public  Status status;
    public  Priority priority;
  public Task(String assignee, String project, String description, Status status, Priority priority) {
	this.assignee = assignee;
	this.Project = project;
	this.description = description;
	this.status = status;
	this.priority = priority;
}
  

	@Override
     public int compareTo(Task o) {
	// TODO Auto-generated method stub
    	 if(this.Project.compareTo(o.Project)==0) {
    		 System.out.println("I was called");
    		 if(this.description.compareTo(o.description)>=0) {
    			 System.out.println("I was called");
    			 return -1;
    		 }else {
    			 System.out.println("I was called");
    			 return 1;
    		 }
    	 }else {
    		 return this.Project.compareTo(o.Project);
    	 }
	  }
	
	
	
}

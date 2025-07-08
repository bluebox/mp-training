
import java.util.HashMap;
import java.util.HashSet;
enum Priority{
    HIGH,LOW,MEDIUM
}
enum Status{
    IN_PROGRESSS,
    COMPLETED
}
public class Task implements Comparable<Task> {
    
    


    private  String assignee;
    private String project;
    private String description;
    private Priority priority;
    private Status status;
    private HashSet<Task> set=new HashSet<>();
    private HashSet<String> innerset=new HashSet<>();
    public Task(String project, String description, Priority priority,Status status) {
        this.project = project;
        this.description = description;
        this.priority = priority;
        this.status=status;
    }

    public String getAssignee() {
        return assignee;
    }
    public String getDescription() {
        return description;
    }
    
    public String getProject() {
        return project;
    }
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setProject(String project) {
        this.project = project;
    }
 
    
   
    @Override
    public String toString() {
        return "Task [project=" + project + ", description=" + description + ", priority=" + priority + ", status="
                + status + "]";
    }

    @Override
    public int compareTo(Task o) {
        if(this.project.compareTo(o.project)==1){
            return 1;

        }
        else if(this.project.compareTo(o.project)==-1)return -1;
        else{
            if(this.description.compareTo(o.description)==1){
                return 1;

            }
            else if(this.description.compareTo(o.description)==-1)return -1;
            else return 0;
        }
    }

}


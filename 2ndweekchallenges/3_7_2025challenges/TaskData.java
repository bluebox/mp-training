import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TaskData {

	
    private Set<Task> annTasks=new HashSet<>();
    private Set<Task> bobstasks=new HashSet<>();
    private Set<Task> carolTasks=new HashSet<>();
    private Set<Task> alltasks=new HashSet<>();
    public Set<Task> getTasks(String owner) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C://Users//Santhosh//Desktop//internship//3_7_2025challenges//SetsOperationsData.csv"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            while(!line.equals("Ann's Tasks")){
            System.out.println(line);
            if(line.equals("All Tasks")){
                continue;
            }
           String[] linearr=line.split(",");
           System.out.println(Arrays.toString(linearr));

           alltasks.add(new Task(linearr[0],linearr[1],Priority.valueOf(linearr[3]),Status.IN_PROGRESSS));}
            while ((line = reader.readLine()) != "Bob's Tasks") {
           String[] linearr=line.split(",");

           annTasks.add(new Task(linearr[0],linearr[1],Priority.valueOf(linearr[3]),Status.valueOf(linearr[4])));
           
        }
        while ((line = reader.readLine()) != "Carol's Tasks") {
           String[] linearr=line.split(",");

           bobstasks.add(new Task(linearr[0],linearr[1],Priority.valueOf(linearr[3]),Status.valueOf(linearr[4])));
           
        } 
         while ((line = reader.readLine()) != null) {
           String[] linearr=line.split(",");

           carolTasks.add(new Task(linearr[0],linearr[1],Priority.valueOf(linearr[3]),Status.valueOf(linearr[4])));
           
        }
           
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    
        Set<Task> set=new HashSet<>();
         Set<Task> result = new HashSet<>();
        System.out.println(annTasks);
       if (owner.equals("Ann")){
        return annTasks;




        
       }  
       if(owner.equals("Bob"))return bobstasks;
       if(owner.equals("Carol")) return carolTasks;
       return alltasks;
    
    }
    public Set<Task> getAlltasks() {
        return alltasks;
    }public Set<Task> getAnnTasks() {
        return annTasks;
    }public Set<Task> getBobstasks() {
        return bobstasks;
    }public Set<Task> getCarolTasks() {
        return carolTasks;
    }

}

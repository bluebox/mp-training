package july_3Challenges;



import java.util.*;

public class MainTaskData {

    public static void main(String[] args) {
        Set<String> master = getMasterTasks();
        Set<String> ann = getAnnTasks();
        Set<String> bob = getBobTasks();
        Set<String> carol = getCarolTasks();

 
        List<Set<String>> allEmployeeTasks = Arrays.asList(ann, bob, carol);

    
        Set<String> unionTasks = TaskUtils.getUnion(allEmployeeTasks);
        System.out.println("Union of Ann, Bob, and Carol's Tasks:");
        System.out.println(unionTasks);

     
        Set<String> commonTasks = TaskUtils.getIntersect(
            TaskUtils.getIntersect(ann, bob), carol
        );
        System.out.println("\nCommon Tasks to All:");
        System.out.println(commonTasks);

    
        Set<String> unassignedTasks = TaskUtils.getDifference(master, unionTasks);
        System.out.println("\nTasks in Master List but Not Assigned:");
        System.out.println(unassignedTasks);
    }

    public static Set<String> getMasterTasks() {
        return new HashSet<>(Arrays.asList(
            "Logging", "DB Access", "Security", "Password Policy",
            "Task Table", "Employee Table", "Cross Reference", "Encryption Polic",
            "Write Views", "Set Up Users", "Set Up Access F"
        ));
    }

    public static Set<String> getAnnTasks() {
        return new HashSet<>(Arrays.asList(
            "Security", "Password Policy", "Cloud solutions",
            "Encryption Polic", "Project Table", "Write Views"
        ));
    }

    public static Set<String> getBobTasks() {
        return new HashSet<>(Arrays.asList(
            "Security", "Password Policy", "Encryption Polic", "Write Views"
        ));
    }

    public static Set<String> getCarolTasks() {
        return new HashSet<>(Arrays.asList(
            "Logging", "DB Access", "Password Policy", "Task Table", "Write Views"
        ));
    }
}

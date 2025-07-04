import java.util.EnumMap;

enum Status
{
	NEW,IN_PROGRESS,COMPLETED;
}
public class EnumMap1 {

	public static void main(String[] args) {
		
		EnumMap<Status,String> list=new EnumMap<>(Status.class);
		list.put(Status.NEW,"Please complete this new task");
		list.put(Status.IN_PROGRESS,"This task is under progress");
		list.put(Status.COMPLETED,"This task was completed");
		
		System.out.println("Tasks are:"+list);
	
		

	}

}

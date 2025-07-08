package day7;

import java.util.HashSet;
import java.util.Set;

import day7.SetChallenge.Priority;
import day7.SetChallenge.Status;

public class TaskData {
	private static String AllTasks="""
			Infrastructure	 ,Logging	,High
			Infrastructure	 ,DB Access	 ,Medium
			Infrastructure	 ,Security	 ,High
			Infrastructure	, Password Policy	 ,Medium
			Data Design	 ,Task Table	 ,Medium
			Data Design	 ,Employee Table	, Medium
			Data Design	 ,Cross Reference Tables	 ,High
			Data Design	 ,Encryption Policy	 ,High
			Data Access	 ,Write Views	, Low
			Data Access	 ,Set Up Users	 ,Low
			Data Access	, Set Up Access Policy	 ,Low

			""";
	private static String AnnTasks="""
			Infrastructure	 ,Security	 ,High,	 In Progress
			Infrastructure	 ,Password Policy,	Medium	 ,In Progress
			Research	 ,Cloud solutions	, Medium	 ,In Progress
			Data Design	 ,Encryption Policy,	 High	
			Data Design	 ,Project Table	 ,Medium	
			Data Access	 ,Write Views	,Low	 ,In Progress
			""";
	private static String BobTasks="""
			Infrastructure	 ,Security	 ,High	 ,In Progress
			Infrastructure	 ,Password Policy	 ,Medium	
			Data Design,	Encryption Policy	,High	
			Data Access	,Write Views	 ,Low	 ,In Progress
			""";
	private static String CarolTasks="""
			Infrastructure	 ,Logging	 ,High	 ,In Progress
			Infrastructure	 ,DB Access	 ,Medium	
			Infrastructure	 ,Password Policy	 ,Medium	
			Data Design	 ,Task Table	 ,High	
			Data Access	 ,Write Views	 ,Low	
			""";
	

	public static Set<SetChallenge> getData(String assignee){
		String taskName="";
		Set<SetChallenge> tasks=new HashSet<>();
		switch(assignee.toLowerCase()) {
		case "ann":
			taskName=AnnTasks;
			break;
		case "bob":
			taskName=BobTasks;
			break;
		case "carol":
			taskName=CarolTasks;
			break;
		case "alltasks":
			taskName=AllTasks;
			break;
		default:
			System.out.println("invalid assignee");
		}
		
		String[] lines=taskName.trim().split("\n");
		for(String line : lines) {
			String[] parts=line.split("\\s*,\\s*");
			if(parts.length==3) {
				tasks.add(new SetChallenge( parts[0], parts[1],Status.NOT_ASSIGNED ,Priority.valueOf(parts[2].toUpperCase().trim().replace(" ","_"))));
			}
			else if(parts.length==4) {
				tasks.add(new SetChallenge(parts[0],parts[1],Status.valueOf(parts[3].toUpperCase().replace(" ", "_")),Priority.valueOf(parts[2].toUpperCase().replace(" ","_"))));
			}
			 
			
		}
		return tasks;
	}
}

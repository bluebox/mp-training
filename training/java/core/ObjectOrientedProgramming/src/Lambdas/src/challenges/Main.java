package challenges;

public class Main {

	public static void main(String[] args) {
		
		
		TaskData td = new TaskData();
		td.display();
		td.createEmployee("1d", "madhav", 21, "Software Eng", "87456672367", "mail");
		td.display();
		td.close();

	}

}

package recordexample;

public class PojoExample {

	public static void main(String[] args) {
		//pojo class for Student details
		
		StudentClass student = new StudentClass("Madhav","12345","IT",21,4);
		System.out.println(student);
		
		//record for Student details
		//Record dosen't require getters and setters
		StudentRecord record = new StudentRecord("Madhav","12345","IT",21,4);
		System.out.println(record);
		

	}

}

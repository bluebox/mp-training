package StudentStream;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum Gender {
	MALE, FEMALE
}

enum Courses{
	Math, Science, History, English
}

public class Student {
	private String Sid;
	private int Sage;
	private Gender Sgender;
	private List<Courses> SCourse;
	private static Random random = new Random();
	
	
	public Student(String sid,int sage, Gender sgender, List<Courses> course) {
		Sid=sid;
		Sage = sage;
		Sgender = sgender;
		SCourse= new ArrayList<>(course);
	}


	public int getSage() {
		return Sage;
	}


	public Gender getSgender() {
		return Sgender;
	}


	public void setSage(int sage) {
		Sage = sage;
	}


	public void setSgender(Gender sgender) {
		Sgender = sgender;
	}	
	
	public List<Courses> getSCourse() {
		return SCourse;
	}


	public void setSCourse(Courses course) {
		SCourse.add(course);
	}

	

	public String getSid() {
		return Sid;
	}


	public void setSid(String sid) {
		Sid = sid;
	}


	public static Student getRandomStudent() {
		String sid = "S00"+random.nextInt(10001);
		int sage= random.nextInt(31)+5;
		
		Gender sgender= Gender.values()[random.nextInt(2)];
		
		List<Courses> course =new ArrayList<>();
		for(int i=0;i<random.nextInt(2);i++) {
			course.add(Courses.values()[random.nextInt(Courses.values().length)]);
		}
		
		
		return new Student(sid,sage,sgender,course);

	}


}

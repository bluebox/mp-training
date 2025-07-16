package corejava.july4_studentsandcourses;
public class Course {
	public String Coursename;
	public int lecturecount;
	
	public Course(String coursename, int lecturecount) {
		
		this.Coursename = coursename;
		this.lecturecount = lecturecount;
	}
	
	public Course(String coursename) {
		this.Coursename=coursename;
	}
	
	public double getpercentcomplete(Course Coursename){
          return(double) Coursename.lecturecount/100*100;
    }
	

}

package TerminaloperationCh;

//package TerminaloperationCh;

public class Course {
	public String Coursename;
	public int lcount;
	public Course(String coursename, int lcount) {
		
		this.Coursename = coursename;
		this.lcount = lcount;
	}
	public Course(String coursename) {
		this.Coursename=coursename;
	}
	public double getpercentcomplete(Course Coursename){
          return(double) Coursename.lcount/100*100;
       }
	

}

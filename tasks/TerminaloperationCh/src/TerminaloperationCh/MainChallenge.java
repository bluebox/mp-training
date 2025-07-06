//package TerminaloperationCh;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class MainChallenge {
	 Course jmc=new Course("jmc",100);
     Course pymc=new Course("pymc",50);
     Course additionaCourse=new Course("creating games in java");
	 Map<String, Integer> lecturecount = new HashMap<>();
	 String additional_course = "";
	 

	 

         List<Object> students = Stream.generate(()->Student.getRandomStudent(jmc,pymc,additionaCourse))
        		 .limit(5000)
        		 .collect((Collectors.toList()));
         OptionalDouble AveragePercentage=students.stream()
        		 .mapToDouble(s->getpercentcomplete("jmc"))
        		 .average();
         Double Averagepercent=AveragePercentage*1.25;
         List<Student> selectedStudents=students.stream()
        		 .filter(s->getpercentcomplete(jmc))
        		 .sorted(Comparator.comparingInt(student::getMinlectureAttended()).reversed())
        		 .limit(10)
        		 .collect((Collectors.toList()));
         Course newone=new Course("new course")
         selectedStudents.forEach(s->s.addCourse(newone));
         selectedStudents.forEach(System.out::println);
         
         
        		 
         
        
             public int getLectureCount(String course) {
                 return lecturecount.getOrDefault(course, 0);
             }
             private Object getpercentcomplete(Course jmc2) {
				return null;
			}
			 public String getadditionalcourse(){
                return additional_course;
             }
             

            
             public static void main(String[] args) {

                 
             }}
        }

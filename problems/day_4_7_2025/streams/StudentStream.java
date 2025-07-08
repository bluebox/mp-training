package day_4_7_2025.streams;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.*;

public class StudentStream {
      String name;
      String Firstname;
      String LastName;
      Long ID;
      int rollno;
      Map<String,String> course;
      String City;
      String State;
      String Country;
      String FatherName;
      String MotherName;
      String Standard;
      boolean Gender;
      int age;
      
      
  static Random random=new Random();
      
  	public StudentStream(String name, Long iD, String city, String state, String country, String fatherName,
			String motherName, String standard,boolean gender) {
		this.name = name;
		this.Firstname=name.substring(0,name.indexOf(" "));
		this.LastName=name.substring(name.indexOf(" ")+1);
		ID = iD;
		City = city;
		State = state;
		Country = country;
		FatherName = fatherName;
		MotherName = motherName;
		Standard = standard;
		Gender=gender;
		int age=random.nextInt(100);
	}

// Factory Method to generate Student randomly
  	public static void generate(List<StudentStream> students) {
  	  // List<StudentStream> students=new LinkedList<>();
  		for(int i=0;i<10;i++) {
  			int num=random.nextInt(1000000000);
  			String name=""+(char)(random.nextInt(26)+64)+"name"+" "+"last"+(char)(random.nextInt(26)+96);
  			Long Id=(long)num;
  			String [] Country= {"India","China","Nepal","USA","UK"};
  			String [] State= {"India","China","Nepal","USA","UK"};
  			String [] City= {"Hyderabad","NY","London","Katmandu","Beiging"};
  			String [] Father= {"john","ram","Neil","Upen","AKash"};
  			String [] Mother= {"Indiana","sara","Neha","UShA","UKara"};
  			String [] classes= {"preprimary","Secondary","Primary","Graduate","PostGraduate"};
  			students.add(new StudentStream(name,Id,City[random.nextInt(5)],State[random.nextInt(5)],
  					Country[random.nextInt(5)],Father[random.nextInt(5)],Mother[random.nextInt(5)],classes[random.nextInt(5)],
  					(random.nextInt(5)%2==0)?true:false));
  			
  		}
  		
  	//	return students;
  		
  		
  	}
  	
  	public int age() {
		return age;
	}
	
  
	public String toString() {
		return "StudentStream [name=" + name + ", Firstname=" + Firstname + ", LastName=" + LastName + ", Gender="
				+ Gender + ", age=" + age + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<StudentStream> students=new ArrayList<>();
		//var students=Stream.generate(StudentStream::generate); 
		
		generate(students);
		
		var stream=(students).stream();
		
		Stream<StudentStream> females=stream.distinct()
				.filter(i->(i.Gender==true));
		
		int boys=(int)stream.count();
		int girls= students.size()-boys;
		
		females.forEach(StudentStream::toString);
		
		 long countofagebelow_30 = stream
                 .filter(n -> n.age<=30) // Filter elements that satisfy the condition
                 .count();	
		
		
		 long countofageabove_60 = stream
                 .filter(n -> n.age>60) // Filter elements that satisfy the condition
                 .count();	
		 
		 System.out.println("Students of within age 30 :"+countofagebelow_30+"of age between 30 and 60 :"+(students.size()-(int)countofagebelow_30-(int)countofageabove_60)+"of age above 60"+countofageabove_60);
  		
		 var sd =students.stream()
				 .distinct()
				 .mapToInt(i->i.age())
				 .summaryStatistics();
		 
		 
		 var sd_agesum =students.stream()
				 .distinct()
				 .mapToInt(i->i.age())
				 .reduce(0,(a,b)->(a+b));
		 
		 
		 // for printing the country of the students  
		 
		 stream.distinct()
				 .map(i->i.Country)
		         .forEach(System.out::println);
  		
  		// for students enrolled more than 7 years
		 var arematches=stream.allMatch(i->i.age>=15);
		 if(arematches) {
			 stream.distinct()
			 .limit(5)
			 .sorted((i,j)->i.age-j.age)
			 .forEachOrdered(StudentStream::toString);
		 }
  		
	}
      
	
	
	
	
	
	public String getName() {
		return name;
	}










	public void setName(String name) {
		this.name = name;
	}










	public String getFirstname() {
		return Firstname;
	}










	public void setFirstname(String firstname) {
		Firstname = firstname;
	}










	public String getLastName() {
		return LastName;
	}










	public void setLastName(String lastName) {
		LastName = lastName;
	}










	public Long getID() {
		return ID;
	}










	public void setID(Long iD) {
		ID = iD;
	}










	public int getRollno() {
		return rollno;
	}










	public void setRollno(int rollno) {
		this.rollno = rollno;
	}










	public Map<String, String> getCourse() {
		return course;
	}










	public void setCourse(Map<String, String> course) {
		this.course = course;
	}










	public String getCity() {
		return City;
	}










	public void setCity(String city) {
		City = city;
	}










	public String getState() {
		return State;
	}










	public void setState(String state) {
		State = state;
	}










	public String getCountry() {
		return Country;
	}










	public void setCountry(String country) {
		Country = country;
	}










	public String getFatherName() {
		return FatherName;
	}










	public void setFatherName(String fatherName) {
		FatherName = fatherName;
	}










	public String getMotherName() {
		return MotherName;
	}










	public void setMotherName(String motherName) {
		MotherName = motherName;
	}










	public String getStandard() {
		return this.Standard;
	}










	public void setClass(String class1) {
		this.Standard= class1;
	}












}

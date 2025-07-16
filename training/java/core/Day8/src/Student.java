import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Student {
	private String name;
	private String gender;
	private String countryCode;
	private Integer age;
	private Boolean isActive;
	private Integer year;
	private List<String> course= new ArrayList<>();
	private Integer percentage;
	
	static Random r=new Random();
	
	public Student(String name, String gender, String countryCode, Integer age, Boolean isActive, Integer year ,List<String> course,Integer percentage) {
		this.name = name;
		this.gender = gender;
		this.countryCode = countryCode;
		this.age = age;
		this.isActive = isActive;
		this.year = year;
		this.course= new ArrayList<>(course);
		this.percentage=percentage;
	}
	
	public static Student generate() {
		String[] names=new String[] {"Raju","babu","Suri","raji","Navya","shreya","ravi","gopi","jack","sandeep","babulu","teja","phillips"};
		String name=names[r.nextInt(names.length)]+" "+(char)('A'+r.nextInt(15));
		String[] genders=new String[] {"Male","Female"};
		String gender=genders[r.nextInt(genders.length)];
		String[] countrycodes=new String[] {"EU","AS","IN","US","UK","CI"};
		String countrycode=countrycodes[r.nextInt(countrycodes.length)];
		Integer age=18+r.nextInt(55);
		Boolean isactive=r.nextBoolean();
		Integer year=2010+r.nextInt(15);
		List<String> courses=Arrays.asList("Python","Java","C++");
		Collections.shuffle(courses);
		int i=0;
		List<String> course=new ArrayList<>();
		while(i<=r.nextInt(0,courses.size())) {
			course.add(i, courses.get(i));
			i++;
		}
		
		return new Student(name,gender,countrycode,age,isactive,year,course,r.nextInt(30,100));
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		
		return "Name : "+name+" , Gender : "+gender+" , Country code : "+countryCode+
				" , age : "+age+" , active Status : "+isActive+" , year : "+year+" , course : "+course+" percentage completed : "+percentage;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public Integer getAge() {
		return age;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public Integer getYear() {
		return year;
	}

	public List<String> getCourse() {
		return course;
	}
	
	
}

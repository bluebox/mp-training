import java.util.Random;

public class Student {
	private String name;
	private String gender;
	private String countryCode;
	private Integer age;
	private Boolean isActive;
	private Integer year;
	private String course;
	
	static Random r=new Random();
	
	public Student(String name, String gender, String countryCode, Integer age, Boolean isActive, Integer year ,String course) {
		this.name = name;
		this.gender = gender;
		this.countryCode = countryCode;
		this.age = age;
		this.isActive = isActive;
		this.year = year;
		this.course=course;
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
		String[] courses=new String[] {"Python","Java","C++"};
		String course=courses[r.nextInt(courses.length)];
		
		return new Student(name,gender,countrycode,age,isactive,year,course);
	}

	@Override
	public String toString() {
		
		return "Name : "+name+" , Gender : "+gender+" , Country code : "+countryCode+
				" , age : "+age+" , active Status : "+isActive+" , year : "+year+" , course : "+course;
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

	public String getCourse() {
		return course;
	}
	
	
}

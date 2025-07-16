package Practice.july7_streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByAge {

	public static void main(String[] args) {
		Students s1=new Students("Deepika",23);
		Students s2=new Students("Meghana",21);
		Students s3=new Students("PardhaSaradhi",51);
		Students s4=new Students("Sailaja",41);
		Students s5=new Students("Deepu",22);
		Students s6=new Students("NageshwarRao",51);
		Students s7=new Students("Sruthi",22);
		List<Students> students=Arrays.asList(s1,s2,s3,s4,s5,s6,s7);
		Map<Integer,List<Students>> ageGroups=students.stream().collect(Collectors.groupingBy(Students::getAge));
		System.out.println(ageGroups);
	}

}
class Students{
	private String name;
	private int age;
	public Students(String name,int age) {
		this.name=name;
		this.age=age;
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "["+this.getName()+", "+this.getAge()+"]";
	}
}
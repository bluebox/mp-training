package hashset;

import java.util.Objects;
import java.util.Set;

public class HashSet {
	
	
	static class Students{
		private String name;
		private String age;
		
		public Students(String name, String age)
		{
			this.name = name;
			this.age = age;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(name,age);
			
		}
		
		
		@Override
		public boolean equals(Object obj)
		{
			if(this == obj)
			{
				return true;
			}
			if(obj == null|| getClass() != obj.getClass())
			{
				return false;
			}else
			{
				Students s = (Students)obj;
				if((this.age == s.getAge())&&(this.name.equals(s.getName())))
				{
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return "Students [name=" + name + ", age=" + age + "]";
		}
		
	}
	
	static Set<Students>s = new java.util.HashSet<HashSet.Students>();
	
	public static void main(String[] args) {
		s.add(new Students("Madhav", "21"));
		s.add(new Students("ravi","21"));
		s.add(new Students("Madhav","21"));
		for(var student:s)
			System.out.println(student);
	}
	
	

	
}

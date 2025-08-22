 class Student implements Comparable<Student> {
	String name;
	 int rollno;
     int height;
	Student(String name,int rollno,int height)
	{
		this.name=name;
		this.rollno=rollno;
		this.height=height;
	}
	public String toString()
	{
		return name+" "+rollno+" "+height;
	}
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}
}

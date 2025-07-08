public class Generic {
    public static void main(String[] args) {
        Student s=new Student();
    s.setCourse("java");
    s.setName("ramesh");
    QueryList<Student> ql=new QueryList<>();
    ql.list.add(s);
    Student s1=new Student();
    s1.setCourse("python");
    s1.setName("rakesh");
    ql.list.add(s1);
    ql.printlist();
    

    }

}

public class Student implements Queryitem {
    private String name;
    private String course;
    private int id;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public void findstudent(String name){

    }
    public boolean matches(String field){
        if(this.name.equals(field)||this.course.equals(field))return true;
        return false;
    }
}

package Day_1_7_25;

public class Student implements QueryItem {
    private String id;
    private String name;
    private String course;

    public Student(String id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public boolean matches(String field) {
        return name.toLowerCase().contains(field.toLowerCase())
            || course.toLowerCase().contains(field.toLowerCase());
    }

    @Override
    public String toString() {
        return id + "=" + name + " = " + course;
    }
}


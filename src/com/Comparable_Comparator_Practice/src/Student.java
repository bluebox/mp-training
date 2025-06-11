public class Student implements Comparable<Student> {

    private String name;
    private int height;
    private int marks;

    public Student(String name, int height, int marks) {
        this.name = name;
        this.height = height;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int compareTo(Student s) {
        return Integer.compare(this.height, s.height);
    }

    public String toString() {
        return this.getClass().getName() + "[" +
                "{name=" + name + "}" +
                "{height=" + height + "}" +
                "{marks=" + marks + "}]";
    }
}

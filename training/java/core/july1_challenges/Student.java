package july_1;

public class Student implements QueryItem, Comparable<Student> {
    private String name;
    private String course;
    private int year;
    private int studentId;

    public Student(String name, String course, int year, int studentId) {
        this.name = name;
        this.course = course;
        this.year = year;
        this.studentId = studentId;
    }

    public String getName() { return name; }
    public String getCourse() { return course; }
    public int getYear() { return year; }
    public int getStudentId() { return studentId; }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.studentId, other.studentId);
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        return switch (fieldName.toLowerCase()) {
            case "name" -> name.equalsIgnoreCase(value);
            case "course" -> course.equalsIgnoreCase(value);
            case "year" -> Integer.toString(year).equals(value);
            case "id" -> Integer.toString(studentId).equals(value);
            default -> false;
        };
    }

    @Override
    public String toString() {
        return "%s (%s, Year %d, ID: %d)".formatted(name, course, year, studentId);
    }
}

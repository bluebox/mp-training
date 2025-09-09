package Student;

import java.time.LocalDate;
import java.util.*;

public class Student {
    private static long lastId = 1;
    private long id;
    private String country;
    private int enrolledYear;
    private int enrolledAge;
    private String gender;
    private boolean hasExperience;
    private Map<String, CourseEngagement> engagements = new HashMap<>();

    public Student(String country, int enrolledYear, int enrolledAge, String gender, boolean hasExperience, Course... courses) {
        this.id = lastId++;
        this.country = country;
        this.enrolledYear = enrolledYear;
        this.enrolledAge = enrolledAge;
        this.gender = gender;
        this.hasExperience = hasExperience;

        for (Course c : courses) {
            addCourse(c);
        }
    }

    public void addCourse(Course c) {
        engagements.put(c.getCourseCode(), new CourseEngagement(c, LocalDate.of(enrolledYear, 1, 1), "Enrolled"));
    }

    public void watchLecture(String courseCode, int lecture, int month, int year) {
        CourseEngagement engagement = engagements.get(courseCode);
        if (engagement != null) {
            engagement.watchLecture(lecture, LocalDate.of(year, month, 1));
        }
    }

    public double getPercentComplete(String courseCode) {
        CourseEngagement engagement = engagements.get(courseCode);
        return (engagement != null) ? engagement.getPercentComplete() : 0;
    }

    public int getMonthsSinceActive(String courseCode) {
        CourseEngagement engagement = engagements.get(courseCode);
        return (engagement != null) ? engagement.getMonthsSinceActive() : 0;
    }

    public int getAge() {
        return enrolledAge + (LocalDate.now().getYear() - enrolledYear);
    }

    public static Student getRandomStudent(List<Course> courseList) {
        Random random = new Random();
        String[] countries = {"AU", "US", "IN", "CA"};
        String[] genders = {"M", "F", "U"};

        int enrolledYear = LocalDate.now().getYear() - random.nextInt(4); // last 4 years
        int enrolledAge = random.nextInt(18, 40);
        String country = countries[random.nextInt(countries.length)];
        String gender = genders[random.nextInt(genders.length)];
        boolean hasExp = random.nextBoolean();

        int courseCount = random.nextInt(1, courseList.size() + 1);
        List<Course> selectedCourses = new ArrayList<>(courseList);
        Collections.shuffle(selectedCourses);

        Student s = new Student(country, enrolledYear, enrolledAge, gender, hasExp,
                selectedCourses.subList(0, courseCount).toArray(new Course[0]));

        for (Course c : selectedCourses.subList(0, courseCount)) {
            int lecture = random.nextInt(1, c.getLectureCount());
            int year = random.nextInt(enrolledYear, LocalDate.now().getYear() + 1);
            int month = random.nextInt(1, 13);
            s.watchLecture(c.getCourseCode(), lecture, month, year);
        }
        return s;
    }

    public long getId() { return id; }
    public String getCountry() { return country; }
    public int getEnrolledAge() { return enrolledAge; }
    public String getGender() { return gender; }
    public boolean hasExperience() { return hasExperience; }
    public Map<String, CourseEngagement> getEngagements() { return engagements; }
}
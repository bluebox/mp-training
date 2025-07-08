import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class Students {

    String name;
    String gender;
    int age;
    String country;
    int enrolled;
    boolean active;
    String course;
    int per;
    
    public Students(String name, String gender, int age, String country, int enrolled, boolean active,String course,int per) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.enrolled = enrolled;
        this.active = active;
        this.course=course;
        this.per=per;
    }

    

    public static void main(String[] args) {
        
        List<Students> s=new ArrayList<>();
        s.add(new Students("ravi", "male", 63, "india", 5, false,"jvm",70));
        s.add(new Students("raju", "male", 23, "india", 8, true,"pymc",40));
        s.add(new Students("ramya", "female", 33, "india", 7, false,"jvm",55));
        s.add(new Students("Mike", "male", 20, "America",8 , true,"jvm",44));
        s.add(new Students("Roy", "female", 32, "russia", 9, true,"pymc",50));
        long malenoof=s.stream()
                                .filter(n->n.gender.equals("male"))
                                .count();
        long femalenoof=s.stream()
                                .filter(n->n.gender.equals("female"))
                                .count();
        System.out.println("no of males"+malenoof);
        System.out.println("no.of females"+femalenoof);
        long young=s.stream().filter(n->n.age<=30).count();
        long middle=s.stream().filter(n->n.age<=60).count();
        long old=s.stream().filter(n->n.age>60).count();
        System.out.println("young are "+young+"middle age are "+middle+"old age are "+old);
        var b=s.stream().mapToInt(n->n.age).summaryStatistics();
        System.out.println("avg age of students "+b.getAverage());
        System.out.println("max age of a student "+b.getMax());
        System.out.println("min age of a student "+b.getMin());
        System.out.println("sum of students "+b.getSum());
        var c=s.stream().map(n->n.country);
        var v=c.distinct();
        v.forEach(m->System.out.println(m));
        var k=s.stream().filter(n->n.active && (n.enrolled>=7));
        k.forEach(m->System.out.println("active students"+m.name));

                                



    }
    
    
}

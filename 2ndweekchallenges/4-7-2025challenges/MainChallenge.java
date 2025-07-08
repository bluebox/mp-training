import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallenge {
    Map<String,Integer> map=new HashMap<>();
    public static List<Students> generate(){
        List<Students> li=new ArrayList<>();
        
            int age=new Random().nextInt(100);
            String name;
            String gender;
            String country;
            int enrolled=new Random().nextInt(15);
            String[] studentname={"rajesh","raghu","ramesh","githa","rahul"};
            String[] studentcountry={"india","usa","russia","ukrain"};
            int number=new Random().nextInt(20);
            String[] gender_student={"male","female"};
            String[] course_student={"pymc","jvm","creating games in java"};
            int per=new Random().nextInt(100)%5;
            li.add(new Students(studentname[number%(studentname.length)],gender_student[number%2], age,studentcountry[number%studentcountry.length], enrolled,number%2==0,course_student[number%3],per));




        
        return li;
    }
    public static double getPercentComplete(List<Students> li){
        Integer avg_per=li.stream().filter(s->s.course.equals("jvm"))
                    .mapToInt(s->s.per)
                    .reduce(0,(a,b)->(a+b));
        System.out.println("average of percentage is "+avg_per/li.size());
        return avg_per/li.size();

    }
    public static void main(String[] args) {
      List<Students>list1=  (List<Students>) Stream.generate(MainChallenge::generate)
                    .limit(2);
        final var avg_percentage=1.25*getPercentComplete(list1);
       
        var list2= list1.stream().filter(s->s.per%avg_percentage>=3).collect(Collectors.toList());
        list1.stream().filter(s->s.active)
                                .sorted()
                                .limit(10)
                                .map(s->s.course="create new Games");

        list1.forEach(System.out::println);
                                
        





     

                    
    }
    



}

import java.util.Scanner;
public class Day2_challengespart3 {
    public static void main(String[] args) {
        //challenge 12
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the minutes");
        long min=sc.nextLong();
        printYearandDays(min);






        //challenge13
        System.out.println("Enter  the shape circle(c) or rectange(r)");
        String shape=sc.next();
        if(shape.equals("c")){
            System.out.println("Enter the radius");
            double radius=sc.nextDouble();
            System.out.println("area of circle is"+area(radius));
        }
        else if(shape.equals("r")){
            System.out.println("Enter the sides of rectangle");
            double l=sc.nextDouble();
            double b=sc.nextDouble();
            System.out.println("area of rectange is"+area(l,b));
        }




        //challenge 14
        System.out.println("Enter the minutes to get remaining sec");

       
        int sec2=sc.nextInt();
        System.out.println("is user want to enter min y or n?");
        String ynmin=sc.next();
        if(ynmin.equals("y"))
        {
            System.out.println("Enter min");
             int min2=sc.nextInt();
             System.out.println(getDuration(min2,sec2));
        }
        else{ System.out.println(getDuration(sec2));}
       
    }
    public static void printYearandDays(long min){

        if(min<0){
            System.out.println("invalid min");
            return ;
        }

        long hr=min/60;
        int days=(int)hr/24;
        int years=days/365;
        if(years>0){
            days=days%365;
        }
        System.out.println(min+"minutes="+years+"y and "+days+"d");

    }
    public static double area(double radius){
        if (radius<0)return -1.0;
        return 3.14*radius*radius;
    }
    public static double area(double x,double y){
        if(x<0 || y<0) return -1.0;
        return x*y;
    }
    public static String getDuration(int sec){
        if(sec<0)
        return "invalid";
        int min=sec/60;
        return getDuration(min,sec);
    }
    public static String getDuration(int min,int sec){
        if (min>60 || sec<0)return "invalid min or sec";
        int hr=min/60;
        int remainingmin=min%60;
        int remainingsec=sec%60;
        return "hours "+hr+"remaining minutes "+remainingmin+"remaining seconds"+remainingsec;
    }
    
}

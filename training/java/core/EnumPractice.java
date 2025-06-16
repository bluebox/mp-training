import java.util.*;
class EnumPractice{
    public static void main(String[] args){
        enum days{
            MONDAY(1),TUESDAY(2),WEDNESDAY(3),THURSDAY(4),FRIDAY(5),SATURDAY(6),SUNDAY(7);
            int index;
            private days(int index){
                this.index=index;
            }
            public void printIds(){
                System.out.println("ID="+this.index);
            }
         }
       Scanner sc=new Scanner(System.in);
       days day=days.valueOf(sc.next());
       switch(day){
           case MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY->System.out.println("Its weekday");
           case SATURDAY,SUNDAY->System.out.println("Its weekend");
       }
       day.printIds();
   }
}

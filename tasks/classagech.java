import java.util.Scanner;
public class classagech {
    private String firstname;
    private String lastname;
    private int age;

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        
        classagech person = new classagech();
        System.out.println("enter the firstname:");
         person.firstname = sc.nextLine();
         System.out.println("enter the lastname:");
         person.lastname = sc.nextLine();
         System.out.println("enter the age:");
        person.age = sc.nextInt();
        System.out.println("is teen"+person.isTeen());
        System.out.println("full time:"+person.fullname());

    }
     public String getFirstname(){
        
        return firstname; 

    
    }
public String getLastname(){
    return lastname;
}    
public int getAge(){
    return age;
}
public void setFirstName(String firstname){
    this.firstname=firstname;
}
public void setLastName(String lastname){
    this.lastname=lastname;
}
public void setAge(int age){
    this.age=age;
    if (age<0 && age>100){
        age=0;
    }
}
public boolean isTeen(){
    return age > 12 || age < 20;
}
public  String fullname(){
    return firstname+lastname;
}
   
    
}

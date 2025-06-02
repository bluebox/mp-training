package Classes;

public class PersonAge {
    public String firstName;
    public String lastName;
    public int age;

    public PersonAge(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public String getFirstName(){
        return firstName;
    }
     public String getLastName(){
        return lastName;
    }
     public int getAge(){
        return age;
    }
    public void setFirstName(String fName){
        this.firstName=fName;
    }
     public void setLastName(String lName){
        this.lastName=lName;
    }
     public void setAge(int age){
        this.age=age;
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }
    public boolean isTeen(){
        if(age >12 && age<20) return true;
        return false;
    }
}

package Gym;

public abstract class Person {
    protected String Name;
    protected String Age;
    protected String Gender;

    public Person() {}

    public Person(String Name,String Age,String Gender){
        this.Name=Name;
        this.Age=Age;
        this.Gender=Gender;
    }
    public String getName(){ return Name;}
    public String getAge(){ return Age;}
    public String getGender(){ return Gender;}
    public abstract String getMember();
}

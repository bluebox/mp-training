public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public int getAge() {
        return age;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setAge(int age) {
        if(age<0||age>100){
            this.age=0;
            return;
        }
        this.age = age;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public boolean isTeen(){
        if(this.age>12 && this.age<20)return true ;
        return false;
    }
    public String getFullName(){
        return this.firstName+this.lastName;
    }
}

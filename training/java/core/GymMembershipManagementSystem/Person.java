package gymSystem;

abstract class Person {
    private String name;
    private int age;
    public Person(){
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    abstract void showDetails();

}

package MyGymApplication;
abstract class Person{

     private String name;
     private int age;

    void details(){
        System.out.println("-".repeat(20));
        System.out.println(this.getClass().getSimpleName() + "\nName : "+ 
        this.name +" age : "+ this.age);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
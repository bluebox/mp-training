public abstract class Person implements Display{
    private String name;
    private int age;

    public Person(String name,int age){
        this.name=name;
        setAge(age);

    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }

    public void setName(String name){
          this.name=name;
    }
    public void setAge(int age){
        if(age<=0){
            throw new IllegalArgumentException("Age shoulg be positive");
        }
        this.age=age;

    }
    public abstract void showDetails();

}

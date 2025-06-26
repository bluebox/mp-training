package OOPS;

class Animals{
    private String name;
    private String sound;

    Animals(String name){
        this(name , "unknown");
    }
    Animals(String name , String sound){
        this.name = name;
        this.sound = sound;
    }

    public String getName(){
        return this.name;

    }

    public String getSound(){
        return this.sound;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSound(String sound){
        this.sound = sound;
    }


}
public class Encapsulation {

    public static void main(String[] args) {
        Animals cat = new Animals("pilli" , "meow");

    System.out.println(cat.getName());

    cat.setName("rocky");

    System.out.println(cat.getName());

        
    }
    
}

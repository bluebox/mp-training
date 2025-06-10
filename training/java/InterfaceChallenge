import java.util.List;
import java.util.ArrayList;
public class InterfaceChallenge{
    public static void main(Strings[] args){
        Player prasad=new Player("prasad","120","100");
        Player pras=new Player("pras","12","10");
        System.out.println(prasad);
    }
}





public  interface ISavailable{
    List<String> write();
    public List<String> read();
}

public class Player implement ISavailable{
    private String name;
    private String weapon;
    private int hitPoints;
    private int strength;
    public Player(String name,int hitPoints,int strength){
        this.name=name;
        this.hitPoints=hitPoints;
        this.strength=strength;
        this.weapon="Sword";
    }
    public String getName(){
        return name;
    }
    public String getWeapon(){
        return weapon;
    }
    public int getStrength(){
        return strength;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public List<String> write(){
        List<String> values=new ArrayList<>();
        values.add(this.name);
        values.add(this.hitPoints);
        values.add(this.strength);
        values.add(this.weapon);
        return values;
    }
    public void read(List<String> values){
        this.name=values.get(0);
        this.hitPoints=integer.perseInt(values.get(1));
        this.strength=integer.perseInt(values.get(2));
        this.weapon=values.get(3);
    }
    public String toString(){
        return "Person[Name:"+name+" hitPoints:"+hitPoints+" Strength:"+strength+" weapon:"+weapon+"]";
    }

}
}

public class Monster implement ISavailable{
    private String name;
    
    private int hitPoints;
    private int strength;
    public Monster(String name,int hitPoints,int strength){
        this.name=name;
        this.hitPoints=hitPoints;
        this.strength=strength;
        
    }
    public String getName(){
        return name;
    }
    
    public int getStrength(){
        return strength;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public List<String> write(){
        List<String> values=new ArrayList<>();
        values.add(this.name);
        values.add(this.hitPoints);
        values.add(this.strength);
        return values;
    }
    public void read(List<String> values){
        this.name=values.get(0);
        this.hitPoints=integer.perseInt(values.get(1));
        this.strength=integer.perseInt(values.get(2));
        
    }
    public String toString(){
        return "Monster[Name:"+name+" hitPoints:"+hitPoints+" Strength:"+strength+"]";
    }
    

}}
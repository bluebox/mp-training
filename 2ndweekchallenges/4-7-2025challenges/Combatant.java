enum Loot{
    GOLD,
    PERALS;
     Loot(){
        System.out.println("loot");
     }

   

}
enum Feature{

    POSITIVE,
    NEGATIVE
}

 record Town(String name,String island,int level,Loot loot,Feature feature,String opponents) {
    public Town{
        

    }

}
sealed public abstract class Combatant permits Islander,Soldier,Pirate{

    private String name;
    private String weapon;
    private String game_data;

    public void setGame_data(String game_data) {
        this.game_data = game_data;
    }
    public String getGame_data() {
        return game_data;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    public String getWeapon() {
        return weapon;
    }

}

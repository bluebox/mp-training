import java.util.*;
interface ISaveable{
   List<String> write();
   void read(List<String> list);
}
class Player implements ISaveable{
   private String name;
   private String weapon;
   private int hitPoints;
   private int strengths;
   public Player(String name,int hitPoints,int strengths){
      this.name=name;
      this.hitPoints=hitPoints;
      this.strengths=strengths;
      this.weapon="Sword";
   }
   public String getName(){
      return name;
   }
   public String getWeapon(){
      return weapon;
   }
   public int getHitPoints(){
      return hitPoints;
   }
   public int getStrengths(){
      return strengths;
   }
   public void setName(String name){
      this.name=name;
   }
   public void setWeapon(String weapon){
      this.weapon=weapon;
   }
   public void setHitPoints(int hitPoints){
      this.hitPoints=hitPoints;
   }
   public void setStrengths(){
      this.strengths=strengths;
   }
   @Override
   public List<String> write(){
      List<String> list=new ArrayList<>();
      list.add(this.name);
      list.add(String.valueOf(this.hitPoints));
      list.add(String.valueOf(this.strengths));
      list.add(this.weapon);
      return list;
   }
   @Override
   public void read(List<String> list){
      if(list!=null && list.size()>0){
         this.name=list.get(0);
         this.hitPoints=Integer.parseInt(list.get(1));
         this.strengths=Integer.parseInt(list.get(2));
         this.weapon=list.get(3);
      }
   }  
   @Override
   public String toString(){
      return "Player{name="+name+",hitPoints="+hitPoints+",strengths="+strengths+",weapon="+weapon+"}";
   }
}
class Monster implements ISaveable{
   private String name;
   private int hitPoints;
   private int strengths;
   public Monster(String name,int hitPoints,int strengths){
       this.name=name;
       this.hitPoints=hitPoints;
       this.strengths=strengths;
   }
   public String getName(){
      return name;
   }
   public int getHitPoints(){
      return hitPoints;
   }
   public int getStrengths(){
      return strengths;
   }
   public void setName(String name){
      this.name=name;
   }
   public void setHitPoints(int hitPoints){
      this.hitPoints=hitPoints;
   }
   public void setStrengths(){
      this.strengths=strengths;
   }
   @Override
   public List<String> write(){
      List<String> list=new ArrayList<>();
      list.add(name);
      list.add(String.valueOf(hitPoints));
      list.add(String.valueOf(strengths));
      return list;
   }
   @Override
   public void read(List<String> list){
      if(list!=null && list.size()>0){
          this.name=list.get(0);
          this.hitPoints=Integer.parseInt(list.get(1));
          this.strengths=Integer.parseInt(list.get(2));
      }
   }
   @Override
   public String toString(){
      return "Monster{name="+name+",hitPoints="+hitPoints+",strengths="+strengths+"}";
   }
}
class SimpleInterface{
   public static void main(String[] args){
       Player player=new Player("Tim",10,15);
       Monster monster=new Monster("Werewolf",20,40);
       System.out.println(player);
       System.out.println(monster);
   }
}













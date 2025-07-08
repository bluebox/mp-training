package day_4_7_2025;

enum Loot{
	GOLD,
	PEARLS;	
	
	private Loot() {
		
	}
}

enum Feature{
	POSITIVE,
	NEGATIVE;
	
	private Feature() {
		
	}
	
}

record Town(String name,String Island,Feature feature,Loot loottype) {
	public Town{
		//compact constructor
	}
}

sealed public abstract class  Combat permits Pirate,Soldier,Islander {
       public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	public String getGame_data() {
		return game_data;
	}
	public void setGame_data(String game_data) {
		this.game_data = game_data;
	}
	String name;
       String weapon;
       String game_data;
       
       
       
       
}

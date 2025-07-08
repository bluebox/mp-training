package day7;

import java.util.Random;

public class Game {
	private int health;
	private String island;
	private String resources;
	private String bandits;
	private String weapon;
	public static Random r=new Random();
	public Game(String island,String resources,String bandits,String weapon) {
		this.health=500;
		this.island=island;
		this.resources=resources;
		this.bandits=bandits;
		this.weapon=weapon;
	}
	
	public static Game generateIslands() {
		String[] islands=new String[] {"Paradise","long throne","high land","Winterfell"};
		String island=islands[r.nextInt(islands.length)];
		
		String[] resources=new String[] {"gold","silver","diamonds","enchanments"};
		String resource=resources[r.nextInt(resources.length)];
		
		String[] bandits=new String[] {"hound","dragon","wild wolf","fire breather"};
		String bandit=bandits[r.nextInt(bandits.length)];
		
		String[] weapons=new String[] {"sword"," Magic wand"};
		String weapon=weapons[r.nextInt(weapons.length)];
		
		return new Game(island,resource,bandit,weapon);
	}
	
	
	
	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public String toString() {
		return "Welcome to "+island+" Island"+" resources "+resources+" bandit : "+bandits;
	}

	public String getIsland() {
		return island;
	}

	public void setIsland(String island) {
		this.island = island;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getBandits() {
		return bandits;
	}

	public void setBandits(String bandits) {
		this.bandits = bandits;
	}

	
	
	
}

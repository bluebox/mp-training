package day11.Interface;

import java.util.ArrayList;
import java.util.List;

public class Player implements ISaveable {

	
	
	private String name,weapon;
	private int hintPoints,strength;
	
	Player(int hintPoints,int strength,String name){
		this.hintPoints=hintPoints;
		this.name=name;
		this.strength=strength;
		this.weapon="sword";
	}
	@Override
	  public List<String>write(){
	        List<String> values = new ArrayList<String>();
	        values.add(0, this.name);
	        values.add(1, "" + this.hintPoints);
	        values.add(2, "" + this.strength);
	        values.add(3, this.weapon);

	        return values;
	    }
	  @Override
	    public void read(List<String> savedValues) {
	        if(savedValues != null && savedValues.size() >0) {
	            this.name = savedValues.get(0);
	            this.hintPoints = Integer.parseInt(savedValues.get(1));
	            this.strength = Integer.parseInt(savedValues.get(2));
	            this.weapon = savedValues.get(3);
	        }
	        for(String s:savedValues)
	        	System.out.print(s+" ");
	    }
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	public void setWeapon(String weapon) {
		this.weapon=weapon;
	}
	public String setWeapon() {
		return this.weapon;
	}
	public void setStrength(int strength) {
		this.strength=strength;
	}
	public int getStrength() {
		return this.strength;
	}
	public void setHintPoints(int hintPoints) {
		this.hintPoints=hintPoints;
	}
	public int getHintPoints() {
		return this.hintPoints;
	}
	@Override
	public String toString() {
		return "player{name= "+name+" hintPoints= "+hintPoints+" strength= "+strength+" weapon = "+weapon;
	}
	
}

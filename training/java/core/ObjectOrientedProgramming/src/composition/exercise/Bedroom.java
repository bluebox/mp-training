package composition.exercise;

public class Bedroom {
	
	
	private String name;
	private Wall wall1,wall2,wall3,wall4;
	private Ceiling ceiling;
	private Bed bed;
	private Lamp lamp;
	public Bedroom(String name, Wall wall1, Wall wall2, Wall wall3, Wall wall4, Ceiling ceiling, Bed bed, Lamp lamp) {
		super();
		this.name = name;
		this.wall1 = wall1;
		this.wall2 = wall2;
		this.wall3 = wall3;
		this.wall4 = wall4;
		this.ceiling = ceiling;
		this.bed = bed;
		this.lamp = lamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Wall getWall1() {
		return wall1;
	}
	public void setWall1(Wall wall1) {
		this.wall1 = wall1;
	}
	public Wall getWall2() {
		return wall2;
	}
	public void setWall2(Wall wall2) {
		this.wall2 = wall2;
	}
	public Wall getWall3() {
		return wall3;
	}
	public void setWall3(Wall wall3) {
		this.wall3 = wall3;
	}
	public Wall getWall4() {
		return wall4;
	}
	public void setWall4(Wall wall4) {
		this.wall4 = wall4;
	}
	public Ceiling getCeiling() {
		return ceiling;
	}
	public void setCeiling(Ceiling ceiling) {
		this.ceiling = ceiling;
	}
	public Bed getBed() {
		return bed;
	}
	public void setBed(Bed bed) {
		this.bed = bed;
	}
	public Lamp getLamp() {
		return lamp;
	}
	public void setLamp(Lamp lamp) {
		this.lamp = lamp;
	}
	
	

}

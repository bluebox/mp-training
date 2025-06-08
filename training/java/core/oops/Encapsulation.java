package oops;
class ClubMember{
	private String name;
	private int id;
	private String level;
	ClubMember(int id, String name, String level){
		this.id=id;
		this.name=name;
		this.level=level.toUpperCase();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String toString() {
		return "ClubMember [name=" + name + ", id=" + id + ", level=" + level + "]";
	}
}
public class Encapsulation {

	public static void main(String[] args) {
		ClubMember club= new ClubMember(657, "Karn", "GOLD");
		System.out.println(club.getId());
		System.out.println(club.getName());
		System.out.println(club.getLevel());
		club.setLevel("Platinum");
		System.out.println(club.toString());
	}

}

import java.time.LocalDate;

public class Member extends Person{
    private int id=1;
    private MemberShip type;
    private LocalDate joinDate;
    public Member(String name,int age,MemberShip type,LocalDate date,int id) {
    	this.name=name;
    	this.age=age;
    	this.type=type;
    	this.joinDate=date;
    	this.id=id;
    }
	@Override
	void getDetails() {
		System.out.println(id+" "+this.name+" of age "+this.age+" joined on "+joinDate+" for "+this.type.getDuration()+" months with "+this.type.toString());
	}
    public String getName() {
    	return this.name;
    }
    public int getAge() {
    	return this.age;
    }
    public int getId() {
    	return id;
    }
    public MemberShip getMemberShip() {
    	return this.type;
    }
    public LocalDate getJoiningDate() {
    	return this.joinDate;
    }
    public String getMemberShipDetails() {
    	return this.type.toString();
    }
}

package tasks;


enum usagetype{
	business,entertainment;
	
}

public class Building implements mappable {
	private String name;
	private usagetype usage;
	public Building(String name, usagetype usage) {
		this.name=name;
		this.usage=usage;
	}
}

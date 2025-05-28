package enums;

public enum Planet {
	Mercury (1,500),
	Venus (2,1000),
	Earth (3,1500),
	Mars (4,2000);
	
	int id;
	int distance;
	Planet(int id, int distance)
	{
		this.id=id;
		this.distance=distance;
	}

}
